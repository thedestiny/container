package com.it.service;

/**
 * Created by xieyue on 2016/7/13.
 * CustomService
 */


import com.google.common.collect.Maps;
import com.it.mapper.CustomMapper;
import com.it.pojo.Custom;
import com.it.pojo.User;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
public class CustomService {

    Logger logger = LoggerFactory.getLogger(CustomService.class);

    @Inject
    private CustomMapper customMapper;

    @Inject
    private UserService userService;

    /**
     * 插入客户
     */
    public Integer insertCustom(Custom custom) {
        custom.setCreatetime(SmallUtils.getTime());
        custom.setUserid(ShiroUtil.getCurrentUserId());
        custom.setRealname(ShiroUtil.getCurrentRealname());
        // 根据公司id查找公司名称，设置给custom
        if (custom.getDependid() != null) {
            custom.setCompany(findCustomById(custom.getDependid()).getCustomer());
        }
        custom.setPinyin(SmallUtils.transToPinyin(custom.getCustomer()));
        return customMapper.insertCustom(custom);
    }

    /**
     * 根据客户id删除客户
     */
    @Transactional
    public Integer deleteCustom(Integer id) {

        Custom custom = findCustomById(id);
        if (custom.getType().equals("company")) {
            Map<String, Object> map = Maps.newHashMap();
            map.put("dependid", id);
            map.put("type", "person");
            List<Custom> customList = queryCustomInformationByParam(map);
            // TODO: 2016/7/14 项目管理
            if (customList.size() < 1) {
                return customMapper.deleteCustom(id);
            } else {
                // 删除与关联客户的信息
                for (Custom cust : customList) {
                    cust.setCompany(null);
                    cust.setDependid(null);
                    // 修改个人的company
                    customMapper.updateCustomInf(cust);
                }
                return customMapper.deleteCustom(id);
            }
        } else {
            return customMapper.deleteCustom(id);
        }

    }

    /**
     * 修改客户信息
     * 1.是个人的情况2.是公司的情况
     */
    @Transactional
    public Integer updateCustomInformation(Custom custom) {
        // 1 .是个人情况
        // 转换拼音
        custom.setPinyin(SmallUtils.transToPinyin(custom.getCustomer()));
        if (custom.getType().equals(Custom.CUSTOM_TYPE_PERSON)) {
            if (custom.getDependid() != null) {
                custom.setCompany(findCustomById(custom.getDependid()).getCustomer());
            }
            return customMapper.updateCustom(custom);
        } else {
            // 获取公司名称 以及id
            String customer = custom.getCustomer();
            Integer id = custom.getId();
            // 获取关联用户
            Map<String, Object> map = Maps.newHashMap();
            map.put("dependid", id);
            map.put("type", Custom.CUSTOM_TYPE_PERSON);
            List<Custom> customList = queryCustomInformationByParam(map);
            // customList 或者 custom 没有修改 customer
            if (customList.size() > 0) {
                if (customList.get(0).getCompany().equals(customer)) {
                    return customMapper.updateCustom(custom);
                }
                for (Custom cust : customList) {
                    cust.setCompany(customer);
                    // 修改个人的company
                    customMapper.updateCustom(cust);
                }
                return customMapper.updateCustom(custom);
            } else {
                return customMapper.updateCustom(custom);
            }
        }
    }

    /**
     * 查询所有客户
     */
    public List<Custom> findAllCustom() {
        return queryCustomInformationByParam(new HashMap<String, Object>());
    }

    /**
     * 根据客户id 查询资料
     */
    public Custom findCustomById(Integer id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        if (ShiroUtil.isAdmin()) {
            return null;
        }
        if (ShiroUtil.isEmployee()) {
            map.put("userid", ShiroUtil.getCurrentUserId());
        }
        return customMapper.queryCustomByParam(map);
    }


    /**
     * @param param 根据参数查找客户列表 员工自动加上 userid
     * @return List<Custom>
     */
    public List<Custom> queryCustomInformationByParam(Map<String, Object> param) {

        if (ShiroUtil.isAdmin()) {
            return null;
        }
        if (ShiroUtil.isEmployee()) {
            param.put("userid", ShiroUtil.getCurrentUserId());
        }
        return customMapper.queryCustomByParams(param);

    }

    public List<Custom> queryAllCompany() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("type", Custom.CUSTOM_TYPE_COMPANY);
        return queryCustomInformationByParam(map);
    }

    public long queryCustomTotal() {
        return queryFilterCustomNum(new HashMap<String, Object>());
    }

    public long queryFilterCustomNum(Map<String, Object> param) {
        if (ShiroUtil.isAdmin()) {
            return 0;
        }
        if (ShiroUtil.isEmployee()) {
            Integer userid = ShiroUtil.getCurrentUserId();
            param.put("userid", userid);
        }
        return customMapper.queryCustomNumByParams(param);
    }

    public void openTheCustom(Integer id) {
        Custom custom = new Custom();
        custom.setId(id);
        custom.setUserid(null);
        custom.setRealname(null);
        customMapper.updateUserid(custom);
    }

    public void privateTheCustom(Integer id) {
        Custom custom = new Custom();
        custom.setId(id);
        custom.setUserid(ShiroUtil.getCurrentUserId());
        custom.setRealname(ShiroUtil.getCurrentRealname());
        customMapper.updateUserid(custom);
    }


    public void moveCustom(Custom custom) {
        Integer userid = custom.getUserid();
        User user = userService.findUserById(userid);
        logger.debug("realname is {}", custom.getRealname());
        custom.setRealname(user.getRealname());
        customMapper.updateCustom(custom);
    }

    /**
     * 转换成可以转换二维码的参数
     * @param id 传入 Custom的id
     * @return 转换成可以转换二维码的字符串
     */
    public String makeEcard(Integer id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        Custom custom = customMapper.queryCustomByParam(map);
        StringBuilder ecard = new StringBuilder("MECARD:");
        if (StringUtils.isNotEmpty(custom.getCustomer())) {
            ecard.append("N:" + custom.getCustomer() + ";");
        }
        if (StringUtils.isNotEmpty(custom.getTel())) {
            ecard.append("TEL:" + custom.getTel() + ";");
        }
        if (StringUtils.isNotEmpty(custom.getEmail())) {
            ecard.append("EMAIL:" + custom.getEmail() + ";");
        }
        if (StringUtils.isNotEmpty(custom.getAddress())) {
            ecard.append("ADR:" + custom.getAddress() + ";");
        }
        if (StringUtils.isNotEmpty(custom.getCompany())) {
            ecard.append("ORG:" + custom.getCompany() + ";");
        }
        ecard.append(";");
        return ecard.toString();
    }
}

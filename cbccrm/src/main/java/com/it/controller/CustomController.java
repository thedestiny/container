package com.it.controller;

/**
 * Created by xieyue on 2016/7/13.
 * CustomController
 */


import com.google.common.collect.Maps;
import com.it.dto.DataTablesResult;
import com.it.pojo.Custom;
import com.it.pojo.User;
import com.it.service.CustomService;
import com.it.service.UserService;
import com.it.utils.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/custom")
public class CustomController {

    Logger logger = LoggerFactory.getLogger(CustomController.class);

    @Inject
    private CustomService customService;

    @Inject
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public String getCustomPage(){
        return "/custom/customlist";
    }

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String addNewCustom(Custom custom){
        Integer n = customService.insertCustom(custom);
        return n == 1 ? "success":"failure";
    }

    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    @ResponseBody
    public String editCustom(Custom custom){
        Integer n = customService.updateCustomInformation(custom);
        return n == 1 ? "success":"failure";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Custom> showCustomList(HttpServletRequest request){

        String draw = request.getParameter("draw");
        String start = request.getParameter("start"); //当前页偏移量
        String length = request.getParameter("length"); //每页显示多少条数据
        String weixin = request.getParameter("weixin");
        String keyword = request.getParameter("search[value]");
        keyword = SmallUtils.transToPinyin(keyword);
        weixin = SmallUtils.transtoUTF8(weixin);
        String sortColumnIndex = request.getParameter("order[0][column]"); //获取排序列的索引
        String sortColumnName = request.getParameter("columns[" + sortColumnIndex + "][name]"); //根据排序列的索引获取列的名字
        String sortType = request.getParameter("order[0][dir]");//排序方式 asc | desc

        Map<String, Object> param = Maps.newHashMap();
        param.put("start", Integer.parseInt(start));
        param.put("keyword",keyword);
        param.put("length", Integer.parseInt(length));
        param.put("username", weixin);
        param.put("sortColumn", sortColumnName);
        param.put("sortType", sortType);
        // 获取用户列表
        List<Custom> customList = customService.queryCustomInformationByParam(param);
        // 数据库总条数
        long total = customService.queryCustomTotal();
        // 返回查找到的条数
        long filter = customService.queryFilterCustomNum(param);
        Map<String, Object> result = Maps.newHashMap();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", filter);
        result.put("data", customList);
        return new DataTablesResult<>(draw, customList, total, filter);

    }

    @RequestMapping(value = "/company",method = RequestMethod.GET)
    @ResponseBody
    public List<Custom> loadCompanyCustom(){
        return customService.queryAllCompany();
    }

    @RequestMapping(value = "/edit/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> findCustomById(@PathVariable Integer id){
        Map<String,Object> map = Maps.newHashMap();
        Custom custom = customService.findCustomById(id);
        List<Custom> customList = customService.queryAllCompany();
        if(custom != null){
            map.put("state","success");
            map.put("custom",custom);
            map.put("companyList",customList);
        }else{
            map.put("state","error");
            map.put("msg","找不到custom");
        }
        return map;
    }

    @RequestMapping(value = "/del/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String delCustom(@PathVariable  Integer id){
        Integer n = customService.deleteCustom(id);
        return n == 1 ? "success":"failure";
    }

    @RequestMapping(value = "/detail/{id:\\d+}",method = RequestMethod.GET)
    public String customDetailInf(@PathVariable Integer id, Model model){
        Custom custom = customService.findCustomById(id);
        if(custom.getType().equals("company")){
            Map<String,Object> map = Maps.newHashMap();
            map.put("dependid",id);
            map.put("type","person");
            List<Custom> customList = customService.queryCustomInformationByParam(map);
            model.addAttribute("customList",customList);
        }
        model.addAttribute("custom",custom);
        return "/custom/details";
    }

    @RequestMapping(value = "/open/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String openCustom(@PathVariable Integer id){
        customService.openTheCustom(id);
        return "success";
    }

    @RequestMapping(value = "/private/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String privateCustom(@PathVariable Integer id){
        customService.openTheCustom(id);
        return "success";
    }



    @RequestMapping(value = "/allusers",method = RequestMethod.GET)
    @ResponseBody
    public List<User> findAllUser(){
        return userService.findAllUsers();
    }

    @RequestMapping(value = "/move",method = RequestMethod.POST)
    public String moveCustom(Custom custom,Model model){
        customService.moveCustom(custom);
        model.addAttribute("message","客户转移成功");
        return "redirect:/custom";
    }


}

package com.it.controller;

/**
 * Created by xieyue on 2016/7/8.
 * UserController
 */


import com.google.common.collect.Maps;
import com.it.dto.DataTablesResult;
import com.it.pojo.Login;
import com.it.pojo.User;
import com.it.service.UserService;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Inject
    private UserService userService;

    // 获取日志界面
    @RequestMapping(value = "/log", method = RequestMethod.GET)
    public String showUserLog() {
        return "setting/log";
    }

    // 获取数据ajax
    @RequestMapping(value = "/log/load", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult loadUserLog(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start");
        String length = request.getParameter("length");
        List<Login> loginList = userService.queryLoginLog(start, length);
        long total = userService.queryCountByUserid();
        return new DataTablesResult<>(draw, loginList, total, total);
    }

    // 获取修改密码界面
    @RequestMapping(value = "/password", method = RequestMethod.GET)
    public String updatePassword() {
        return "setting/password";
    }

    // 验证密码是否正确，用于修改密码remote
    @RequestMapping(value = "/identify")
    @ResponseBody
    public String identityPassword(String password,
                                   @RequestHeader("X-Requested-With") String xRequestWith) {

        if ("XMLHttpRequest".equals(xRequestWith)) {
            String pwd = ShiroUtil.getCurrentUser().getPassword();
            return pwd.equals(password) ? "true" : "false";
        } else {
            throw new RuntimeException("服务器正在忙。。。。");
        }
    }

    // 提交修改密码
    @RequestMapping(value = "/password",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public String updatePassword(String password) {
        int n = userService.updateUserPWD(password);
        return SmallUtils.transtoISO(n == 1 ? "修改成功" : "修改失败");
    }

    // 员工管理页面
    @RequestMapping(value = "/manage", method = RequestMethod.GET)
    public String getUserManagePage(Model model) {
        model.addAttribute("roleList", userService.queryRole());
        return "manage";
    }

    // 获取数据
    @RequestMapping(value = "/manage/load", method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult getUserInformation(HttpServletRequest request) {
        String draw = request.getParameter("draw");
        String start = request.getParameter("start"); //当前页偏移量
        String length = request.getParameter("length"); //每页显示多少条数据
        String username = request.getParameter("username");
        String roleid = request.getParameter("roleid");
        String realname = request.getParameter("realname");
        username = SmallUtils.transtoUTF8(username);
        roleid = SmallUtils.transtoUTF8(roleid);
        realname = SmallUtils.transtoUTF8(realname);
        String sortColumnIndex = request.getParameter("order[0][column]"); //获取排序列的索引
        String sortColumnName = request.getParameter("columns[" + sortColumnIndex + "][name]"); //根据排序列的索引获取列的名字
        String sortType = request.getParameter("order[0][dir]");//排序方式 asc | desc

        length = length == null ? "10" : length;
        start = start == null ? "0" : start;
        sortColumnName = StringUtils.isEmpty(sortColumnName) ? "id": sortColumnName;
        sortType = StringUtils.isEmpty(sortType) ? "asc": sortType;

        Map<String, Object> param = Maps.newHashMap();
        param.put("start", Integer.parseInt(start));

        param.put("length", Integer.parseInt(length));
        param.put("username", username);
        if (StringUtils.isNotEmpty(roleid)) {
            param.put("roleid", Integer.parseInt(roleid));
        }
        param.put("realname", realname);
        param.put("sortColumn", sortColumnName);
        param.put("sortType", sortType);
        // 获取用户列表
        List<User> userList = userService.queryUserInformationByParam(param);
        // 数据库总条数
        long total = userService.queryUserTotal();
        // 返回查找到的条数
        long filter = userService.queryFilterUserNum(param);
        Map<String, Object> result = Maps.newHashMap();
        result.put("draw", draw);
        result.put("recordsTotal", total);
        result.put("recordsFiltered", filter);
        result.put("data", userList);
        return new DataTablesResult<>(draw, userList, total, filter);
    }

    // 提交新员工资料
    @RequestMapping(value = "/manage/add", method = RequestMethod.POST)
    @ResponseBody
    public String addNewEmployee(User user) {
        Integer n = userService.addUser(user);
        return n == 1 ? "success" : "failure";
    }

    // 验证username是否可用
    @RequestMapping(value = "/manage/identify", method = RequestMethod.GET)
    @ResponseBody
    public String identifyUsername(HttpServletRequest request) {
        // todo 只允许ajax请求 request请求头
        String username = request.getParameter("username");
        User user = userService.findUserByUsername(username);
        return user == null ? "true" : "false";
    }

    // 根据username 或者user id 获取 用户资料 这里用id
    @RequestMapping(value = "/manage/edit/{id}", method = RequestMethod.GET)
    @ResponseBody
    public User queryUserInfById(@PathVariable Integer id) {
        return userService.findUserById(id);
    }

    // 提交编辑的信息
    @RequestMapping(value = "/manage/edit", method = RequestMethod.POST)
    @ResponseBody
    public String commitUserInfo(User user) {
        Integer n = userService.updateUserInfo(user);
        return n == 1 ? "success" : "failure";
    }

    // 冻结该账户
    @RequestMapping(value = "/manage/block/{id}", method = RequestMethod.GET)
    public String blockAccount(@PathVariable Integer id) {
        User user = new User(id);
        user.setEnable(false);
        Integer n = userService.updateUserInfo(user);
        return n == 1 ? "success" : "failure";
    }


}

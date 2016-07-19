package com.it.controller;

import com.google.common.collect.Maps;
import com.it.dto.JSONResult;
import com.it.pojo.Task;
import com.it.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/schedule")
public class TaskController {
    Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Inject
    private TaskService taskService;

    /**
     * 获得待办事项列表/以及未办事项
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String getSchedulePage(Model model){
        List<Task> taskList = taskService.findTimeOutTask();
        model.addAttribute("taskList",taskList);
        return "schedule/tasklist";
    }

    /**
     * 加载数据
     * @param start
     * @param end
     * @return
     */
    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public List<Task> getScheduleList(String start, String end){
        Map<String,Object> map = Maps.newHashMap();
        map.put("start",start);
        map.put("end",end);
        return taskService.queryTaskByParams(map);
    }

    /**
     * 添加新的待办事项
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public JSONResult addNewSchedule(Task task, String hour, String min){
        String remindtime = hour + ":"+min + ":00";
        task.setRemindtime(remindtime);
        taskService.addNewUserTask(task);
        return new JSONResult(task);
    }

    /**
     * 修改该事项为已完成
     */
    @RequestMapping(value = "/done/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public JSONResult finishTheTask(@PathVariable Integer id){
        Task task = taskService.updateTaskInf(id);
        return new JSONResult(task);
    }

    /**
     * 删除事项
     */
    @RequestMapping(value = "/del/{id:\\d+}",method = RequestMethod.GET)
    @ResponseBody
    public String deleteTask(@PathVariable Integer id){
        taskService.delteTheTask(id);
        return "success";
    }

    // 未完成的事项





}

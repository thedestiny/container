package com.it.service;

/**
 * Created by xieyue on 2016/7/18.
 * TaskService
 */


import com.google.common.collect.Maps;
import com.it.mapper.TaskMapper;
import com.it.pojo.Task;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
public class TaskService {
    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Inject
    private TaskMapper taskMapper;

    /**
     * 新增事项
     */
    public void addNewUserTask(Task task){
        task.setUserid(ShiroUtil.getCurrentUserId());
        task.setDone(false);
        taskMapper.addNewTask(task);
    }

    /**
     * 删除事项
     */
    public void delteTheTask(Integer id){
        Map<String,Object> map = Maps.newHashMap();
        map.put("userid",ShiroUtil.getCurrentUserId());
        map.put("id",id);
        taskMapper.deleteTask(map);
    }


    /**
     * 更新事项
     */
    public Task updateTaskInf(Integer id){
        Task task = new Task();
        task.setId(id);
        task.setDone(true);
        task.setColor("#cccccc");
        task.setUserid(ShiroUtil.getCurrentUserId());
        taskMapper.updateTask(task);
        Map<String,Object> map = Maps.newHashMap();
        map.put("id",id);
        return queryTaskByParams(map).get(0);

    }


    /**
     * 查找事项
     */
    public List<Task> queryTaskByParams(Map<String,Object> map){

        map.put("userid",ShiroUtil.getCurrentUserId());
        return taskMapper.queryTaskByParams(map);
    }

    public List<Task> findTimeOutTask() {
        Map<String,Object> map = Maps.newHashMap();
        map.put("userid",ShiroUtil.getCurrentUserId());
        map.put("done",false);
        map.put("end", SmallUtils.getTime());
        return taskMapper.queryOutTimeTask(map);
    }

    public List<Task> querySaleRecordTask(Integer id) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("saleid",id);
        return queryTaskByParams(map);
    }

    public List<Task> queryCustomTask(Integer id) {
        Map<String,Object> map = Maps.newHashMap();
        map.put("customid",id);
        return queryTaskByParams(map);
    }





}

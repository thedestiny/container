package com.it.controller;

/**
 * Created by xieyue on 2016/7/11.
 * NoticeController
 */


import com.google.common.collect.Maps;
import com.it.dto.DataTablesResult;
import com.it.pojo.Notice;
import com.it.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    Logger logger = LoggerFactory.getLogger(NoticeController.class);

    @Inject
    private NoticeService noticeService;

    @RequestMapping(method = RequestMethod.GET)
    public String getNoticePage(){
        return "notice/list";
    }

    @RequestMapping(value = "/new",method = RequestMethod.GET)
    public String addNoticePage(){
        return "notice/new";
    }

    @RequestMapping(value = "/new",method = RequestMethod.POST)
    @ResponseBody
    public String addNoticePage(Notice notice){
        Integer n = noticeService.addNewNotice(notice);
        return n == 1 ? "success":"failure";
    }

    @RequestMapping(value = "/load",method = RequestMethod.GET)
    @ResponseBody
    public DataTablesResult<Notice> getNoticeList(HttpServletRequest request){

        String draw = request.getParameter("draw");
        String start = request.getParameter("start"); //当前页偏移量
        String length = request.getParameter("length"); //每页显示多少条数据

        String keyword = request.getParameter("keyword");

        String sortColumnIndex = request.getParameter("order[0][column]"); //获取排序列的索引
        String sortColumnName = request.getParameter("columns[" + sortColumnIndex + "][name]"); //根据排序列的索引获取列的名字
        String sortType = request.getParameter("order[0][dir]");//排序方式 asc | desc

        Map<String,Object> map = Maps.newHashMap();
        map.put("start",start);
        map.put("length",length);
        map.put("keyword",keyword);

        Long total = noticeService.queryNoticeTotal();
        Long filter = noticeService.queryNoticeNumByParams(map);
        List<Notice> noticeList = noticeService.findNoticeByParams(map);

        return new DataTablesResult<>(draw,noticeList,total,filter);
    }

    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
    public String showNoticeContent(@PathVariable Integer id,Model model){
        Notice notice = noticeService.findNoticeById(id);
        model.addAttribute("notice",notice);
        return "notice/show";
    }



    @RequestMapping(value = "/image/upload",method = RequestMethod.POST)
    public Map<String,Object> uploadImage(MultipartFile file ){

        Map<String,Object> result = Maps.newHashMap();

        if(!file.isEmpty()){
            String path = noticeService.saveImage(file);
            result.put("success",true);
            result.put("file_path",path);
        } else{
            result.put("success",false);
            result.put("msg","请选择文件");
        }
        return  result;

    }





}

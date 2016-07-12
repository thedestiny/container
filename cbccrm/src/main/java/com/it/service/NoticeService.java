package com.it.service;

/**
 * Created by xieyue on 2016/7/11.
 */


import com.google.common.collect.Maps;
import com.it.mapper.NoticeMapper;
import com.it.pojo.Notice;
import com.it.utils.ShiroUtil;
import com.it.utils.SmallUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Named
public class NoticeService {
    Logger logger = LoggerFactory.getLogger(NoticeService.class);

    @Inject
    private NoticeMapper noticeMapper;
    @Value("${file.imagepath}")
    protected String imageSavePath;


    /**
     * 添加新的公告
     *
     * @param notice notice 对象
     * @return 影响行数
     */
    public Integer addNewNotice(Notice notice) {
        notice.setPublisher(ShiroUtil.getCurrentRealname());
        notice.setUserid(ShiroUtil.getCurrentUserId());
        notice.setPublishtime(SmallUtils.getTime());
        return noticeMapper.addNotice(notice);
    }

    /**
     * 根据参数查询Notice信息
     *
     * @param map 参数的map集合 发布者，时间，标题的关键词，排序，分页参数
     * @return List<Notice>
     */
    public List<Notice> findNoticeByParams(Map<String, Object> map) {
        return noticeMapper.queryNotice(map);
    }

    /**
     * 根据参数查询Notice条数
     *
     * @param map 参数的map集合 发布者，时间，标题的关键词
     * @return 过滤后的记录数
     */
    public Long queryNoticeNumByParams(Map<String, Object> map) {
        return noticeMapper.queryNoticeNum(map);
    }

    /**
     * 查询公告的总条数
     *
     * @return 返回公告的总条数
     */
    public Long queryNoticeTotal() {
        return queryNoticeNumByParams(new HashMap<String, Object>());
    }


    public Notice findNoticeById(Integer id) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("id", id);
        return findNoticeByParams(map).get(0);
    }

    /**
     * 将在线编辑器中的文件进行保存
     *
     * @param file 传入文件对象
     * @return 返回保存路径
     */
    public String saveImage(MultipartFile file) throws IOException {
        // 获取文件的输入流和文件原始名

        InputStream inputStream = file.getInputStream();
        String orginalName = file.getOriginalFilename();
        // 获取文件后缀名
        String extName = "." + orginalName.split(".")[1];
        String newFileName = UUID.randomUUID().toString();
        FileOutputStream outputStream = new FileOutputStream(new File(imageSavePath, newFileName));
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();

        return "preview/"+newFileName;
    }
}

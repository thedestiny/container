package com.it.controller;

/**
 * Created by xieyue on 2016/7/12.
 * FilePreviewController
 */


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
public class FilePreviewController {

    Logger logger = LoggerFactory.getLogger(FilePreviewController.class);

    @Value("${file.imagepath}")
    private String filepath;

    @RequestMapping("/preview/{filename}")
    public void previewFile(@PathVariable String filename, HttpServletResponse response)
            throws IOException {

        File file = new File(filepath,filename);
        if(!file.exists()){
            throw new RuntimeException("文件不存在");
        }

        FileInputStream inputStream = new FileInputStream(file);
        OutputStream outputStream = response.getOutputStream();
        IOUtils.copy(inputStream,outputStream);

        outputStream.flush();
        outputStream.close();
        inputStream.close();


    }



}

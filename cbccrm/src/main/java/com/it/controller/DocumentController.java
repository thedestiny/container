package com.it.controller;

/**
 * Created by xieyue on 2016/7/12.
 * DocumentController
 */


import com.google.common.collect.Maps;
import com.it.dto.JSONResult;
import com.it.pojo.Document;
import com.it.service.DocumentService;
import com.it.utils.SmallUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.inject.Inject;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/document")
public class DocumentController {
    Logger logger = LoggerFactory.getLogger(DocumentController.class);

    @Inject
    private DocumentService documentService;

    @Value("${file.documentpath}")
    private String filepath;

    @RequestMapping(method = RequestMethod.GET)
    public String getDocumentPage(Model model, @RequestParam(required = false, defaultValue = "0") Integer faid) {

        Map<String, Object> map = Maps.newHashMap();
        List<Document> crumbList = null;
        map.put("faid", faid);
        List<Document> documentList = documentService.findDocumentByParams(map);
        if(faid > 0) {
            crumbList = documentService.breadCrumb(faid);
        }

        model.addAttribute("crumbList", crumbList);
        model.addAttribute("documentList", documentList);
        model.addAttribute("faid", faid);
        return "/document/doclist";
    }

//    @RequestMapping(value = "/{id:\\d+}",method = RequestMethod.GET)
//    @ResponseBody
//    public JSONResult getDocumentPage(@PathVariable Integer id){
//        id = id == null ? 0: id;
//        Map<String,Object> map = Maps.newHashMap();
//        map.put("faid",id);
//        List<Document> documentList = documentService.findDocumentByParams(map);
//        if(documentList.size() == 0){
//            return new JSONResult(documentList);
//        }else{
//            return new JSONResult("没有找到相关文件");
//        }
//    }

    @RequestMapping(value = "/adddoc", method = RequestMethod.POST)
    @ResponseBody
    public String createNewDocument(String docname, Integer faid) {
        logger.debug("faid is {}",faid);
        logger.debug("==========================");
        faid = faid == null ? 0 : faid;
        Integer n = documentService.createDocument(docname, faid);
        return n == 1 ? "success" : "failure";
    }


    @RequestMapping(value = "/file/upload", method = RequestMethod.POST)
    @ResponseBody
    public String fileUpload(MultipartFile file, Integer faid) {
        if (file.isEmpty()) {
            throw new RuntimeException("文件为空");
        } else {
            try {
                logger.debug("faid is {}",faid);
                documentService.uploadFile(file, faid);
            } catch (IOException e) {
                throw new RuntimeException("文件保存失败");
            }
        }
        return "success";
    }

    @RequestMapping(value = "/down/{id}", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable Integer id) throws FileNotFoundException {
        Document document = documentService.findDocumentById(id);

        if (document == null) {
            throw new RuntimeException("文件请假回家了。。。");
        }
        File file = new File(filepath, document.getSavename());
        if (!file.exists()) {
            throw new RuntimeException("文件回火星了。。");
        }
        FileInputStream inputStream = new FileInputStream(file);
        String filename = document.getFilename();
        filename = SmallUtils.transtoISO(filename);

        return ResponseEntity
                .ok()
                .contentType(MediaType.parseMediaType(document.getContexttype()))
                .contentLength(document.getFilesize())
                .header("Content-Disposition", "attachment;filename=\"" + filename + "\"")
                .body(new InputStreamResource(inputStream));
    }


}

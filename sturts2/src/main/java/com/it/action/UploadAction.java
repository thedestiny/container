package com.it.action;


import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class UploadAction extends BaseAction {

    Logger logger = LoggerFactory.getLogger(UploadAction.class);

    private String name;
    private List<File> files;
    private List<String> filesFileName;
    private List<String> filesContentType;


    private Long downloadFileSize;
    private String downloadType;
    private String donwloadFileName;

    public String download() {
        File file = new File("G:/1.png");

        downloadType = "image/png";
        downloadFileSize = file.length();
        donwloadFileName = "1.png";

        return SUCCESS;
    }

    public InputStream getInputStream() throws IOException {
        return new FileInputStream(new File("G:/1.png"));
    }

    @Override
    public String execute() throws Exception {
        System.out.println("execute!");
        return SUCCESS;
    }

    public String upload() throws IOException {

        for (int i = 0, len = files.size(); i < len; i++) {
            logger.debug("name is {}", name);
            logger.debug("file is {}", files.get(i).getName());
            logger.debug("filename is {}", filesFileName.get(i));
            logger.debug("filetype is {}", filesContentType.get(i));

            FileInputStream fileInputStream = new FileInputStream(files.get(i));

            FileOutputStream fileOutputStream = new FileOutputStream("G:/" + filesFileName.get(i));

            IOUtils.copy(fileInputStream, fileOutputStream);

            fileOutputStream.flush();
            fileOutputStream.close();
            fileInputStream.close();

        }

        return SUCCESS;

    }


    public Long getDownloadFileSize() {
        return downloadFileSize;
    }

    public void setDownloadFileSize(Long downloadFileSize) {
        this.downloadFileSize = downloadFileSize;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }

    public String getDonwloadFileName() {
        return donwloadFileName;
    }

    public void setDonwloadFileName(String donwloadFileName) {
        this.donwloadFileName = donwloadFileName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public List<String> getFilesFileName() {
        return filesFileName;
    }

    public void setFilesFileName(List<String> filesFileName) {
        this.filesFileName = filesFileName;
    }

    public List<String> getFilesContentType() {
        return filesContentType;
    }

    public void setFilesContentType(List<String> filesContentType) {
        this.filesContentType = filesContentType;
    }
}

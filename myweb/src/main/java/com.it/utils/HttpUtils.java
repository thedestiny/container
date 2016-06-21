package com.it.utils;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by xieyue on 2016/6/21.
 * HttpUtils
 */
public class HttpUtils {

    public static String getText(String url) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            int httpCode = response.getStatusLine().getStatusCode();
            if (httpCode == 200) {
                InputStream inputStream = response.getEntity().getContent();
                return IOUtils.toString(inputStream);
            } else {
                throw new RuntimeException("服务器在忙" + httpCode);
            }
        } catch (IOException e) {
            throw new RuntimeException("close encounter exception", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static void getRequestStream(String url,String path) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(url);
            HttpResponse response = httpClient.execute(httpGet);
            int httpCode = response.getStatusLine().getStatusCode();
            if (httpCode == 200) {
                InputStream inputStream = response.getEntity().getContent();
                FileOutputStream  fileOutputStream = new FileOutputStream(path);
                IOUtils.copy(inputStream,fileOutputStream);
                inputStream.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            } else {
                throw new RuntimeException("服务器在忙" + httpCode);
            }
        } catch (IOException e) {
            throw new RuntimeException("close encounter exception", e);
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

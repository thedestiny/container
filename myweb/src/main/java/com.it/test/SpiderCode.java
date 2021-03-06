package com.it.test;

import com.it.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by xieyue on 2016/6/21.
 * SpiderCode
 */
public class SpiderCode {

    @Test
    public void downLoadImage() throws Exception {
        int page = 1;
        while (page++ < 10) {
            Document document = Jsoup.connect("http://www.topit.me/pop?p=" + page).cookie("is_click", "1").get();
            Elements elements = document.select("#content .catalog .e>a");
            int i = 2;
            for (Element element : elements) {
                String href = element.attr("href");
                //System.out.println("href is : " + href);
                Document document1 = Jsoup.connect(href).cookie("is_click", "1").get();
                Element img = document1.select("#content>a").first();
                // System.out.println(document1);
                //System.out.println(img);
                String imgsrc = img.attr("href");
                Element save = document1.select("#sidebar .hearters>p>a").first();
                String num = save.html();
                num = num.substring(num.lastIndexOf("(") + 1);
                num = num.substring(0, num.length() - 1);
                // System.out.println("num is :" + num);
//            String regex = "\\d+";
//            Pattern pattern = Pattern.compile(regex);
//            Matcher matcher = pattern.matcher(num);
//            int n = 0;
//            if (matcher.find()) {
//                n = Integer.parseInt(matcher.group(0));
//            }
                //System.out.println("图片地址 ：" + imgsrc);
                if (Integer.parseInt(num) > 10) {
                    System.out.println("downloading.....");
                    HttpUtils.getRequestStream(imgsrc, "G:/images/" + imgsrc.substring(imgsrc.lastIndexOf("/") + 1));
                }
                Thread.sleep(2000);
            }
        }

    }

    @Test
    public void selectImage() throws Exception {

        File filedoc = new File("G:/images");
        File[] files = filedoc.listFiles();
        if (files == null) {
            return;
        }
        for (File file : files) {
            String name = file.getName();
            FileInputStream fileInputStream = new FileInputStream(file);
            int size = fileInputStream.available();
            fileInputStream.close();
            System.out.println(size);
            if (size < 1024 * 100) {
                if (file.isFile()) {
                    System.out.println(file);
                    if (file.delete()) {
                        System.out.println(name + " is delete success!");
                    } else {
                        System.out.println(name + " is delete failure!");
                    }
                }
            }
        }
    }
}

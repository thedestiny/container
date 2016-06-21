package com.it.ajax;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;


/**
 * Created by xieyue on 2016/6/21.
 * ReptitleCode
 */
public class ReptitleCode {

    @Test
    public  void downLoadImage() throws Exception {

        Document document = Jsoup.connect("http://www.topit.me/pop?p=1").cookie("is_click", "1").get();
        Elements elements = document.select("#content .catalog .e>a");
        int i = 0;
        for(Element element : elements){
            String href = element.attr("href");
            System.out.println("href is : " + href);
            Document document1 = Jsoup.connect(href).cookie("is_click", "1").get();
            Element img = document1.select("#content>a").first();
            System.out.println(img);
            String imgsrc = img.attr("href");
            Element save = document1.select("#siderbar").first();
            System.out.println(save);
            // String num = save.html();
            // System.out.println("收藏数 ：" + num);
            Thread.sleep(2000);
            if(i-- < 0){
                break;
            }
        }

    }


}

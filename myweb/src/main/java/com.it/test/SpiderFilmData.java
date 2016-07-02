package com.it.test;

import com.it.dao.FilmDao;
import com.it.entity.Film;
import com.it.utils.HttpUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by xieyue on 2016/6/22.
 * SpiderFilmData
 */
public class SpiderFilmData {

    @Test
    public void copyData() throws IOException {
        // .cookie("is_click", "1")  .list-wp .item .cookie("ap","1").cookie("bid","sbQ2iTJmGVY").cookie("II", "118241")
        Document document = Jsoup.connect("https://movie.douban.com/annual2015").cookie("ap","1").cookie("bid","sbQ2iTJmGVY").cookie("II", "118241").get();
        Elements elements = document.select(".subjects-wrapper>a");
        int n = 0;
        for(Element element :elements){
            String href = element.attr("href");
            Document document1 = Jsoup.connect(href).cookie("bid","sbQ2iTJmGVY").cookie("II", "118241").get();
            Elements elements1 =document1.select("h1");
            String flim = elements1.first().html();
            String screen = elements1.last().html().replace("(","").replace(")","");
            // 电影详情
            Elements element1 = document1.select("#info");
            System.out.println("flim is " + flim + " : " + screen);
            System.out.println(element1.toString());
            // 评分
            String rate = document1.select(".rating_self>strong").html();
            if(n++ >6){
                break;
            }



        }


    }

}

package com.linlin.esjd.utils;


import com.linlin.esjd.pojo.Content;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
//解析网页工具类
public class HtmlParseUtil {

/*    public static void main(String[] args) throws Exception {
        new HtmlParseUtil().parseJD("java").forEach(System.out::println);
    }*/

    public List<Content> parseJD(String keywords) throws Exception{
        //获取请求
        //String url="https://search.jd.com/Search?keyword=java&enc=utf-8&wq=java&pvid=947dfa8e9a9843519b21465c22dab73a";
        String url="https://search.jd.com/Search?keyword="+keywords+"&enc=utf-8&wq="+keywords+"&pvid=947dfa8e9a9843519b21465c22dab73a";
        //解析网页 document就是浏览器document对象
        Document document= Jsoup.parse(new URL(url),30000);
        Element element=document.getElementById("J_goodsList");
        //System.out.println(element.html());
        // 获取所有li元素
        Elements elements = element.getElementsByTag("li");

        ArrayList<Content> goodsList = new ArrayList<>();

        //获取元素所有内容
        for(Element el:elements){
            String img = el.getElementsByTag("img").eq(0).attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
/*            System.out.println("=========");
            System.out.println(img);
            System.out.println(price);
            System.out.println(title);*/
            Content content = new Content();
            content.setTitle(title);
            content.setPrice(price);
            content.setImg(img);

            goodsList.add(content);

        }
        return goodsList;
    }

}

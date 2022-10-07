package com.mp.json;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

//jsoup 是一款Java 的HTML解析器，可直接解析某个URL地址、HTML文本内容
public class JsoupUtil {


    public static void main(String[] args) {

        Document d1;
        Document d2;

        try {
            //通过Jsoup类中的静态方法connect返回Document对象，该document对象实际为整个html页面内容。
            /**
             * Jsoup.connect方法 返回一个org.jsoup.Connection对象。
             * 在Connection对象中，我们可以执行get或者post来执行请求
             * 在执行请求之前，我们可以使用Connection对象来设置一些请求信息。比如：头信息，cookie，请求等待时间，代理等等来模拟浏览器的行为。
             */

            d1 = Jsoup.connect("https://maoyan.com/films")
                    .header("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36")
                    .get();
            System.out.println(d1);

            /**
             * 1. 分析网页结构，发现我们想要的国内电影下载排行榜，所对应的class样式为co_content2，
             * 2. 可以通过属性、后代选择器选中元素" div[class=‘co_content2’] ul a" ，然
             * 3. 后通过Element类中的text()方法获取文本内容
             */
            Elements es = d1.select("div[class='user-info']");

            //遍历得到的结果并输出内容
            for (Element e : es) {
                System.out.println(e.text());
            }

            //从文件加载文档，使用`Jsoup.parse()`方法从文件加载HTML
            //d2 = Jsoup.parse(new File("/home/a.html"), "utf-8");

            //links包含了页面所有的连接
            Elements links = d1.select("a[href]");
            for (Element link : links) {
                System.out.println("text : " + link.text()+"---》link : " + link.attr("href"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}




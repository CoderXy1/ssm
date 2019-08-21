package com.resource.jsoup;

import com.resource.model.Movie;
import com.resource.model.Pic;
import com.sun.prism.shader.Solid_TextureYV12_AlphaTest_Loader;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class picJsoup {

    public static void main(String args[]) {

        picJsoup.getText(110000);

    }

    public static List<Pic> getText(int page) {

        Document doc = null;
        List<Pic> list = new ArrayList<>();

        for (int i = 0; i < 10; i++) {

            try {
                String url = "http://www.win4000.com/wallpaper_detail_" + page + ".html";
                Connection conn = Jsoup.connect(url).timeout(5000);
                conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                conn.header("Accept-Encoding", "gzip, deflate, sdch");
                conn.header("Accept-Language", "zh-CN,zh;q=0.8");
                conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                doc = conn.get();
                //Thread.sleep(1000);

                if (doc != null) {
                    Movie movie = new Movie();

                    List<String> list_info = new ArrayList<>();

                    Elements picName = doc.getElementsByClass("ptitle").select("h1");
                    Elements picInfo = doc.getElementsByClass("Bigimg_style");
                    Elements picSize = doc.getElementsByClass("size").select("em");
                    Elements picLabel = doc.getElementsByClass("label").select("a");
                    Elements picDescribe = doc.getElementsByClass("npaper_jj").select("p");
                    Elements picPath = doc.getElementsByClass("pic-large");
                    Elements picDate = doc.getElementsByClass("time");

                    Pic pic = new Pic();
                    pic.setPicName(picName.text());
                    pic.setPicSize(picSize.text());
                    pic.setPicType(picLabel.text());
                    pic.setPicDescribe(picDescribe.text());
                    pic.setPicPath(picPath.attr("src"));

                    System.out.println("success" + page);

                    list.add(pic);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                page++;
            }

        }

        return list;

    }

}

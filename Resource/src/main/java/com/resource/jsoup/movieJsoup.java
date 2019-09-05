package com.resource.jsoup;
/**
 * 80S网站
 **/

import com.resource.model.Movie;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.awt.print.Book;
import java.io.IOException;
import java.util.*;

public class movieJsoup {

    public static void main(String args[]) {

        //System.out.println(movieJsoup.getText(21062).size());

    }

    public static List<Movie> getText(int page) {

        Document doc = null;
        List<Movie> list = new ArrayList<Movie>();

        for (int i = 0; i < 20; i++) {

            try {
                String url = "https://www.80ying.com/movie/" + page;
                Connection conn = Jsoup.connect(url).timeout(5000);
                conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                conn.header("Accept-Encoding", "gzip, deflate, sdch");
                conn.header("Accept-Language", "zh-CN,zh;q=0.8");
                conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                doc = conn.get();
                Thread.sleep(1000);

                if (doc != null) {
                    Movie movie = new Movie();

                    List<String> list_info = new ArrayList<>();

                    Element div_info = doc.getElementById("minfo");
                    Element movieDescribe = div_info.getElementById("movie_content");
                    Elements movieName = div_info.select("h1");
                    Elements moviePic = div_info.select("img[src$=.jpg]");
                    Elements movieScore = div_info.getElementsByClass("span_block");
                    Element path = doc.getElementById("cpdl2list");
                    Elements moviePath = path.select("a[rel$=nofollow]");

                    for (Element info : movieScore) {
                        //System.out.println(info.text());
                        String unUseInfo = info.text().split("：")[0] + "：";
                        list_info.add(info.text().replaceAll(unUseInfo, ""));
                    }
                    int size = list_info.size();

                    movie.setMovieName(movieName.text()); //电影名
                    movie.setMovieType(list_info.get(0)); //电影类型
                    movie.setAddress(list_info.get(1)); //电影地区
                    movie.setDirector(list_info.get(size-6)); //电影导演
                    movie.setMovieYear(list_info.get(size-5)); //电影年份
                    movie.setScore(list_info.get(size-2)); //电影评分
                    movie.setDownloadPath(moviePath.attr("href")); //电影下载路径
                    movie.setPicPath(moviePic.attr("src")); //电影海报地址
                    movie.setMovieDescribe(movieDescribe.text()); //电影描述

                    list.add(movie);

                    System.out.println("success" + i);

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

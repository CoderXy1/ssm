package com.resource.jsoup;

import com.resource.model.Book;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class bookJsoup {

    public static void main(String args[]) {

        bookJsoup.getText();

    }

    public static List<Map<String, Object>> splitString(String[] booksInfo) {

        List<Map<String, Object>> list_map = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        for (String book : booksInfo) {
            book = book.trim();
            if (book.indexOf("出版") != -1) {
                map.put("publishing", book);
            } else if (book.indexOf("0元") != -1) {
                book = book.replaceAll("[^\\d.]+", "");
                map.put("price", Float.valueOf(book));
            }
        }
        list_map.add(map);
        return list_map;
    }


    public static List<Book> getText() {

        List<Book> list = new ArrayList<Book>();
        Document doc = null;
        String bookName = "", bookPic = "", bookContext = "";

        for (int j = 1000; j < 2000; j = j + 20) {

            String url = "https://book.douban.com/tag/小说?start=" + j + "&type=T";

            try {
                Connection conn = Jsoup.connect(url).timeout(5000);
                conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                conn.header("Accept-Encoding", "gzip, deflate, sdch");
                conn.header("Accept-Language", "zh-CN,zh;q=0.8");
                conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                doc = conn.get();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            String title = "", text = "";
            try {
                doc = Jsoup.connect(url).get();
            } catch (Exception e) {
                e.printStackTrace();
                doc = null;
            }
            if (doc != null) {
                Elements div_info = doc.getElementsByClass("subject-list");
                Elements li = div_info.select("li");

                for (final Element link : li) {

                    Book book = new Book();
                    String booksInfo = link.getElementsByClass("pub").text();
                    String bookScore = link.getElementsByClass("rating_nums").text().trim();
                    String[] a = booksInfo.split("/");
                    List<Map<String, Object>> list_map = splitString(a);
                    book.setBookName(link.select("h2").select("a").text());
                    book.setBookPicPath(link.select("img[src$=.jpg]").attr("src"));
                    Object price = list_map.get(0).get("price");
                    book.setPrice(price == null ? 0.0f : Float.valueOf(price.toString()));
                    Object publishing = list_map.get(0).get("publishing");
                    book.setPublishing(publishing == null ? "" : publishing.toString());
                    book.setAuthor(a[0]);
                    book.setTotalNumber(3);
                    book.setHavingNumber(3);
                    book.setTypeName("文学");
                    if (bookScore == null || bookScore.equals("")) {
                        book.setBookScore(0);
                    } else {
                        book.setBookScore(Math.round(Float.valueOf(bookScore)));
                    }
                    book.setPutInDate(new Date());
                    list.add(book);
                    System.out.println(book.getBookName());

                }
            }
        }

        return list;
    }

}

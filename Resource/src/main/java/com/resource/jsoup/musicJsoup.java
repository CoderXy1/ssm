package com.resource.jsoup;

import com.resource.model.Movie;
import com.resource.model.Music;
import com.resource.model.Pic;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import javax.xml.bind.Element;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class musicJsoup {

    public static void main(String args[]) {

        musicJsoup.getText(0);

    }

    public static List<String> getMatcherSubstrs(String str, String regexStr) {
        Pattern pattern = Pattern.compile(regexStr);

        Matcher matcher = pattern.matcher(str);
        List<String> result = new ArrayList<String>();
        while (matcher.find()) {
            if (matcher.group(1).endsWith(".html") && !result.contains(matcher.group(1)))
                result.add(matcher.group(1));
        }
        return result;
    }

    public static String getURLContent(String urlStr, String charSet) {
        StringBuilder sb = new StringBuilder();
        try {
            URL url = new URL(urlStr);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), Charset.forName(charSet)));
            String temp = "";
            while ((temp = reader.readLine()) != null) {
                sb.append(temp + "\r\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }


    public static List<Music> getText(int num) {

        List<Music> list = new ArrayList<>();
        Document doc = null;
        int page = num / 60 + 1;

        for (int i = page; i < page + 20; i++) {

            try {
                String allStr = getURLContent("http://www.333ttt.com/up/?page=" + i, "utf-8");
                List<String> urls = getMatcherSubstrs(allStr, "<a[^>]*href=\"(?<href>[^\"]*)\"[^>]*>");
                for (String url : urls) {
                    url = "http://www.333ttt.com/" + url;
                    Connection conn = Jsoup.connect(url).timeout(5000);
                    conn.header("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
                    conn.header("Accept-Encoding", "gzip, deflate, sdch");
                    conn.header("Accept-Language", "zh-CN,zh;q=0.8");
                    conn.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.87 Safari/537.36");
                    doc = conn.get();
                    //Thread.sleep(1000);

                    if (doc != null) {

                        Music music = new Music();
                        Elements musicInfo = doc.getElementsByClass("tib");
                        Elements musicPath = doc.getElementsByClass("kuad");
                        Elements musicSize = doc.getElementsByClass("STYLE3");

                        String[] info = musicInfo.text().split("-");
                        music.setSinger(info[0]);
                        music.setMusicName(info[1]);
                        music.setMusicPath(musicPath.text());

                        Pattern pattern = Pattern.compile("([0-9]{1,}[.][0-9]*)MB");
                        Matcher matcher = pattern.matcher(musicSize.text());
                        if (matcher.find()) {
                            music.setMusicSize(matcher.group(0));
                        }

                        System.out.println("success" + list.size());

                        list.add(music);

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

            }

        }

        return list;

    }

}

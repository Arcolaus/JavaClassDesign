package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

import main.lessonparse.Lesson;

import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<Lesson> t = new Vector<>();
        Document doc = null;
        try {
            doc = Jsoup.parse(new File("./img/chk.html"), "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements LessonElements = doc.getElementsByClass("Center");

        for (Element element : LessonElements) {
            if (element.text().length() != 0) {
//                System.setOut(ps);
                System.out.println(element.id() + element.text());
            }
        }
    }
}

/**
 * 2021.9.27
 * 虽然还不怎么会写正式项目里的注释
 * 但是在尽力尝试，一方面代码逻辑并不复杂，对象之间的关系也相对简单，甚至目前没有出现过一次继承或者接口的实现
 * 加油
 */

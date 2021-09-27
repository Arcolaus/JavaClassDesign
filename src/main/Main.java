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
        Vector<Lesson> t = new Vector<Lesson>();
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

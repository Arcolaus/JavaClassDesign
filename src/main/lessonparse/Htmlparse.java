package main.lessonparse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Htmlparse {
    private Elements LessonElements = new Elements();

    Htmlparse(Studentlesson student, String htmlPath) {
        Document originDoc = null;
        try {
            originDoc = Jsoup.parse(new File(htmlPath), "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements id = originDoc.select("td[nowrap=nowrap]");
        for (Element it : id)
            student.studentId = it.text();
//            System.out.println(it.text());

        Elements StudentClass = originDoc.select("td[align=right]");
        for (Element it : StudentClass)
            student.studentClass = it.text();
//            System.out.println(it.text());

        LessonElements = originDoc.getElementsByClass("Center");
        for (Element element : LessonElements) {
//            student.studentLesson.add(new Lessoninfo(element.id(),element.text()));
//            System.out.println(element.id() + "  " + element.text());
        }
    }

    public void lessonAdd(Studentlesson student) {

    }
}

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

import main.lessonparse.Lessoninfo;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<Lessoninfo> t=new Vector<Lessoninfo>();
        Document doc = null;
        try {
            doc = Jsoup.parse(new File("./img/schedule.html"), "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        Elements id = doc.select("td[nowrap=nowrap]");
//        for (Element it : id)
//            System.out.println(it.text());
//        Elements StudentClass = doc.select("td[align=right]");
//        for (Element it : StudentClass)
//            System.out.println(it.text());
//        Lessoninfo x=new Lessoninfo("5-8","<<JAVA语言课程设计>>;1 博知楼506 王靖 1-16周 实验学时");
        Elements LessonElements = doc.getElementsByClass("Center");
        for (Element element : LessonElements) {
            if (element.text().length() != 0) {
//                t.add(new Lessoninfo(element.id(),element.text()));
                System.out.println(element.id() + element.text());
            }
        }
    }
}

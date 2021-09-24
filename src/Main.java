import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import main.lessonparse.Lessoninfo;

import java.io.PrintStream;
import java.util.Vector;

public class Main {
    public static void main(String[] args) {
        Vector<Lessoninfo> t = new Vector<Lessoninfo>();
        Document doc = null;
        try {
            doc = Jsoup.parse(new File("./img/schedule.html"), "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        PrintStream ps = null;
        try {
            ps = new PrintStream("d:/info.txt");
        } catch (FileNotFoundException e) {
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

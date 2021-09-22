package java;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Classhtmlparse {
    Classhtmlparse(String htmlPath) {
        Document doc = null;
        try {
            doc = Jsoup.parse(new File(htmlPath), "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements id = doc.select("td[nowrap=nowrap]");
        for (Element it : id)
            System.out.println(it.text());


        Elements StudentClass = doc.select("td[align=right]");
        for (Element it : StudentClass)
            System.out.println(it.text());


        Elements LessonElements = doc.getElementsByClass("Center");
        for (Element element : LessonElements) {
            System.out.println(element.id() + "  " + element.text());
        }
    }
}

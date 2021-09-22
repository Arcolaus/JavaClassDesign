import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class jartest {
    public static void main(String[] args) {
        Document doc = null;
        try {
            doc = Jsoup.parse(new File("img/schedule.html"), "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }


        Elements classElment = doc.getElementsByClass("Center");
        for (Element it : classElment) {
            if(it.text().length()!=0)
                System.out.println(it.text());
        }
    }
}
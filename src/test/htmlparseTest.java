package test;

import main.lessonparse.Lesson;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class htmlparseTest {
    public static void main(String[] args) {
//        htmlparseTest t = new htmlparseTest("./img/chk.html");
//        t.stringSplit();
//        for (Element element : t.LessonElements) {
//            System.out.println(element.id() + element.text());
//        }
//        System.out.println("chk1");
//        ArrayList<Lesson> tt = t.getLessons();
//        System.out.println(t.Lessons.size());
//        for (Lesson it : tt) {
//            System.out.println(it.getLessonName());
//            System.out.println("星期："+it.getBaseTime());
//            System.out.println("小节时间："+it.getCombineTime());
//            System.out.println("课序号："+it.getLessonSerial());
//            System.out.println(it.getRoomPlace());
//            System.out.println(it.getTeacher());
//            for(Integer iter:it.takeWeek){
//                System.out.print(iter+" ");
//            }
//            System.out.println("\n==================\n");
//        }
    }

    String studentId;
    String studentClass;
    String pattern = "<<[^<<]*";
    Pattern regex = Pattern.compile(pattern);

    static ArrayList<Lesson> Lessons = new ArrayList<Lesson>();

    Elements LessonElements;

    htmlparseTest(String htmlPath) {
        Document originDoc = null;
        LessonElements = new Elements();
        try {
            originDoc = Jsoup.parse(new File(htmlPath), "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements id = originDoc.select("td[nowrap=nowrap]");
        for (Element it : id)
            this.studentId = it.text();

        Elements StudentClass = originDoc.select("td[align=right]");
        for (Element it : StudentClass)
            this.studentClass = it.text();

        LessonElements = originDoc.getElementsByClass("Center");
    }

    void stringSplit() {
        for (Element element : LessonElements) {
            if (element.text().length() != 0) {
                String time = element.id();
                ArrayList<String> lessons = new ArrayList<String>();
                Matcher matcher = regex.matcher(element.text());

            /*
            将类似于
            "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 9 实验学时 <<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 10 实验学时"
            这样的字符串分割成
            "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 9 实验学时"
            "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 10 实验学时"
            这样的形式，并用lessons存储起来
            每个的时间是一样的
             */
                int splitPosition = 0;
                while (matcher.find()) {
                    String singlelesson = element.text().substring(splitPosition, matcher.end());
                    splitPosition = matcher.end();
                    lessons.add(singlelesson);
                }
//                System.out.println(lessons.size());
                for (String StringIter : lessons)
                    Lessons.add(new Lesson(time, StringIter));
            }
        }
    }

    ArrayList<Lesson> getLessons() {
        return Lessons;
    }
}

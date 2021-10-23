package main.lessonparse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * 大致逻辑：
 * HtmlParse类用于解析一个html文档
 * 用getLessons()函数返回一个ArrayList<Lesson>，包含html中的所有课程
 * 接着可以用Student类的addLesson()函数，向一个Student类添加课程
 * 也就是说，在添加课程这一步中，不会直接调用Lesson类的任何一个函数或成员变量
 */
public class HtmlParse {
    //    此html分析出的课表所属的学生学号和班级
    private String studentId;
    private String studentClass;

    //    用于分割的正则表达式匹配串
    private String pattern = "<<[^<<]*";
    private Pattern regex = Pattern.compile(pattern);

    //    分析出的所有课程
    private ArrayList<Lesson> Lessons = new ArrayList<Lesson>();

    //    获取到的html文档变量
    private Elements LessonElements;

    public HtmlParse(String htmlPath) {
        Document originDoc = null;
        LessonElements = new Elements();
        try {
            originDoc = Jsoup.parse(new File(htmlPath), "GBK");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements id = originDoc.select("td[nowrap=nowrap]");
        for (Element it : id) {
            String p = ": [0-9]*";
            Pattern r = Pattern.compile(p);
            Matcher m = r.matcher(it.text());
            while (m.find()) {
                if (!m.group().equals(" "))
                    this.studentId = m.group().split(" ")[1];
            }
        }
        Elements StudentClass = originDoc.select("td[align=right]");
        for (Element it : StudentClass)
            this.studentClass = it.text();

        LessonElements = originDoc.getElementsByClass("Center");
        for (Element element : LessonElements) {
            String time = element.id();
            ArrayList<String> lessons = new ArrayList<String>();
            Matcher matcher = regex.matcher(element.text());

            /**
             * 将类似于
             * "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 9 实验学时 <<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 10 实验学时"
             * 将这样的字符串分割成
             * "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 9 实验学时"
             * "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 10 实验学时"
             * 这样的形式，并用lessons存储起来
             **/
            int splitPosition = 0;
            while (matcher.find()) {
                String singlelesson = element.text().substring(splitPosition, matcher.end());
                splitPosition = matcher.end();
                lessons.add(singlelesson);
            }

            for (String StringIter : lessons)
                Lessons.add(new Lesson(time, StringIter));
        }
    }

    // 备用
//     public void stringSplit() {
//         for (Element element : LessonElements) {
//             String time = element.id();
//             ArrayList<String> lessons = new ArrayList<String>();
//             Matcher matcher = regex.matcher(element.text());
//
//
// 将类似于
// "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 9 实验学时 <<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 10 实验学时"
// 将这样的字符串分割成
// "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 9 实验学时"
// "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 10 实验学时"
// 这样的形式，并用lessons存储起来
//
//             int splitPosition = 0;
//             while (matcher.find()) {
//                 String singlelesson = element.text().substring(splitPosition, matcher.end());
//                 splitPosition = matcher.end();
//                 lessons.add(singlelesson);
//             }
//
//             for (String StringIter : lessons)
//                 Lessons.add(new Lesson(time, StringIter));
//         }
//     }
    public ArrayList<Lesson> getLessons() {
        return Lessons;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentClass() {
        return this.studentClass;
    }
}

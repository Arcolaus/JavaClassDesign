package main;

import main.lessonparse.HtmlParse;
import main.lessonparse.Lesson;
import main.lessonparse.Student;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        HtmlParse doc = new HtmlParse("src/img/chk.html");
        Student st = new Student(doc.getStudentId(), doc.getStudentClass());
        st.addLesson(doc.getLessons());

        ArrayList<Lesson> ak = st.getStudentLessons();

        for (Lesson it : ak) {
            System.out.println("+++++++++");
            System.out.println(it.getLessonName());
            System.out.println(it.getRoomPlace());
            System.out.println(it.getTeacher());
            System.out.println("");
        }
    }
}
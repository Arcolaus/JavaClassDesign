package main.lessonparse;

import java.util.ArrayList;
import java.util.Iterator;

public class Student {
    String studentId;
    String studentClass;
    ArrayList<Lesson> studentLessons;

    public Student() {
        studentLessons = new ArrayList<>();
    }

    public Student(String studentid, String studentclass) {
        studentLessons = new ArrayList<>();
        this.studentId = studentid;
        this.studentClass = studentclass;
    }

    //    重载向学生类添加课程的函数
    public void addLesson(Lesson addingLessons) {
        studentLessons.add(addingLessons);
    }

    public void addLesson(ArrayList<Lesson> addingLessons) {
        for (Lesson iter : addingLessons)
            this.studentLessons.add(iter);
    }

    public void removeLessonByName(String lessonName) {
        Iterator<Lesson> lessonIterator = studentLessons.iterator();
        while (lessonIterator.hasNext()) {
            Lesson singleLesson = lessonIterator.next();
            if (singleLesson.getLessonName().equals(lessonName))
                lessonIterator.remove();
        }
    }

    public ArrayList<Lesson> getStudentLessons() {
        return studentLessons;
    }
}

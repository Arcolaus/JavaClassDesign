package main.lessonparse;

import java.util.ArrayList;

public class Student {
    String studentId;
    String studentClass;
    ArrayList<Lesson> studentLessons;

    public Student() {
    }

    public Student(String studentid, String studentclass) {
        this.studentId = studentid;
        this.studentClass = studentclass;
    }

    void addLesson(Lesson addingLessons) {
        studentLessons.add(addingLessons);
    }

    void addLesson(ArrayList<Lesson> addingLessons) {
        for (Lesson iter : addingLessons)
            this.studentLessons.add(iter);
    }
}

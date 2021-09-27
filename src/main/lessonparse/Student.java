package main.lessonparse;

import java.util.ArrayList;

public class Student {
    String studentId;
    String studentClass;
    ArrayList<Lesson> studentLesson;

    public Student() {
    }

    public Student(String studentid, String studentclass) {
        this.studentId = studentid;
        this.studentClass = studentclass;
    }

    void addLesson(Lesson addingclesson) {
        studentLesson.add(addingclesson);
    }
}

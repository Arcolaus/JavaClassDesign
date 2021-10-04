package main.lessonparse;

import java.util.ArrayList;

public class Student {
    String studentId;
    String studentClass;
    ArrayList<Lesson> studentLessons;

    public Student() {
        studentLessons=new ArrayList<>();
    }

    public Student(String studentid, String studentclass) {
        studentLessons=new ArrayList<>();
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

    public ArrayList<Lesson> getStudentLessons(){
        return studentLessons;
    }
}

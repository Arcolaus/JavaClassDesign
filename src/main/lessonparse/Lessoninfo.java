package main.lessonparse;

import java.util.Vector;

public class Lessoninfo {
    private String lessonName;
    private int timeBase;
    private int timeCombine;
    private int serial;
    private String roomPlace="@";
    private String teacher;

    // 课程的周数有两种显示方式：
    // 1. x-xx周
    // 2. x,x,x,x
    private Vector takeWeek;

    public Lessoninfo(String time, String otherInfo) {
    }

}

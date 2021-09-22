package java.lessonparse;

import java.util.Vector;

public class Lessoninfo {
    private String lessonName;
    private String timeBase;
    private String timeCombine;
    private int serial;
    private String roomPlace;
    private String teacher;

    // 课程的周数有两种显示方式：
    // 1. x-xx周
    // 2. x,x,x,x
    private Vector takeWeek;

}

package main.lessonparse;

import java.util.Vector;

public class Lessoninfo {
    private String lessonName;
    private int baseTime;
    private int combineTime;
    private int lessonSerial;
    private String roomPlace = "@";
    private String teacher;
    // <<大学物理实验II-1>>;2 博逸楼304 索标,孙光辉,程茸,韩会景,徐洪浩,李世霞 2-8 实验学时 <<面向对象程序设计方法>>;3 博逸楼516 李英 9-17 实验学时

    // 课程的周数有两种显示方式：
    // 1. x-xx周
    // 2. x,x,x,x
    private Vector takeWeek;

    public Lessoninfo(String time, String otherInfo) {

        takeWeek = new Vector();
        String[] timeinfo = time.split("-");
        String[] lessoninfo = otherInfo.split(";| ");
        // TODO lessonName
        lessoninfo[0] = lessoninfo[0].replace("<<", "");
        lessoninfo[0] = lessoninfo[0].replace(">>", "");

        this.lessonName = lessoninfo[0];

        // TODO baseTime
        this.baseTime = Integer.parseInt(timeinfo[0]);

        // TODO combineTime
        this.combineTime = Integer.parseInt(timeinfo[1]);

        // TODO lessonSerial
        this.lessonSerial = Integer.parseInt(lessoninfo[1]);

        // TODO roomPlace
        this.roomPlace += lessoninfo[2];

        // TODO teacher
        this.teacher = lessoninfo[3];

        if (lessoninfo[4].contains("双周")) {
            String[] week = lessoninfo[4].split("-|双周");
            for (int i = Integer.parseInt(week[0]); i <= Integer.parseInt(week[1]); i++)
                takeWeek.add(i);
        } else {
            String[] week = lessoninfo[4].split(",");
            for (int i = 0; i < week.length; i++)
                takeWeek.add(Integer.parseInt(week[i]));
        }
    }
}

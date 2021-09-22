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
        takeWeek = new Vector();
        String[] timeinfo=time.split("-");
        String[] lessoninfo = otherInfo.split(";| ");

        // name
        lessoninfo[0]=lessoninfo[0].replace("<<","");
        lessoninfo[0]=lessoninfo[0].replace(">>","");
        this.lessonName=lessoninfo[0];

        // time
        this.timeBase=Integer.parseInt(timeinfo[0]);
        this.timeCombine=Integer.parseInt(timeinfo[1]);

        // serial
        this.serial=Integer.parseInt(lessoninfo[1]);

        // room palce
        this.roomPlace+=lessoninfo[2];

        // teacher
        this.teacher=lessoninfo[3];

        // take week
        if(lessoninfo[4].contains("双周")){
            String[] week = lessoninfo[4].split("-|双周");
            for (int i = Integer.parseInt(week[0]); i <= Integer.parseInt(week[1]); i++)
                takeWeek.add(i);
        }else{
            String[] week=lessoninfo[4].split(",");
            for(int i=0;i<week.length;i++)
                takeWeek.add(Integer.parseInt(week[i]));
        }
    }

    // TODO 1.课程有冲突 2.某些课程有理论课和实验课，但是放在了同一行 3.单双周
    // 课程有冲突

}

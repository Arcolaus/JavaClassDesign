package main.lessonparse;

import java.util.ArrayList;

public class Lesson {
    private String lessonName;
    private int baseTime;
    private int combineTime;
    private int lessonSerial;
    private String roomPlace = "@";
    private String teacher;

    private ArrayList<Integer> takeWeek;

    public Lesson(String time, String otherInfo) {

        takeWeek = new ArrayList<Integer>();
        String[] timeinfo = time.split("-");
        String[] lessoninfo = otherInfo.split(";| ");
        // 课程名
        lessoninfo[0] = lessoninfo[0].replace("<<", "");
        lessoninfo[0] = lessoninfo[0].replace(">>", "");

        this.lessonName = lessoninfo[0].replace(" ","");

        // 星期
        this.baseTime = Integer.parseInt(timeinfo[0]);

        // 小节
        this.combineTime = Integer.parseInt(timeinfo[1]);

        // 课序号
        this.lessonSerial = Integer.parseInt(lessoninfo[1]);

        // 上课教室
        this.roomPlace += lessoninfo[2];

        // 授课老师
        // 比较幸运的是，一开始写的时候没考虑到一门课有多个老师的情况
        // 但是多个老师之间使用的是逗号进行分隔，也使得在解析字符串的时候被划到了一个字符串中
        this.teacher = lessoninfo[3];

        // 上课周数的三种表示
        // 1.单周x-xx
        // 2.双周x-xx
        // 3.x,x,xx,xx,xx
        if (lessoninfo[4].contains("双周")) {
            String[] week = lessoninfo[4].split("双周|-");

            for (int i = Integer.parseInt(week[1]); i <= Integer.parseInt(week[2]); i += 2)
                takeWeek.add(i);

        } else if (lessoninfo[4].contains("单周")) {
            String[] week = lessoninfo[4].split("单周|-");

            for (int i = Integer.parseInt(week[1]); i <= Integer.parseInt(week[2]); i += 2)
                takeWeek.add(i);

        } else {
            if (lessoninfo[4].contains("周")) {
                String[] week = lessoninfo[4].split("-|周");
                for (int i = Integer.parseInt(week[0]); i <= Integer.parseInt(week[1]); i++)
                    takeWeek.add(i);

            } else {
                String[] week = lessoninfo[4].split(",");
                for (int i = 0; i < week.length; i++)
                    takeWeek.add(Integer.parseInt(week[i]));

            }
        }
    }

    public String getLessonName() {
        return this.lessonName;
    }

    public int getBaseTime() {
        return this.baseTime;
    }

    public int getCombineTime() {
        return this.combineTime;
    }

    public int getLessonSerial() {
        return this.lessonSerial;
    }

    public String getRoomPlace() {
        return this.roomPlace;
    }

    public String getTeacher() {
        return this.teacher;
    }

    public ArrayList<Integer> getTakeWeek() {
        return this.takeWeek;
    }
}

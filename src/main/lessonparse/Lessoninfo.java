package main.lessonparse;

import java.util.ArrayList;

public class Lessoninfo {
    String lessonName;
    int baseTime;
    int combineTime;
    int lessonSerial;
    String roomPlace = "@";
    String teacher;

    private ArrayList<Integer> takeWeek;

    public Lessoninfo(String time, String otherInfo) {

        takeWeek = new ArrayList<Integer>();
        String[] timeinfo = time.split("-");
        String[] lessoninfo = otherInfo.split(";| ");
        // lessonName
        lessoninfo[0] = lessoninfo[0].replace("<<", "");
        lessoninfo[0] = lessoninfo[0].replace(">>", "");

        this.lessonName = lessoninfo[0];

        // baseTime
        this.baseTime = Integer.parseInt(timeinfo[0]);

        // combineTime
        this.combineTime = Integer.parseInt(timeinfo[1]);

        // lessonSerial
        this.lessonSerial = Integer.parseInt(lessoninfo[1]);

        // roomPlace
        this.roomPlace += lessoninfo[2];

        // teacher
        this.teacher = lessoninfo[3];

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
                String[] week = lessoninfo[4].split("-");

                for (int i = Integer.parseInt(week[0]); i <= Integer.parseInt(week[1]); i++)
                    takeWeek.add(i);

            } else {
                String[] week = lessoninfo[4].split(",");

                for (int i = 0; i < week.length; i++)
                    takeWeek.add(Integer.parseInt(week[i]));

            }
        }
    }

    String getLessonName() {
        return this.lessonName;
    }

    int getBaseTime() {
        return this.baseTime;
    }

    int getCombineTime() {
        return this.combineTime;
    }

    int getLessonSerial() {
        return this.lessonSerial;
    }

    String getRoomPlace() {
        return this.roomPlace;
    }

    String getTeacher() {
        return this.teacher;
    }
}

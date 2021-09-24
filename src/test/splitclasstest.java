package test;

import java.util.ArrayList;
import java.util.regex.*;

public class splitclasstest {
    public static void main(String[] args) {

        String str = "<<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 9 实验学时 <<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 10 实验学时 <<数据结构>>;3 博知楼508-专业实验中心 刘遵仁 11 实验学时";

        ArrayList<String> cla = new ArrayList<String>();
        String pattern = "<<[^<<]*";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(str);
        int splitPosition = 0;
        while (m.find()) {
            String tt = str.substring(splitPosition, m.end());
            splitPosition = m.end();
            cla.add(tt);
        }
        for(int i=0;i< cla.size();i++)
        {
            splitclasstest tmp=new splitclasstest("5-11",cla.get(i));
            tmp.show();
            System.out.println();
        }
    }

    String lessonName;
    int baseTime;
    int combineTime;
    int lessonSerial;
    String roomPlace = "@";
    String teacher;

    private ArrayList<Integer> takeWeek;

    public void show(){
         System.out.println(this.lessonName);
         System.out.println(this.baseTime);
         System.out.println(this.combineTime);
         System.out.println(this.lessonSerial);
         System.out.println(this.roomPlace);
         System.out.println(this.teacher);
         for(int i=0;i<this.takeWeek.size();i++)
             System.out.print(this.takeWeek.get(i)+" ");
         System.out.println();
    }
    public splitclasstest(String time, String otherInfo) {
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
            if (lessoninfo[4].contains("-")) {
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
}
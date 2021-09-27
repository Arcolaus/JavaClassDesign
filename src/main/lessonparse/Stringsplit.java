package main.lessonparse;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Stringsplit {
    private ArrayList<String> lessons = new ArrayList<String>();
    private String pattern = "<<[^<<]*";
    private String combineTime;
    Stringsplit(String combinetime, String combineinfo) {
        this.combineTime=combinetime;
        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(combineinfo);

        int splitPosition = 0;
        while (matcher.find()) {
            String singlelesson = combineinfo.substring(splitPosition, matcher.end());
            splitPosition = matcher.end();
            lessons.add(singlelesson);
        }
    }

    ArrayList<Lessoninfo> getLessons(){
        ArrayList<Lessoninfo> result=new ArrayList<Lessoninfo>();
        for(int i=0;i<lessons.size();i++)
            result.add(new Lessoninfo(this.combineTime,lessons.get(i)));
        return result;
    }
}

package main.lessonview;

import main.lessonparse.Student;

import javax.swing.*;
import java.awt.*;

public class lessonTable extends JTable {
     private static final Object[] tableHead={"","周一","周二","周三","周四","周五","周六","周日"};
     private static final Object[][] initContent={
            {" 1"," 2"," 3"," 4"," 5"," 6"," 7"," 8"}
    };
    public lessonTable(Student student){
        super(initContent,tableHead);
    }
}

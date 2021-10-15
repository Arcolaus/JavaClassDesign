package main.lessonview;

import main.lessonparse.Lesson;
import main.lessonparse.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import java.awt.*;

public class lessonTable extends JTable {
    private static final Object[] tableHead = {" ", "周一", "周二", "周三", "周四", "周五", "周六", "周日"};
    private static Object[][] initContent = {
            {" 1", "", "", "", "", "", "", ""},
            {" 2", "", "", "", "", "", "", ""},
            {" 3", "", "", "", "", "", "", ""},
            {" 4", "", "", "", "", "", "", ""},
            {"T1", "", "", "", "", "", "", ""},
            {" 5", "", "", "", "", "", "", ""},
            {" 6", "", "", "", "", "", "", ""},
            {" 7", "", "", "", "", "", "", ""},
            {" 8", "", "", "", "", "", "", ""},
            {"T2", "", "", "", "", "", "", ""},
            {" 9", "", "", "", "", "", "", ""},
            {"10", "", "", "", "", "", "", ""},
            {"11", "", "", "", "", "", "", ""},
    };

    public lessonTable(Student student, int weekTime) {
        for (Lesson it : student.getStudentLessons()) {
            if (!it.getTakeWeek().contains(weekTime))
                continue;

            // 确定处于哪一格
            int dayTime2Col = it.getDayTime();
            int combineTime2Row = it.getCombineTime();

            // 格内显示的信息
            String info_1 = it.getLessonName();
            String info_2 = it.getRoomPlace();
            String info_3 = it.getTeacher();
            String allInfo = "<html><body>" + info_1 + "<br>" + info_2 + "<br>" + info_3;
            initContent[dayTime2Col][combineTime2Row] = allInfo;
        }
        TableModel t = new DefaultTableModel(initContent, tableHead);
        this.setModel(t);

        this.setRowHeight(65);

        // 设置第一列列宽，用于显示课程节数
        TableColumnModel columnModel1 = this.getColumnModel();
        TableColumn firstColumn = columnModel1.getColumn(0);
        firstColumn.setMaxWidth(20);
    }
}

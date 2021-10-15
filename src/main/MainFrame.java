package main;

import main.lessonparse.HtmlParse;
import main.lessonparse.Student;
import main.lessonview.lessonTable;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener {
    final static int STUDENT = 0;
    final static int ADMIN = 1;
    // 登录及用户信息
    private LoginFrame loginFrame;
    private int userType;
    private String userId;

    // 信息区
    private JPanel infoField;
    private JLabel profilePhoto;
    private ImageIcon imgPhoto;
    private JLabel userInfo;

    // 功能区
    private JPanel funcField;
    private JButton funcExit;
    private JButton funcAddLesson;
    private JButton funcModifyLesson;
    private JButton funcDeleteLesson;
    private JButton funcImportHtml;
    private JButton funcUploadDB;
    private JButton preWeek;
    private JButton nextWeek;

    // 学生信息
    private Student student;
    private int currentWeek=1;
    private JLabel showCurrentWeek;
    // 课表内容区

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

    MainFrame() {
        super("Lesson Vision");
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        loginFrame = new LoginFrame();
        loginFrame.getUserLogin().addActionListener(this);

        // 添加右侧信息功能区
        infoField = new JPanel();
        infoField.setLayout(null);
        infoField.setBounds(0, 0, 130, 420);
        infoField.setBackground(new Color(210, 209, 209));

        // 添加右上角头像
        profilePhoto = new JLabel();
        imgPhoto = new ImageIcon("src/img/MainFrame/otherPhoto.png");
        if (userType == STUDENT)
            imgPhoto = new ImageIcon("src/img/MainFrame/studentPhoto.png");
        else if (userType == ADMIN)
            imgPhoto = new ImageIcon("src/img/MainFrame/adminPhoto.png");
        imgPhoto.setImage(imgPhoto.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
        profilePhoto.setIcon(imgPhoto);
        profilePhoto.setBounds(15, 0, 100, 100);
        infoField.add(profilePhoto);

        // 添加用户信息栏
        Font userinfoFont = new Font("黑体", Font.PLAIN, 14);
        userInfo = new JLabel();
        userInfo.setFont(userinfoFont);
        userInfo.setText("<html>班级:计科00班<br>学号:S000001</html>");
        userInfo.setVerticalAlignment(JLabel.CENTER);
        userInfo.setBounds(15, 105, 100, 40);
        infoField.add(userInfo);

        // 添加右侧按钮板
        Font buttonFont = new Font("黑体", Font.PLAIN, 16);
        funcField = new JPanel();
        funcField.setOpaque(false);
        funcField.setLayout(new GridLayout(6, 1, 0, 5));
        funcField.setBounds(15, 200, 100, 210);

        // 添加课程按钮
        funcAddLesson = new JButton("添加课程");
        funcAddLesson.setFont(buttonFont);
        funcAddLesson.setBackground(Color.GREEN);
        funcAddLesson.setUI(new BasicButtonUI());
        funcField.add(funcAddLesson);

        // 修改课程按钮
        funcModifyLesson = new JButton("修改课程");
        funcModifyLesson.setFont(buttonFont);
        funcModifyLesson.setBackground(Color.ORANGE);
        funcModifyLesson.setUI(new BasicButtonUI());
        funcField.add(funcModifyLesson);

        // 删除课程按钮
        funcDeleteLesson = new JButton("删除课程");
        funcDeleteLesson.setFont(buttonFont);
        funcDeleteLesson.setBackground(Color.PINK);
        funcDeleteLesson.setUI(new BasicButtonUI());
        funcField.add(funcDeleteLesson);

        // 导入按钮
        funcImportHtml=new JButton("导入课程");
        funcImportHtml.setFont(buttonFont);
        funcImportHtml.setBackground(Color.CYAN);
        funcImportHtml.setUI(new BasicButtonUI());
        funcField.add(funcImportHtml);

        // 上传按钮
        funcUploadDB=new JButton("上传到云");
        funcUploadDB.setFont(buttonFont);
        funcUploadDB.setBackground(Color.CYAN);
        funcUploadDB.setUI(new BasicButtonUI());
        funcField.add(funcUploadDB);

        // 退出按钮
        funcExit = new JButton("退出");
        funcExit.setFont(buttonFont);
        funcExit.setBackground(Color.RED);
        funcExit.setUI(new BasicButtonUI());
        funcExit.addActionListener(this);
        funcField.add(funcExit);

        // 添加课表
        HtmlParse doc = new HtmlParse("src/img/chk.html");
        student = new Student(doc.getStudentId(), doc.getStudentClass());
        student.addLesson(doc.getLessons());
        lessonTable table=new lessonTable(student,currentWeek);
        JScrollPane t=new JScrollPane(table);
        t.setBounds(140,5,1280,850);
        this.add(t);

        // 当前周数显示
        showCurrentWeek=new JLabel("第"+Integer.toString(currentWeek)+"周");
        showCurrentWeek.setFont(new Font("Microsoft Yahei UI", Font.BOLD, 18));
        showCurrentWeek.setBounds(40,430,90,30);
        this.add(showCurrentWeek);

        // 上一周按钮
        preWeek=new JButton("上一周");
        preWeek.setFont(buttonFont);
        preWeek.addActionListener(this);
        preWeek.setBounds(20,470,90,30);
        this.add(preWeek);

        // 下一周按钮
        nextWeek=new JButton("下一周");
        nextWeek.setFont(buttonFont);
        nextWeek.addActionListener(this);
        nextWeek.setBounds(20,505,90,30);
        this.add(nextWeek);

        this.add(funcField);
        this.add(infoField);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1440, 900);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent op) {
        // 登录许可
//        if (loginFrame.getPermission()) {
        this.setVisible(true);
        loginFrame.dispose();
//        } else if (loginFrame.emptyUserInfo() && !loginFrame.getPermission()) {
//            JOptionPane.showMessageDialog(this, "用户信息错误，请检查用户名和密码，或者用户类型");
//        }

        if (op.getSource() == funcExit)
            this.dispose();
        if(op.getSource()==preWeek){
            if(currentWeek>1)
                currentWeek--;
            showCurrentWeek.setText("第"+Integer.toString(currentWeek)+"周");
            lessonTable table=new lessonTable(student,currentWeek);
            JScrollPane t=new JScrollPane(table);
            t.setBounds(140,5,1280,850);
            this.add(t);
        }
        if(op.getSource()==nextWeek){
            if(currentWeek<20)
                currentWeek++;
            showCurrentWeek.setText("第"+Integer.toString(currentWeek)+"周");
            lessonTable table=new lessonTable(student,currentWeek);
            JScrollPane t=new JScrollPane(table);
            t.setBounds(140,5,1280,850);
            this.add(t);
        }
    }
}

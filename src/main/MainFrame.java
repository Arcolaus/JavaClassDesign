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

    // 学生信息
    private Student student;

    // 课表内容区

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
    }

    MainFrame() {
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
        funcField.setLayout(new GridLayout(4, 1, 0, 5));
        funcField.setBounds(15, 200, 100, 140);

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
        funcDeleteLesson.setBackground(Color.RED);
        funcDeleteLesson.setUI(new BasicButtonUI());
        funcField.add(funcDeleteLesson);

        // 退出按钮
        funcExit = new JButton("退出");
        funcExit.setFont(buttonFont);
        funcExit.setBackground(Color.WHITE);
        funcExit.setUI(new BasicButtonUI());
        funcExit.addActionListener(this);
        funcField.add(funcExit);

        // 添加课表
        HtmlParse doc = new HtmlParse("src/img/chk.html");
        student = new Student(doc.getStudentId(), doc.getStudentClass());
//        student=new Student();
        lessonTable table=new lessonTable(student,1);
        JScrollPane t=new JScrollPane(table);
//        t=new JScrollPane();
        t.setBounds(140,5,1280,850);
        this.add(t);

        this.add(funcField);
        this.add(infoField);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1440, 900);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent op) {
        // 登录允许
//        if (loginFrame.getPermission()) {
        this.setVisible(true);
        loginFrame.dispose();
//        } else if (loginFrame.emptyUserInfo() && !loginFrame.getPermission()) {
//            JOptionPane.showMessageDialog(this, "用户信息错误，请检查用户名和密码，或者用户类型");
//        }

        if (op.getSource() == funcExit)
            this.dispose();
    }
}

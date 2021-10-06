package main;

import main.lessonparse.Student;

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
    private boolean loginPermited;
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
        infoField.setBounds(0, 5, 130, 520);
        infoField.setBackground(new Color(210, 209, 209, 255));

        // 添加右上角头像
        profilePhoto = new JLabel();
        imgPhoto = new ImageIcon("img/MainFrame/otherPhoto.png");
        if (userType == STUDENT)
            imgPhoto = new ImageIcon("img/MainFrame/studentPhoto.png");
        else if (userType == ADMIN)
            imgPhoto = new ImageIcon("img/MainFrame/adminPhoto.png");
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

        // 添加右侧按钮
        Font buttonFont = new Font("黑体", Font.PLAIN, 16);
        funcField = new JPanel();
        funcField.setOpaque(false);
        funcField.setLayout(new GridLayout(4, 1, 0, 5));
        funcField.setBounds(15, 200, 100, 140);
        funcExit = new JButton("退出");
        funcAddLesson = new JButton("添加课程");
        funcModifyLesson = new JButton("修改课程");
        funcDeleteLesson = new JButton("删除课程");

        funcAddLesson.setFont(buttonFont);
        funcDeleteLesson.setFont(buttonFont);
        funcExit.setFont(buttonFont);
        funcModifyLesson.setFont(buttonFont);

        funcAddLesson.setBackground(Color.GREEN);
        funcModifyLesson.setBackground(Color.ORANGE);
        funcDeleteLesson.setBackground(Color.RED);
        funcExit.setBackground(Color.WHITE);
        funcAddLesson.setUI(new BasicButtonUI());
        funcModifyLesson.setUI(new BasicButtonUI());
        funcDeleteLesson.setUI(new BasicButtonUI());
        funcExit.setUI(new BasicButtonUI());

        funcField.add(funcAddLesson);
        funcField.add(funcModifyLesson);
        funcField.add(funcDeleteLesson);
        funcField.add(funcExit);
        this.add(funcField);

        this.add(infoField);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1024, 768);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

//        if (loginFrame.getPermission()) {
        this.setVisible(true);
        loginFrame.dispose();
//        } else if (loginFrame.emptyUserInfo() && !loginFrame.getPermission()) {
//            JOptionPane.showMessageDialog(this, "用户信息错误，请检查用户名和密码，或者用户类型");
//        }

    }
}

package main;

import main.lessonparse.HtmlParse;
import main.lessonparse.Lesson;
import main.lessonparse.Student;
import main.lessonview.lessonTable;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;

public class MainFrame extends JFrame implements ActionListener {
    public final static int STUDENT = 0;
    public final static int ADMIN = 1;

    //注册驱动，输入链接地址，用户名，密码
    private final String driver = "com.mysql.cj.jdbc.Driver";
    //访问mysql数据库
    //格式 jdbc:数据库://本地端口3306/数据库名?跨时区参数
    private final String url = "jdbc:mysql://db.vesuo.cn:3306/hy_vesuo_cn?serverTimezone=Asia/Shanghai";
    private final String username = "hy";
    private final String password = "xhntA5PwRMhybShG";
    private final Connection connect = DriverManager.getConnection(url, username, password);
    private final Statement stat = connect.createStatement();

    // 选择HTML文件导入
    private JFileChooser importHtml;
    private File htmlDoc;
    private FileNameExtensionFilter htmlFilter;

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
    private JButton funcDonwload;
    private JButton preWeek;
    private JButton nextWeek;

    // 学生信息
    private Student student;

    // 课表内容区
    private int currentWeek = 1;
    private JLabel showCurrentWeek;
    private JScrollPane tableShow;

    public static void main(String[] args) {
        try {
            MainFrame mainFrame = new MainFrame();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    MainFrame() throws ClassNotFoundException, SQLException {
        super("Lesson Vision");
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(255, 255, 255));
        loginFrame = new LoginFrame(stat);
        student = new Student(loginFrame.getUsername(), null);
        loginFrame.getUserLogin().addActionListener(this);
        loginFrame.getPasswordField().addActionListener(this);

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
        userInfo.setText("<html>"+"班级:"+ student.getStudentClass() + "<br>学号:" + student.getStudentId() + "</html>");
        userInfo.setVerticalAlignment(JLabel.CENTER);
        userInfo.setBounds(15, 105, 120, 40);
        infoField.add(userInfo);

        // 添加右侧按钮板
        Font buttonFont = new Font("黑体", Font.PLAIN, 16);
        funcField = new JPanel();
        funcField.setOpaque(false);
        funcField.setLayout(new GridLayout(7, 1, 0, 5));
        funcField.setBounds(15, 190, 100, 230);

        // 添加课程按钮
        funcAddLesson = new JButton("添加课程");
        funcAddLesson.setFont(buttonFont);
        funcAddLesson.setBackground(Color.GREEN);
        funcAddLesson.setUI(new BasicButtonUI());
        funcAddLesson.addActionListener(this);
        funcField.add(funcAddLesson);

        // 修改课程按钮
        funcModifyLesson = new JButton("修改课程");
        funcModifyLesson.setFont(buttonFont);
        funcModifyLesson.setBackground(Color.ORANGE);
        funcModifyLesson.setUI(new BasicButtonUI());
        funcModifyLesson.addActionListener(this);
        funcField.add(funcModifyLesson);

        // 删除课程按钮
        funcDeleteLesson = new JButton("删除课程");
        funcDeleteLesson.setFont(buttonFont);
        funcDeleteLesson.setBackground(Color.PINK);
        funcDeleteLesson.setUI(new BasicButtonUI());
        funcDeleteLesson.addActionListener(this);
        funcField.add(funcDeleteLesson);

        // 导入
        funcImportHtml = new JButton("导入课程");
        funcImportHtml.setFont(buttonFont);
        funcImportHtml.setBackground(Color.CYAN);
        funcImportHtml.setUI(new BasicButtonUI());
        funcImportHtml.addActionListener(this);
        funcField.add(funcImportHtml);

        // 上传
        funcUploadDB = new JButton("上传到云");
        funcUploadDB.setFont(buttonFont);
        funcUploadDB.setBackground(Color.CYAN);
        funcUploadDB.setUI(new BasicButtonUI());
        funcUploadDB.addActionListener(this);
        funcField.add(funcUploadDB);

        // 下载
        funcDonwload = new JButton("从云下载");
        funcDonwload.setFont(buttonFont);
        funcDonwload.setBackground(Color.CYAN);
        funcDonwload.setUI(new BasicButtonUI());
        funcDonwload.addActionListener(this);
        funcField.add(funcDonwload);

        // 退出按钮
        funcExit = new JButton("退出");
        funcExit.setFont(buttonFont);
        funcExit.setBackground(Color.RED);
        funcExit.setUI(new BasicButtonUI());
        funcExit.addActionListener(this);
        funcField.add(funcExit);

        // 添加课表
        importHtml = new JFileChooser();
        lessonTable table = new lessonTable();
        tableShow = new JScrollPane(table);
        tableShow.setBounds(140, 5, 1280, 850);
        this.add(tableShow);

        // 当前周数显示
        showCurrentWeek = new JLabel("第" + Integer.toString(currentWeek) + "周");
        showCurrentWeek.setFont(new Font("Microsoft Yahei UI", Font.BOLD, 18));
        showCurrentWeek.setBounds(40, 430, 90, 30);
        this.add(showCurrentWeek);

        // 上一周按钮
        preWeek = new JButton("上一周");
        preWeek.setFont(buttonFont);
        preWeek.addActionListener(this);
        preWeek.setBounds(20, 470, 90, 30);
        this.add(preWeek);

        // 下一周按钮
        nextWeek = new JButton("下一周");
        nextWeek.setFont(buttonFont);
        nextWeek.addActionListener(this);
        nextWeek.setBounds(20, 505, 90, 30);
        this.add(nextWeek);

        this.add(funcField);
        this.add(infoField);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1440, 900);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void updateInfo() {
        showCurrentWeek.setText("第" + Integer.toString(currentWeek) + "周");
        lessonTable table = new lessonTable(student, currentWeek);
        tableShow = new JScrollPane(table);
        tableShow.setBounds(140, 5, 1280, 850);
        this.add(tableShow);
    }

    @Override
    public void actionPerformed(ActionEvent op) {
        // 登录许可
//        System.out.println(loginFrame.emptyUserInfo() +" in "+!loginFrame.getPermission());
        if (loginFrame.getPermission()) {
            this.setVisible(true);
            loginFrame.dispose();
        } else {
            if (!loginFrame.emptyUserInfo())
                JOptionPane.showMessageDialog(this, "用户信息错误，请检查用户名和密码，或者用户类型");
        }

        if (op.getSource() == funcExit)
            this.dispose();

        if (op.getSource() == preWeek) {
            if (currentWeek > 1)
                currentWeek--;
            updateInfo();
        }
        if (op.getSource() == nextWeek) {
            if (currentWeek < 20)
                currentWeek++;
            updateInfo();
        }

        if (op.getSource() == funcImportHtml) {
            htmlFilter = new FileNameExtensionFilter("HTML文件", "html");
            importHtml.setFileFilter(htmlFilter);
            importHtml.setCurrentDirectory(new File("./src/img/"));
            int val = importHtml.showOpenDialog(this);

            if (val == JFileChooser.APPROVE_OPTION) {
                this.currentWeek = 1;
                HtmlParse doc = new HtmlParse(importHtml.getSelectedFile().getAbsolutePath());
                student = new Student(doc.getStudentId(), doc.getStudentClass());
                student.addLesson(doc.getLessons());
                updateInfo();
                boolean userExsit = false;
                String sql = "SELECT ID FROM T_STUDENT WHERE ID = '%s';";
                sql = String.format(sql, student.getStudentId());
//                System.out.println(sql);
                try {
                    ResultSet rs = stat.executeQuery(sql);
                    while (rs.next())
                        userExsit = true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                if (!userExsit) {
                    String addName = "INSERT INTO T_STUDENT (ID,CLASS) VALUES ('%s','%s');";
                    addName = String.format(addName, student.getStudentId(), student.getStudentClass());
                    try {
                        stat.execute(addName);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                userInfo.setText("<html>" + student.getStudentClass() + "<br>学号:" + student.getStudentId() + "</html>");
            }
        }
        if (op.getSource() == funcAddLesson) {
            this.currentWeek = 1;
            showCurrentWeek.setText("第" + Integer.toString(currentWeek) + "周");
            JTextField timeField = new JTextField("1-1", 3);
            JTextField nameField = new JTextField("大学生素养", 7);
            JTextField serialField = new JTextField("1", 5);
            JTextField roomField = new JTextField("博知000", 5);
            JTextField teacherField = new JTextField("教师", 5);
            JTextField weekField = new JTextField("单周2-16", 5);
            JTextField typeField = new JTextField("讲课学时", 5);


            JPanel myPanel = new JPanel();
            myPanel.setLayout(new GridLayout(7, 2, 0, 3));
            myPanel.add(new JLabel("课程时间"));
            myPanel.add(timeField);
            myPanel.add(new JLabel("课程名"));
            myPanel.add(nameField);
            myPanel.add(new JLabel("课序号"));
            myPanel.add(serialField);
            myPanel.add(new JLabel("课序号"));
            myPanel.add(roomField);
            myPanel.add(new JLabel("上课教室"));
            myPanel.add(teacherField);
            myPanel.add(new JLabel("授课老师"));
            myPanel.add(weekField);
            myPanel.add(new JLabel("上课周"));
            myPanel.add(typeField);

            int option = JOptionPane.showConfirmDialog(this, myPanel,
                    "请输入课程信息", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                String otherInfo = "<<" + nameField.getText() + ">>;" + serialField.getText() + " " + roomField.getText() + " " + teacherField.getText() + " " + weekField.getText() + " " + typeField.getText();
                Lesson lesson = new Lesson(timeField.getText(), otherInfo);
                student.addLesson(lesson);
                lessonTable table = new lessonTable(student, currentWeek);
                tableShow = new JScrollPane(table);
                tableShow.setBounds(140, 5, 1280, 850);
                this.add(tableShow);
            }
        }

        if (op.getSource() == funcModifyLesson) {
            String inputLessonName = JOptionPane.showInputDialog(this, "请输入要删除的课程名", "");
            if (!inputLessonName.equals("")) {
                this.currentWeek = 1;
                showCurrentWeek.setText("第" + Integer.toString(currentWeek) + "周");
            }
        }

        if (op.getSource() == funcDeleteLesson) {
            this.currentWeek = 1;
            showCurrentWeek.setText("第" + Integer.toString(currentWeek) + "周");
            String inputLessonName = JOptionPane.showInputDialog(this, "请输入要删除的课程名", "");
            student.removeLessonByName(inputLessonName);
            lessonTable table = new lessonTable(student, currentWeek);
            tableShow = new JScrollPane(table);
            tableShow.setBounds(140, 5, 1280, 850);
            this.add(tableShow);
        }

        if (op.getSource() == funcUploadDB) {
            for (Lesson it : student.getStudentLessons()) {
                String sql = "INSERT INTO T_LESSONS(STUDENT, LESSONNAME, DAYTIME, COMBINETIME, SERIAL, ROOM, TEACHR)" +
                        " VALUES ('%s','%s',%d,%d,%d,'%s','%s');";
                sql = String.format(sql, student.getStudentId(), it.getLessonName(), it.getDayTime(), it.getCombineTime(),
                        it.getLessonSerial(), it.getRoomPlace(), it.getTeacher());
                try {
                    stat.execute(sql);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if(op.getSource()==funcDonwload){
            System.out.println("download");
        }
    }
}
/*
 * INSERT INTO T_LESSONS(STUDENT, LESSONNAME, DAYTIME, COMBINETIME, SERIAL, ROOM, TEACHR)
 * VALUES
 * ('2019206639','计算机网络',3,7,1,'bozhi','fra');
 * */

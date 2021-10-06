package main;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame implements ActionListener {
    private String username = "1";
    private String password = "1";

    private boolean emptyInfo;
    private JPanel userInfo;
    private JTextField userNameFild;
    private JPasswordField passwordField;

    private ImageIcon decorateImg;

    private JRadioButton adminOP;
    private JRadioButton userop;
    private ButtonGroup opGrop;

    private JButton userLogin;
    private JButton exit;

    private boolean loginPermission = false;

    LoginFrame() {
        super();
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(189, 232, 222));

        // 添加左侧图片
        decorateImg = new ImageIcon("img/LoginFrame/decoration.png");
        JLabel decorateLabel = new JLabel();
        decorateLabel.setIcon(decorateImg);
        decorateLabel.setBounds(0, 0, decorateImg.getIconWidth() - 40, decorateImg.getIconHeight());
        this.add(decorateLabel);

        // 添加美化标题
        Font titleFont = new Font("Lucida Handwriting", Font.BOLD, 40);
        JLabel title = new JLabel("Class Vision");
        title.setFont(titleFont);
        title.setBounds(350, 0, 300, 80);
        title.setToolTipText("更好的课表可视化");
        this.add(title);

        //  添加用户名显示和输入框
        Font infoFont = new Font("Microsoft Yahei UI", Font.PLAIN, 20);
        JLabel usernameInfo = new JLabel("用户名");
        userNameFild = new JTextField("1");
        usernameInfo.setFont(infoFont);
        usernameInfo.setBounds(350, 70, 80, 80);
        userNameFild.setFont(infoFont);
        userNameFild.setBounds(430, 95, 180, 30);
        userNameFild.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String inputName = userNameFild.getText();
                String inputpwd = String.valueOf(passwordField.getPassword());
                if (password.equals(inputpwd) && username.equals(inputName))
                    loginPermission = true;
                if (inputName.equals("") || inputpwd.equals(""))
                    emptyInfo = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(userNameFild);
        this.add(usernameInfo);


        //  添加密码输入提示和输入框
        JLabel passwordInfo = new JLabel("密码");
        passwordField = new JPasswordField("1");
        passwordInfo.setFont(infoFont);
        passwordInfo.setBounds(350, 120, 80, 80);
        passwordField.setBounds(430, 145, 180, 30);
        passwordField.setEchoChar('*');
        passwordField.setFont(infoFont);
        passwordField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String inputName = userNameFild.getText();
                String inputpwd = String.valueOf(passwordField.getPassword());
                if (password.equals(inputpwd) && username.equals(inputName))
                    loginPermission = true;
                if (inputName.equals("") || inputpwd.equals(""))
                    emptyInfo = true;
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        this.add(passwordInfo);
        this.add(passwordField);

        // 添加用户类型选项
        Font optionFont = new Font("Microsoft Yahei UI", Font.PLAIN, 16);
        opGrop = new ButtonGroup();
        adminOP = new JRadioButton("管理员");
        adminOP.setFont(optionFont);
        adminOP.setBounds(430, 200, 100, 30);
        adminOP.setOpaque(false);
        opGrop.add(adminOP);
        this.add(adminOP);
        userop = new JRadioButton("学生");
        userop.setFont(optionFont);
        userop.setBounds(550, 200, 100, 30);
        userop.setOpaque(false);
        opGrop.add(userop);
        this.add(userop);

        // 添加功能按钮
        Font funcFont = new Font("Microsoft Yahei UI", Font.PLAIN, 10);
        userLogin = new JButton("登录");
        exit = new JButton("退出");
        userLogin.setBounds(400, 250, 70, 30);
        exit.setBounds(560, 250, 70, 30);
        userLogin.setFont(funcFont);
        exit.setFont(funcFont);
        userLogin.addActionListener(this);
        exit.addActionListener(this);
        this.add(userLogin);
        this.add(exit);

        this.setTitle("登录");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String inputName = userNameFild.getText();
        String inputpwd = String.valueOf(passwordField.getPassword());
        if (e.getSource() == userLogin) {
            if (inputName.equals("") || inputpwd.equals(""))
                JOptionPane.showMessageDialog(this, "用户名和密码不可为空!");
        } else if (e.getSource() == exit) {
            this.dispose();
        }
    }

    public boolean getPermission() {
        return loginPermission;
    }

    public JButton getUserLogin() {
        return userLogin;
    }

    public boolean emptyUserInfo() {
        return emptyInfo;
    }
}

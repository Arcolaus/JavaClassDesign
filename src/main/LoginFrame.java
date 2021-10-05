package main;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private String username;
    private String password;

    private JLabel horLine1;
    private JLabel horLine2;
    private JLabel verLine1;
    private JLabel verLine2;
    private ImageIcon horLineImg;
    private ImageIcon verLineImg;

    private JPanel userInfo;
    private JTextField userNameFild;
    private JPasswordField passwordField;

    LoginFrame() {
        super();
        horLineImg = new ImageIcon("img/LoginFrame/HorizontalLine.png");
        verLineImg = new ImageIcon("img/LoginFrame/VerticalLine.png");
        horLine1 = new JLabel();
        horLine2 = new JLabel();
        verLine1 = new JLabel();
        verLine2 = new JLabel();

        horLine1.setIcon(horLineImg);
        horLine1.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        horLine2.setIcon(horLineImg);
        horLine2.setHorizontalAlignment(SwingConstants.HORIZONTAL);
        verLine1.setIcon(verLineImg);
        verLine2.setIcon(verLineImg);

        this.add(horLine1, BorderLayout.NORTH);
        this.add(horLine2, BorderLayout.SOUTH);
        this.add(verLine1, BorderLayout.WEST);
        this.add(verLine2, BorderLayout.EAST);

        userInfo = new JPanel();
        userInfo.setLayout(null);
        Font font = new Font("黑体", Font.PLAIN, 18);

        // 用户名显示
        JLabel jl1 = new JLabel("用户名：");
        jl1.setFont(font);
        jl1.setBounds(50, 30, 100, 30);

        // 用户名填写
        userNameFild = new JTextField();
        userNameFild.setBounds(130, 30, 150, 30);

        // 密码显示
        JLabel jl3 = new JLabel("密码：");
        jl3.setFont(font);
        jl3.setBounds(60, 80, 100, 30);

        // 密码填写
        passwordField = new JPasswordField();
        passwordField.setBounds(130, 80, 150, 30);

        userInfo.add(jl1);
        userInfo.add(userNameFild);
        userInfo.add(jl3);
        userInfo.add(passwordField);
        this.add(userInfo, BorderLayout.CENTER);

        this.setTitle("LoginFrame");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

}

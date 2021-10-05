package main;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {
    private String username;
    private String password;

    private JPanel userInfo;
    private JTextField userNameFild;
    private JPasswordField passwordField;

    private JLabel decorateLabel;
    private ImageIcon decorateImg;

    LoginFrame() {
        super();
        this.setLayout(null);
        this.getContentPane().setBackground(new Color(189, 232, 222));

        // 添加左侧图片
        decorateImg = new ImageIcon("img/LoginFrame/decoration.png");
        decorateLabel = new JLabel();
        decorateLabel.setIcon(decorateImg);
        decorateLabel.setBounds(0, 0, decorateImg.getIconWidth() - 40, decorateImg.getIconHeight());
//        decorateLabel.setOpaque(true);
        this.add(decorateLabel);

        // 添加美化标题
        Font titleFont = new Font("Bauhaus 93", Font.PLAIN, 50);
        JLabel title = new JLabel("Class Vision");
        title.setFont(titleFont);
        title.setBounds(350, 0, 300, 80);
        title.setToolTipText("更好的课表可视化~");
//        title.setOpaque(true);
        this.add(title);

        Font infoFont=new Font("Microsoft Yahei UI",Font.PLAIN,20);
        //  添加用户名显示和输入框
        JLabel usernameInfo=new JLabel("用户名");
        userNameFild=new JTextField();
        usernameInfo.setFont(infoFont);
        usernameInfo.setBounds(350,70,80,80);
        userNameFild.setFont(infoFont);
        userNameFild.setBounds(430,95,180,30);
        this.add(userNameFild);
        this.add(usernameInfo);


        //  添加密码输入提示和输入框
        JLabel passwordInfo=new JLabel("密码");
        passwordField=new JPasswordField();
        passwordInfo.setFont(infoFont);
        passwordInfo.setBounds(350,120,80,80);
        passwordField.setBounds(430,145,180,30);
        this.add(passwordInfo);
        this.add(passwordField);

        this.setTitle("登录");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 400);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }

}

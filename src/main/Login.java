package main;

import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JLabel ImageShow;
    private ImageIcon ImageShow_Icon;

    private JPanel InfoField;
    private JTextField usrfield;
    private JPasswordField pwfield;

    private String username;
    private String password;

    public Login() {
        this.setLayout(new FlowLayout(0,0,0));
        ImageShow_Icon = new ImageIcon("img/LoginCover.png");
        ImageShow_Icon.setImage(ImageShow_Icon.getImage().getScaledInstance(260, 260,Image.SCALE_SMOOTH));

        ImageShow = new JLabel(ImageShow_Icon);
        ImageShow.setIcon(ImageShow_Icon);
        this.add(ImageShow);

        InfoField=new JPanel();
        InfoField.setLayout(new BorderLayout());
        JLabel jl1=new JLabel("Username");
        InfoField.add(jl1,BorderLayout.NORTH);
        usrfield=new JTextField();
        pwfield=new JPasswordField("Password");
        InfoField.add(pwfield,BorderLayout.SOUTH);
        this.add(InfoField);


        this.setTitle("main.Login");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
    }
}

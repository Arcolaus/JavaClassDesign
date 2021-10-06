package main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame implements ActionListener{
    public static void main(String[] args) {
       // MainFrame k = new MainFrame();
        LoginFrame t = new LoginFrame();
        //t.userLogin.addActionListener(k);
//        k.addComponentListener(t.userLogin);
//        System.out.println(t.getPermission());
    }

    MainFrame() {
        super("Class Vision");
//        t=new LoginFrame();
//        t.userLogin.addActionListener(this);
//        this.addL
//        t.addComponentListener(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(new LoginFrame().userLogin)){
            System.out.println("success");
        }
    }
}

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
        JLabel jl1=new JLabel("Username1");
        JLabel jl2=new JLabel("Username2");
        InfoField.add(jl1,BorderLayout.NORTH);
        InfoField.add(jl2,BorderLayout.CENTER);
        usrfield=new JTextField();
        pwfield=new JPasswordField("Password");
        InfoField.add(pwfield,BorderLayout.SOUTH);
        this.add(InfoField);


        this.setTitle("Login");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600,300);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
//        this.setBounds(200,200,600,300);
        this.setVisible(true);
    }
}

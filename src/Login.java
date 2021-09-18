import javax.swing.*;
import java.awt.*;

public class Login extends JFrame {
    private JLabel ImageShow;
    private ImageIcon ImageShow_Icon;
    private String username;
    private String password;

    public Login() {
        ImageShow_Icon = new ImageIcon("img/LoginCover.png");
        ImageShow = new JLabel(ImageShow_Icon);
        this.add(ImageShow);
        ImageShow.setVisible(true);
    }
}

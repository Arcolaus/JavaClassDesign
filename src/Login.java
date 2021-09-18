import javax.swing.*;

public class Login {
    private JPanel LoginMain;
    private JLabel ImageShow;
    private ImageIcon ImageShow_Icon;

    public Login() {
        LoginMain=new JPanel();
//        JPanel.setDefaultLocale(a);

        ImageShow_Icon = new ImageIcon("img/Login.Cover.png");
        ImageShow = new JLabel(ImageShow_Icon);
    }
}

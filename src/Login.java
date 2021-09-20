import javax.swing.*;

public class Login {
    private JPanel root;
    private JLabel UsernameHint;
    private JPasswordField password;
    private JTextField username;
    private JLabel PasswordHint;
    private JButton button1;
    private JRadioButton radioButton1;
    private JRadioButton radioButton2;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login");
        frame.setContentPane(new Login().root);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450,350);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

}

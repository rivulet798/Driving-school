package gui;

import gui.dialog.Authorization;
import gui.dialog.Registration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Window extends JFrame {
    public static final String FILENAME = "/images/back.png";
    public Font font;
    public JButton registration;
    public JButton authorization;
    public JButton exit;
    public JComponent background;
    public JLabel welcome;
    private MyHandler handler = new MyHandler();

    private Window(String s) {
        super(s);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);

        background = new JComponent() {
            @Override
            public void paintComponent(Graphics graphics) {
                Image img = null;
                try {
                    img = ImageIO.read(getClass().getResource(FILENAME));
                } catch (Exception e) {
                    System.out.println( e.getMessage());
                }
                graphics.drawImage(img, 0, 0, 600, 500, null);
            }
        };
        this.setContentPane(background);

        font = new Font("TimesRoman", Font.BOLD, 18);

        registration = new JButton("Регистрация в системе");
        registration.setFont(font);
        registration.addActionListener(handler);

        authorization = new JButton("Вход в систему");
        authorization.setFont(font);
        authorization.addActionListener(handler);

        exit = new JButton("Выход из системы");
        exit.setFont(font);
        exit.addActionListener(handler);

        welcome = new JLabel();
        welcome.setText("<html><h2><i>Автошкола \" Дорога жизни \" </i></h2></html>");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setForeground(Color.BLACK);


        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(20, 100, 20, 100);
        con.fill = GridBagConstraints.HORIZONTAL;

        welcome.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 0;
        con.gridheight = 2;
        add(welcome, con);

        authorization.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 2;
        con.gridheight = 1;
        add(authorization, con);

        registration.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(registration, con);

        exit.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(exit, con);
    }

    private static class SingletonHolder { // nested class
        private final static Window INSTANCE = new Window("АВТОШКОЛА");
    }

    public static Window getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == authorization) {
                welcome.setText("<html><h2><i>Автошкола \" Дорога жизни \" </i></h2></html>");
                setVisible(false);
                Authorization authorization = Authorization.getInstance();
                authorization.info.setText("");
                authorization.password.setText("");
                authorization.login.setText("");
                authorization.password.setBackground(Color.WHITE);
                authorization.login.setBackground(Color.WHITE);
                authorization.setVisible(true);
            }
            if (act.getSource() == exit) {
                System.exit(0);
            }
            if(act.getSource() == registration){
                welcome.setText("<html><h2><i>Автошкола \" Дорога жизни \" </i></h2></html>");
                setVisible(false);
                Registration registration = Registration.getInstance();
                registration.info.setText("");
                registration.login.setText("");
                registration.phone.setText("");
                registration.password.setText("");
                registration.lastName.setText("");
                registration.firstName.setText("");
                registration.patronymic.setText("");
                registration.expirience.setText("");
                registration.login.setBackground(Color.WHITE);
                registration.phone.setBackground(Color.WHITE);
                registration.password.setBackground(Color.WHITE);
                registration.lastName.setBackground(Color.WHITE);
                registration.firstName.setBackground(Color.WHITE);
                registration.patronymic.setBackground(Color.WHITE);
                registration.expirience.setBackground(Color.WHITE);
                registration.setVisible(true);
            }
        }
    }

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        Window window = Window.getInstance();
        window.setVisible(true);
    }
}
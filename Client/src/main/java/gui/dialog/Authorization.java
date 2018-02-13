package gui.dialog;

import client.ClientRunner;
import entity.AbstractEntity;
import entity.ext.Admin;
import entity.ext.Teacher;
import gui.Window;
import gui.menu.AdminMenu;
import gui.menu.StudentMenu;
import gui.menu.TeacherMenu;
import message.MessageType;
import message.MyMessage;
import validation.ValidationData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import static java.lang.String.valueOf;

public class Authorization extends JDialog {
    public static final String FILENAME = "/images/back.png";
    public JLabel loginLabel;
    public JLabel passwordLabel;
    public JLabel info;
    public JComponent background;

    public JTextField login;
    public JPasswordField password;

    public JButton cancel;
    public JButton entry;

    private MyHandler handler = new MyHandler();

    private Authorization(JFrame owner) {
        super(owner, "Авторизация", true);
        this.setSize(300,400);
        this.setLocation(500,200);
        this.setResizable(false);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                Window window = Window.getInstance();
                window.setVisible(true);
            }
        });

        background = new JComponent() {
            @Override
            public void paintComponent(Graphics graphics) {
                Image img = null;
                try {
                    img = ImageIO.read(getClass().getResource(FILENAME));
                } catch (Exception e) {
                    System.out.println( e.getMessage());
                }
                graphics.drawImage(img, 0, 0, 300, 400, null);
            }
        };
        this.setContentPane(background);

        loginLabel = new JLabel("<html><h3><i><center> Введите логин </center></i></h3></html>");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setForeground(Color.BLACK);
        passwordLabel = new JLabel("<html><h3><i><center> Введите пароль </center></i></h3></html>");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setForeground(Color.BLACK);

        info = new JLabel();
        info.setHorizontalAlignment(JLabel.CENTER);
        info.setForeground(Color.BLUE);

        login = new JTextField();
        login.setHorizontalAlignment(JTextField.CENTER);
        password = new JPasswordField();
        password.setHorizontalAlignment(JTextField.CENTER);

        cancel = new JButton("Отмена");
        cancel.addActionListener(handler);
        entry = new JButton("Войти");
        entry.addActionListener(handler);

        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(20, 10, 20, 10);
        con.fill = GridBagConstraints.HORIZONTAL;

        loginLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(loginLabel, con);

        login.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 1;
        con.gridheight = 1;
        add(login, con);

        passwordLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 2;
        con.gridheight = 1;
        add(passwordLabel, con);

        password.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 2;
        con.gridheight = 1;
        add(password, con);

        info.setPreferredSize(new Dimension(10,10));
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 3;
        con.gridheight = 1;
        add(info, con);

        entry.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(entry, con);

        cancel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 5;
        con.gridheight = 1;
        add(cancel, con);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                info.setText("");
                login.setBackground(Color.WHITE);
                password.setBackground(Color.WHITE);
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == cancel) {
                login.setText("");
                password.setText("");
                Window window = Window.getInstance();
                window.setVisible(true);
                setVisible(false);
            }
            if (act.getSource() == entry) {
                if (ValidationData.checkLogin(login.getText()) && ValidationData.checkPassword(password.getPassword())) {
                    MyMessage myMessage = new MyMessage(login.getText(), valueOf(password.getPassword()));
                    myMessage.setMessageType(MessageType.AUTHORIZATION);
                    ClientRunner.clientThread.flag = 0;
                    ClientRunner.clientThread.setMyMessage(myMessage);
                    while (ClientRunner.clientThread.flag == 0) { }
                    if (ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.ADMIN_AUTHORIZATION) {
                        ClientRunner.clientThread.setCurrentUser(ClientRunner.clientThread.getMyMessage().getAdmin());
                        setVisible(false);
                        AdminMenu.getInstance().setVisible(true);
                        login.setText("");
                        password.setText("");

                    } else {
                        if (ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.TEACHER_AUTHORIZATION) {
                            ClientRunner.clientThread.setCurrentUser(ClientRunner.clientThread.getMyMessage().getTeacher());
                            setVisible(false);
                            TeacherMenu.getInstance().setVisible(true);
                            login.setText("");
                            password.setText("");
                        } else {
                            if (ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.STUDENT_AUTHORIZATION) {
                                ClientRunner.clientThread.setCurrentUser(ClientRunner.clientThread.getMyMessage().getStudent());
                                setVisible(false);
                                StudentMenu.getInstance().setVisible(true);
                                login.setText("");
                                password.setText("");
                            } else {
                                info.setText("Вы не зарегистрированы в системе");
                            }
                        }
                    }
                    ClientRunner.clientThread.flag = 0;
                } else {
                    if (!ValidationData.checkLogin(login.getText())) {
                        login.setBackground(Color.red);
                    }
                    if (!ValidationData.checkPassword(password.getPassword())) {
                        password.setBackground(Color.red);
                    }
                }
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static Authorization INSTANCE = new Authorization(Window.getInstance());
    }

    public static Authorization getInstance() {
        return Authorization.SingletonHolder.INSTANCE;
    }

}

package gui.dialog;

import client.ClientRunner;
import entity.ext.Teacher;
import gui.Window;
import message.MyMessage;
import message.MessageType;
import validation.ValidationData;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class Registration extends JDialog {

    private MyHandler handler = new MyHandler();

    public static final String FILENAME = "/images/img.png";
    public JLabel lastNameLabel;
    public JLabel firstNameLabel;
    public JLabel patronymicLabel;
    public JLabel loginLabel;
    public JLabel passwordLabel;
    public JLabel passwordConfirmLabel;
    public JLabel phoneLabel;
    public JLabel expirienceLabel;
    public JLabel info;
    public JComponent background;


    public JTextField login;
    public JPasswordField password;
    public JPasswordField passwordConfirm;
    public JTextField lastName;
    public JTextField firstName;
    public JTextField patronymic;
    public JTextField phone;
    public JTextField expirience;

    public JButton cancel;
    public JButton entry;

    private Registration(JFrame owner) {
        super(owner, "Регистрация", true);
        this.setBounds(owner.getBounds().x, owner.getBounds().y, 360, 500);
        this.setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                login.setText("");
                password.setText("");
                passwordConfirm.setText("");
                lastName.setText("");
                firstName.setText("");
                patronymic.setText("");
                phone.setText("");
                expirience.setText("");
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
                graphics.drawImage(img, 0, 0, 360, 500, null);
            }
        };
        this.setContentPane(background);
        info = new JLabel();
        info.setHorizontalAlignment(JLabel.CENTER);
        loginLabel = new JLabel("<html><h3><i><center> Введите логин </center></i></h3></html>");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        loginLabel.setForeground(Color.WHITE);
        passwordLabel = new JLabel("<html><h3><i><center> Введите пароль </center></i></h3></html>");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel.setForeground(Color.WHITE);
        passwordConfirmLabel = new JLabel("<html><h3><i><center> Подтвердить пароль </center></i></h3></html>");
        passwordConfirmLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordConfirmLabel.setForeground(Color.WHITE);
        lastNameLabel = new JLabel("<html><h3><i><center> Фамилия </center></i></h3></html>");
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);
        lastNameLabel.setForeground(Color.WHITE);
        firstNameLabel = new JLabel("<html><h3><i><center> Имя </center></i></h3></html>");
        firstNameLabel.setHorizontalAlignment(JLabel.CENTER);
        firstNameLabel.setForeground(Color.WHITE);
        patronymicLabel = new JLabel("<html><h3><i><center> Отчество </center></i></h3></html>");
        patronymicLabel.setHorizontalAlignment(JLabel.CENTER);
        patronymicLabel.setForeground(Color.WHITE);
        phoneLabel = new JLabel("<html><h3><i><center> Номер телефона </center></i></h3></html>");
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneLabel.setForeground(Color.WHITE);
        expirienceLabel = new JLabel("<html><h3><i><center> Опыт работы </center></i></h3></html>");
        expirienceLabel.setHorizontalAlignment(JLabel.CENTER);
        expirienceLabel.setForeground(Color.WHITE);

        login = new JTextField();
        login.setHorizontalAlignment(JTextField.CENTER);
        password = new JPasswordField();
        password.setHorizontalAlignment(JTextField.CENTER);
        passwordConfirm = new JPasswordField();
        passwordConfirm.setHorizontalAlignment(JTextField.CENTER);
        lastName = new JTextField();
        lastName.setHorizontalAlignment(JTextField.CENTER);
        firstName = new JTextField();
        firstName.setHorizontalAlignment(JTextField.CENTER);
        patronymic = new JTextField();
        patronymic.setHorizontalAlignment(JTextField.CENTER);
        phone = new JTextField();
        phone.setHorizontalAlignment(JTextField.CENTER);
        expirience = new JTextField();
        expirience.setHorizontalAlignment(JTextField.CENTER);


        cancel = new JButton("Отмена");
        cancel.addActionListener(handler);
        entry = new JButton("Зарегестрировать");
        entry.addActionListener(handler);

        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(10, 10, 10, 10);
        con.fill = GridBagConstraints.HORIZONTAL;

        loginLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 1;
        con.gridheight = 1;
        add(loginLabel, con);

        login.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 1;
        con.gridheight = 1;
        add(login, con);

        passwordLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 2;
        con.gridheight = 1;
        add(passwordLabel, con);

        password.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 2;
        con.gridheight = 1;
        add(password, con);

        passwordConfirmLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(passwordConfirmLabel, con);

        passwordConfirm.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 3;
        con.gridheight = 1;
        add(passwordConfirm, con);

        lastNameLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(lastNameLabel, con);

        lastName.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 4;
        con.gridheight = 1;
        add(lastName, con);

        firstNameLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 5;
        con.gridheight = 1;
        add(firstNameLabel, con);

        firstName.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 5;
        con.gridheight = 1;
        add(firstName, con);

        patronymicLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 6;
        con.gridheight = 1;
        add(patronymicLabel, con);

        patronymic.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 6;
        con.gridheight = 1;
        add(patronymic, con);

        phoneLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 7;
        con.gridheight = 1;
        add(phoneLabel, con);

        phone.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 7;
        con.gridheight = 1;
        add(phone, con);

        expirienceLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 8;
        con.gridheight = 1;
        add(expirienceLabel, con);

        expirience.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 8;
        con.gridheight = 1;
        add(expirience, con);

        info.setPreferredSize(new Dimension(10,10));
        con.gridx = 0;
        con.gridy = 9;
        con.gridwidth = 3;
        con.gridheight = 1;
        add(info, con);

        entry.setPreferredSize(new Dimension(50, 30));
        con.gridx = 0;
        con.gridy = 10;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(entry, con);

        cancel.setPreferredSize(new Dimension(50, 30));
        con.gridx = 1;
        con.gridy = 10;
        con.gridheight = 1;
        add(cancel, con);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                info.setText("");
                login.setBackground(Color.WHITE);
                password.setBackground(Color.WHITE);
                firstName.setBackground(Color.WHITE);
                lastName.setBackground(Color.WHITE);
                patronymic.setBackground(Color.WHITE);
                phone.setBackground(Color.WHITE);
                expirience.setBackground(Color.WHITE);
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
                setVisible(false);
                login.setText("");
                password.setText("");
                passwordConfirm.setText("");
                lastName.setText("");
                firstName.setText("");
                patronymic.setText("");
                phone.setText("");
                expirience.setText("");
                Window window = Window.getInstance();
                window.setVisible(true);

            }
            if (act.getSource() == entry) {
                if (ValidationData.checkLogin(login.getText()) && ValidationData.checkPassword(password.getPassword()) &&
                        ValidationData.checkNameSurnamePatronymic(patronymic.getText()) && ValidationData.checkNameSurnamePatronymic(firstName.getText()) && ValidationData.checkNameSurnamePatronymic(lastName.getText()) &&
                        ValidationData.checkPhone(phone.getText()) && ValidationData.checkExpirience(expirience.getText())) {
                    if (new String(password.getPassword()).equals(new String(passwordConfirm.getPassword()))) {
                        Teacher teacher = new Teacher(login.getText(), String.valueOf(password.getPassword()), firstName.getText(),
                                lastName.getText(),patronymic.getText(), phone.getText(), Integer.parseInt(expirience.getText()));
                        MyMessage myMessage = new MyMessage(teacher);
                        myMessage.setMessageType(MessageType.ACCOUNT_REGISTRATION);
                        ClientRunner.clientThread.flag = 0;
                        ClientRunner.clientThread.setMyMessage(myMessage);

                        while (ClientRunner.clientThread.flag == 0) {
                        }

                        System.out.println(ClientRunner.clientThread.getMyMessage().getMessageType());
                        if (ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.SUCCESSFUL) {
                            setVisible(false);
                            login.setText("");
                            password.setText("");
                            passwordConfirm.setText("");
                            lastName.setText("");
                            firstName.setText("");
                            patronymic.setText("");
                            phone.setText("");
                            expirience.setText("");
                            Window window = Window.getInstance();
                            window.setVisible(true);
                            window.welcome.setText("Регистрация прошла успешно");
                        } else {
                            if(ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.SAME_LOGINS) {
                                info.setBackground(Color.red);
                                info.setText("Пользователь с таким логином уже существует");
                            }
                            else{
                                info.setBackground(Color.red);
                                info.setText("Данный номер телефона уже зарегистрирован в базе");
                            }
                        }
                        ClientRunner.clientThread.flag = 0;
                    } else {
                        info.setBackground(Color.red);
                        info.setText("Введенные пароли не совпадают!");
                    }
                } else {
                    if (!ValidationData.checkPhone(phone.getText())) {
                        phone.setBackground(Color.red);
                    }
                    if (!ValidationData.checkNameSurnamePatronymic(firstName.getText())) {
                        firstName.setBackground(Color.red);
                    }
                    if (!ValidationData.checkNameSurnamePatronymic(lastName.getText())) {
                        lastName.setBackground(Color.red);
                    }
                    if (!ValidationData.checkNameSurnamePatronymic(patronymic.getText())) {
                        patronymic.setBackground(Color.red);
                    }
                    if (!ValidationData.checkLogin(login.getText())) {
                        login.setBackground(Color.red);
                    }
                    if (!ValidationData.checkPassword(password.getPassword())) {
                        password.setBackground(Color.red);
                    }
                    if (!ValidationData.checkExpirience(expirience.getText())) {
                        expirience.setBackground(Color.red);
                    }
                }
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static Registration INSTANCE = new Registration(Window.getInstance());
    }

    public static Registration getInstance() {
        return Registration.SingletonHolder.INSTANCE;
    }

}

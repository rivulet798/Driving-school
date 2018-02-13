package gui.dialog;

import client.ClientRunner;
import entity.ext.Student;
import gui.menu.StudentMenu;
import message.MessageType;
import message.MyMessage;
import validation.ValidationData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RedactStudent extends JDialog {
    public JLabel loginLabel;
    public JLabel passwordLabel;
    public JLabel firstNameLabel;
    public JLabel lastNameLabel;
    public JLabel patronymicLabel;
    public JLabel phoneLabel;
    public JLabel info;

    public JTextField login;
    public JTextField password;
    public JTextField firstName;
    public JTextField lastName;
    public JTextField patronymic;
    public JTextField phone;

    public JButton cancel;
    public JButton redact;

    private MyHandler handler = new MyHandler();

    private RedactStudent(JFrame owner) {
        this.setSize(300, 650);
        this.setLocation(500,50);
        this.setResizable(false);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                StudentMenu studentMenu = StudentMenu.getInstance();
                studentMenu.setVisible(true);
                setVisible(false);
            }
        });

        loginLabel = new JLabel("<html><h3><i><center> Логин </center></i></h3></html>");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel = new JLabel("<html><h3><i><center> Пароль </center></i></h3></html>");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        lastNameLabel = new JLabel("<html><h3><i><center> Фамилия </center></i></h3></html>");
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);
        firstNameLabel = new JLabel("<html><h3><i><center> Имя </center></i></h3></html>");
        firstNameLabel.setHorizontalAlignment(JLabel.CENTER);
        patronymicLabel = new JLabel("<html><h3><i><center> Отчество </center></i></h3></html>");
        patronymicLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneLabel = new JLabel("<html><h3><i><center> Телефон </center></i></h3></html>");
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        info = new JLabel("<html><h3><i><center>  </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);

        login = new JTextField(((Student) ClientRunner.clientThread.getCurrentUser()).getLogin());
        login.setHorizontalAlignment(JTextField.CENTER);
        password = new JPasswordField(((Student) ClientRunner.clientThread.getCurrentUser()).getPassword());
        password.setHorizontalAlignment(JPasswordField.CENTER);
        lastName = new JTextField(((Student) ClientRunner.clientThread.getCurrentUser()).getLastName());
        lastName.setHorizontalAlignment(JPasswordField.CENTER);
        firstName = new JTextField(((Student) ClientRunner.clientThread.getCurrentUser()).getFirstName());
        firstName.setHorizontalAlignment(JPasswordField.CENTER);
        patronymic = new JTextField(((Student) ClientRunner.clientThread.getCurrentUser()).getPatronymic());
        patronymic.setHorizontalAlignment(JPasswordField.CENTER);
        phone = new JTextField(((Student) ClientRunner.clientThread.getCurrentUser()).getPhone());
        phone.setHorizontalAlignment(JPasswordField.CENTER);

        cancel = new JButton("Отмена");
        cancel.addActionListener(handler);
        redact = new JButton("Редактировать");
        redact.addActionListener(handler);

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

        lastName.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 3;
        con.gridheight = 1;
        add(lastName, con);

        lastNameLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(lastNameLabel, con);

        firstName.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 4;
        con.gridheight = 1;
        add(firstName, con);

        firstNameLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(firstNameLabel, con);

        patronymic.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 5;
        con.gridheight = 1;
        add(patronymic, con);

        patronymicLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 5;
        con.gridheight = 1;
        add(patronymicLabel, con);

        phone.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 6;
        con.gridheight = 1;
        add(phone, con);

        phoneLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 6;
        con.gridheight = 1;
        add(phoneLabel, con);

        info.setPreferredSize(new Dimension(300, 25));
        con.gridx = 0;
        con.gridy = 8;
        con.weightx = 2;
        con.gridheight = 1;
        add(info, con);

        redact.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 9;
        con.weightx = 1;
        con.gridheight = 1;
        add(redact, con);

        cancel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 9;
        con.gridheight = 1;
        add(cancel, con);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                info.setText("");
                login.setBackground(Color.WHITE);
                password.setBackground(Color.WHITE);
                lastName.setBackground(Color.WHITE);
                firstName.setBackground(Color.WHITE);
                patronymic.setBackground(Color.WHITE);
                phone.setBackground(Color.WHITE);
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
                StudentMenu studentMenu = StudentMenu.getInstance();
                studentMenu.setVisible(true);
                setVisible(false);
            }
            if (act.getSource() == redact) {
                if (ValidationData.checkLogin(login.getText()) && ValidationData.checkPassword(password.getText().toCharArray()) &&
                        ValidationData.checkNameSurnamePatronymic(patronymic.getText()) && ValidationData.checkNameSurnamePatronymic(firstName.getText()) && ValidationData.checkNameSurnamePatronymic(lastName.getText()) &&
                        ValidationData.checkPhone(phone.getText())) {
                    Student student = new Student(((Student) ClientRunner.clientThread.getCurrentUser()).getId(), login.getText(), password.getText(), lastName.getText(),
                            firstName.getText(), patronymic.getText(), phone.getText(), ((Student) ClientRunner.clientThread.getCurrentUser()).getGroupNumber());
                    MyMessage myMessage = new MyMessage(student);
                    myMessage.setMessageType(MessageType.REDACT_STUDENT);
                    ClientRunner.clientThread.flag = 0;
                    ClientRunner.clientThread.setMyMessage(myMessage);

                    while (ClientRunner.clientThread.flag == 0) {
                    }

                    if (ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.REDACT_SUCCESS) {
                        info.setText(MessageType.REDACT_SUCCESS.toString());
                    } else {
                        info.setText("Ошибка редактирования");
                    }
                    ClientRunner.clientThread.flag = 0;
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
                    if (!ValidationData.checkPassword(password.getText().toCharArray())) {
                        password.setBackground(Color.red);
                    }
                }
            }
        }
    }
    private static class SingletonHolder { // nested class
        private final static RedactStudent INSTANCE = new RedactStudent(StudentMenu.getInstance());
    }

    public static RedactStudent getInstance() {
        return RedactStudent.SingletonHolder.INSTANCE;
    }
}


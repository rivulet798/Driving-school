package gui.table.student;

import client.ClientRunner;
import message.MessageType;
import message.MyMessage;
import validation.ValidationData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddStudent extends JDialog {
    public JLabel loginLabel;
    public JLabel passwordLabel;
    public JLabel passwordConfirmLabel;
    public JLabel firstNameLabel;
    public JLabel lastNameLabel;
    public JLabel patronymicLabel;
    public JLabel phoneLabel;
    public JLabel groupNumberLabel;
    public JLabel info;

    public JTextField login;
    public JPasswordField password;
    public JPasswordField passwordConfirm;
    public JTextField firstName;
    public JTextField lastName;
    public JTextField patronymic;
    public JTextField phone;
    public JComboBox groupNumber;

    public JButton cancel;
    public JButton add;

    String[] groups;

    public int rowNumber;

    private AddStudent.MyHandler handler = new AddStudent.MyHandler();

    private AddStudent(JFrame owner) {
        super(owner, "Добавление", true);
        this.setBounds(owner.getBounds().x, owner.getBounds().y, 300, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        loginLabel = new JLabel("<html><h3><i><center> Логин </center></i></h3></html>");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel = new JLabel("<html><h3><i><center> Пароль </center></i></h3></html>");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordConfirmLabel = new JLabel("<html><h3><i><center> Подтвердите Пароль </center></i></h3></html>");
        passwordConfirmLabel.setHorizontalAlignment(JLabel.CENTER);
        lastNameLabel = new JLabel("<html><h3><i><center> Фамилия </center></i></h3></html>");
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);
        firstNameLabel = new JLabel("<html><h3><i><center> Имя </center></i></h3></html>");
        firstNameLabel.setHorizontalAlignment(JLabel.CENTER);
        patronymicLabel = new JLabel("<html><h3><i><center> Отчество </center></i></h3></html>");
        patronymicLabel.setHorizontalAlignment(JLabel.CENTER);
        phoneLabel = new JLabel("<html><h3><i><center> Телефон </center></i></h3></html>");
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        groupNumberLabel = new JLabel("<html><h3><i><center> Номер группы </center></i></h3></html>");
        groupNumberLabel.setHorizontalAlignment(JLabel.CENTER);

        info = new JLabel("<html><h3><i><center> Введите данные </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);


        login = new JTextField();
        login.setHorizontalAlignment(JTextField.CENTER);
        password = new JPasswordField();
        password.setHorizontalAlignment(JPasswordField.CENTER);
        passwordConfirm = new JPasswordField();
        passwordConfirm.setHorizontalAlignment(JPasswordField.CENTER);
        lastName = new JTextField();
        lastName.setHorizontalAlignment(JTextField.CENTER);
        firstName = new JTextField();
        firstName.setHorizontalAlignment(JTextField.CENTER);
        patronymic = new JTextField();
        patronymic.setHorizontalAlignment(JTextField.CENTER);
        phone =  new JTextField();
        phone.setHorizontalAlignment(JTextField.CENTER);

        groups = new String[1];
        groups[0] = "";
        groupNumber = new JComboBox(groups);
        groupNumber.setAlignmentX(LEFT_ALIGNMENT);
        ActionListener actionListener1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        };
        groupNumber.addActionListener(actionListener1);


        cancel = new JButton("Отмена");
        cancel.addActionListener(handler);
        add = new JButton("Добавить");
        add.addActionListener(handler);

        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(10, 10, 10, 10);
        con.fill = GridBagConstraints.HORIZONTAL;

        loginLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 1;
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

        passwordConfirmLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(passwordConfirmLabel, con);

        passwordConfirm.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 3;
        con.gridheight = 1;
        add(passwordConfirm, con);

        lastNameLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(lastNameLabel, con);

        lastName.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 4;
        con.gridheight = 1;
        add(lastName, con);

        firstNameLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 5;
        con.gridheight = 1;
        add(firstNameLabel, con);

        firstName.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 5;
        con.gridheight = 1;
        add(firstName, con);

        patronymicLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 6;
        con.gridheight = 1;
        add(patronymicLabel, con);

        patronymic.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 6;
        con.gridheight = 1;
        add(patronymic, con);

        phoneLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 7;
        con.gridheight = 1;
        add(phoneLabel, con);

        phone.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 7;
        con.gridheight = 1;
        add(phone, con);

        groupNumberLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 8;
        con.gridheight = 1;
        add(groupNumberLabel, con);

        groupNumber.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 8;
        con.gridheight = 1;
        add(groupNumber, con);

        info.setPreferredSize(new Dimension(10,25));
        con.gridx = 0;
        con.gridy = 9;
        con.gridwidth = 3;
        con.gridheight = 1;
        add(info, con);

        add.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 10;
        con.weightx = 1;
        con.gridheight = 1;
        add(add, con);

        cancel.setPreferredSize(new Dimension(50, 20));
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
                lastName.setBackground(Color.WHITE);
                firstName.setBackground(Color.WHITE);
                patronymic.setBackground(Color.WHITE);
                phone.setBackground(Color.WHITE);
                groupNumber.setBackground(Color.WHITE);
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
                passwordConfirm.setText("");
                firstName.setText("");
                lastName.setText("");
                patronymic.setText("");
                phone.setText("");
                setVisible(false);
            }
            if (act.getSource() == add) {
                if (ValidationData.checkLogin(login.getText()) && ValidationData.checkPassword(password.getPassword()) &&
                        ValidationData.checkNameSurnamePatronymic(patronymic.getText()) && ValidationData.checkNameSurnamePatronymic(firstName.getText()) && ValidationData.checkNameSurnamePatronymic(lastName.getText()) &&
                        ValidationData.checkPhone(phone.getText())) {
                    if (new String(password.getPassword()).equals(new String(passwordConfirm.getPassword()))) {

                        if(rowNumber==-1) {
                            String[] strings = new String[8];
                            strings[0] = "0";
                            strings[1] = login.getText();
                            strings[2] = String.valueOf(password.getPassword());
                            strings[3] = lastName.getText();
                            strings[4] = firstName.getText();
                            strings[5] = patronymic.getText();
                            strings[6] = phone.getText();
                            String choice = (String)groupNumber.getSelectedItem();
                            String[] splitedChoice = choice.split(" ");
                            int gNumber = Integer.parseInt(splitedChoice[0]);
                            strings[7] = String.valueOf(gNumber);
                            StudentTable teacherTable = StudentTable.getInstance();
                            teacherTable.addRow(strings);
                        }
                        else{
                            StudentTable studentTable = StudentTable.getInstance();
                            String[] strings = new String[8];
                            strings[0] = studentTable.btm.getRow(rowNumber)[0];
                            strings[1] = login.getText();
                            strings[2] = String.valueOf(password.getPassword());
                            strings[3] = lastName.getText();
                            strings[4] = firstName.getText();
                            strings[5] = patronymic.getText();
                            strings[6] = phone.getText();
                            String choice = (String)groupNumber.getSelectedItem();
                            String[] splitedChoice = choice.split(" ");
                            int gNumber = Integer.parseInt(splitedChoice[0]);
                            strings[7] = String.valueOf(gNumber);
                            studentTable.changeRow(strings,rowNumber);
                        }
                    } else {
                        info.setBackground(Color.red);
                        info.setText("Введенные пароли не совпадают!");
                    }
                }else{
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

                }
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static AddStudent INSTANCE = new AddStudent(StudentTable.getInstance());
    }

    public static AddStudent getInstance() {
        return AddStudent.SingletonHolder.INSTANCE;
    }

    public void setGroupCombobox() {
        MyMessage myMessage = new MyMessage(MessageType.ALL_GROUPS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);
        while (ClientRunner.clientThread.flag == 0) {
        }
        if (ClientRunner.clientThread.getMyMessage().getGroups().size() != 0) {
            groupNumber.removeAllItems();
            for (int i = 0; i < ClientRunner.clientThread.getMyMessage().getGroups().size(); i++) {
                groupNumber.addItem(ClientRunner.clientThread.getMyMessage().getGroups().get(i).getGroupNumber()+" "+
                        ClientRunner.clientThread.getMyMessage().getGroups().get(i).getCategory());
            }

            groupNumber.updateUI();
        }else{
            info.setText("Нет свободных групп");
            setVisible(false);
        }
        ClientRunner.clientThread.flag = 0;
    }
}

package gui.windows;

import client.ClientRunner;
import entity.ext.Admin;
import entity.ext.Group;
import entity.ext.Student;
import entity.ext.Teacher;
import gui.menu.StudentMenu;
import gui.table.administrator.AddAdministrator;
import gui.menu.AdminMenu;
import message.MessageType;
import message.MyMessage;
import validation.ValidationData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherInfo extends JDialog {
    public JLabel lastNameLabel;
    public JLabel firstNameLabel;
    public JLabel patronymicLabel;
    public JLabel phoneLabel;
    public JLabel experienceLabel;

    public JLabel lastName;
    public JLabel firstName;
    public JLabel patronymic;
    public JLabel phone;
    public JLabel experience;

    public JLabel info;

    public JButton cancel;

    private MyHandler handler = new MyHandler();

    private TeacherInfo(JFrame owner) {
        super(owner, "Информация об учителе", true);
        this.setLocation(510,120);
        this.setSize( 300, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        lastNameLabel = new JLabel("<html><h3><i><center> ФАМИЛИЯ </center></i></h3></html>");
        lastNameLabel.setHorizontalAlignment(JLabel.CENTER);
        lastName = new JLabel("<html><h3><i><center>"+""+" </center></i></h3></html>");
        lastName.setHorizontalAlignment(JLabel.CENTER);
        firstNameLabel = new JLabel("<html><h3><i><center> ИМЯ </center></i></h3></html>");
        firstNameLabel.setHorizontalAlignment(JLabel.CENTER);
        firstName = new JLabel("<html><h3><i><center>"+ ""+" </center></i></h3></html>");
        firstName.setHorizontalAlignment(JLabel.CENTER);
        patronymicLabel = new JLabel("<html><h3><i><center> ОТЧЕСТВО </center></i></h3></html>");
        patronymicLabel.setHorizontalAlignment(JLabel.CENTER);
        patronymic = new JLabel("<html><h3><i><center>"+""+"</center></i></h3></html>");
        patronymic.setHorizontalAlignment(JLabel.CENTER);
        phoneLabel = new JLabel("<html><h3><i><center> ТЕЛЕФОН </center></i></h3></html>");
        phoneLabel.setHorizontalAlignment(JLabel.CENTER);
        phone = new JLabel("<html><h3><i><center>"+""+"</center></i></h3></html>");
        phone.setHorizontalAlignment(JLabel.CENTER);
        experienceLabel = new JLabel("<html><h3><i><center> ОПЫТ РАБОТЫ </center></i></h3></html>");
        experienceLabel.setHorizontalAlignment(JLabel.CENTER);
        experience = new JLabel("<html><h3><i><center>"+""+"</center></i></h3></html>");
        experience.setHorizontalAlignment(JLabel.CENTER);

        info = new JLabel("<html><h3><i><center>  </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);


        cancel = new JButton("Назад");
        cancel.addActionListener(handler);

        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(20, 10, 20, 10);
        con.fill = GridBagConstraints.HORIZONTAL;

        lastNameLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(lastNameLabel, con);

        lastName.setPreferredSize(new Dimension(60, 20));
        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(lastName, con);

        firstNameLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 2;
        con.gridheight = 1;
        add(firstNameLabel, con);

        firstName.setPreferredSize(new Dimension(60, 20));
        con.gridx = 1;
        con.gridy = 2;
        con.gridheight = 1;
        add(firstName, con);

        patronymicLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(patronymicLabel, con);

        patronymic.setPreferredSize(new Dimension(60, 20));
        con.gridx = 1;
        con.gridy = 3;
        con.gridheight = 1;
        add(patronymic, con);

        phoneLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(phoneLabel, con);

        phone.setPreferredSize(new Dimension(60, 20));
        con.gridx = 1;
        con.gridy = 4;
        con.gridheight = 1;
        add(phone, con);

        experienceLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 5;
        con.gridheight = 1;
        add(experienceLabel, con);

        experience.setPreferredSize(new Dimension(60, 20));
        con.gridx = 1;
        con.gridy = 5;
        con.gridheight = 1;
        add(experience, con);

        info.setPreferredSize(new Dimension(300, 25));
        con.gridx = 0;
        con.gridy = 6;
        con.weightx = 2;
        con.gridheight = 1;
        add(info, con);


        cancel.setPreferredSize(new Dimension(100, 20));
        con.gridx = 0;
        con.gridy = 7;
        con.gridheight = 1;
        add(cancel, con);
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == cancel) {
                StudentMenu studentMenu = StudentMenu.getInstance();
                studentMenu.setVisible(true);
                setVisible(false);
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static TeacherInfo INSTANCE = new TeacherInfo(StudentMenu.getInstance());
    }

    public static TeacherInfo getInstance() {
        return TeacherInfo.SingletonHolder.INSTANCE;
    }

    public void teacherInf(){
        MyMessage myMessage = new MyMessage(MessageType.TEACHER_INFO);
        Group group = new Group(((Student)ClientRunner.clientThread.getCurrentUser()).getGroupNumber());
        myMessage.setGroup(group);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);
        while (ClientRunner.clientThread.flag == 0) {
        }

        Teacher teacher = ClientRunner.clientThread.getMyMessage().getTeacher();

        lastName.setText(teacher.getLastName());
        firstName.setText(teacher.getFirstName());
        patronymic.setText(teacher.getPatronymic());
        phone.setText(teacher.getPhone());
        experience.setText(String.valueOf(teacher.getExperience()));

        ClientRunner.clientThread.flag = 0;
    }
}


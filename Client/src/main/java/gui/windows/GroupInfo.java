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

public class GroupInfo extends JDialog {
    public JLabel groupNumberLabel;
    public JLabel categoryLabel;
    public JLabel dateOfBeginningLabel;
    public JLabel dateOfEndingLabel;

    public JLabel groupNumber;
    public JLabel category;
    public JLabel dateOfBeginning;
    public JLabel dateOfEnding;
    public JLabel info;

    public JButton cancel;

    private MyHandler handler = new MyHandler();

    private GroupInfo(JFrame owner) {
        super(owner, "Информация о группе", true);
        this.setLocation(510,120);
        this.setSize( 300, 500);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        groupNumberLabel = new JLabel("<html><h3><i><center> НОМЕР ГРУППЫ </center></i></h3></html>");
        groupNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        groupNumber = new JLabel("<html><h3><i><center>"+""+" </center></i></h3></html>");
        groupNumber.setHorizontalAlignment(JLabel.CENTER);
        categoryLabel = new JLabel("<html><h3><i><center> КАТЕГОРИЯ </center></i></h3></html>");
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        category = new JLabel("<html><h3><i><center>"+ ""+" </center></i></h3></html>");
        category.setHorizontalAlignment(JLabel.CENTER);
        dateOfBeginningLabel = new JLabel("<html><h3><i><center> ДАТА НАЧАЛА </center></i></h3></html>");
        dateOfBeginningLabel.setHorizontalAlignment(JLabel.CENTER);
        dateOfBeginning = new JLabel("<html><h3><i><center>"+""+"</center></i></h3></html>");
        dateOfBeginning.setHorizontalAlignment(JLabel.CENTER);
        dateOfEndingLabel = new JLabel("<html><h3><i><center> ДАТА ЗАВЕРШЕНИЯ</center></i></h3></html>");
        dateOfEndingLabel.setHorizontalAlignment(JLabel.CENTER);
        dateOfEnding = new JLabel("<html><h3><i><center>"+""+"</center></i></h3></html>");
        dateOfEnding.setHorizontalAlignment(JLabel.CENTER);

        info = new JLabel("<html><h3><i><center>  </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);


        cancel = new JButton("Назад");
        cancel.addActionListener(handler);

        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(20, 10, 20, 10);
        con.fill = GridBagConstraints.HORIZONTAL;

        groupNumberLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(groupNumberLabel, con);

        groupNumber.setPreferredSize(new Dimension(60, 20));
        con.gridx = 1;
        con.gridy = 1;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(groupNumber, con);

        categoryLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 2;
        con.gridheight = 1;
        add(categoryLabel, con);

        category.setPreferredSize(new Dimension(60, 20));
        con.gridx = 1;
        con.gridy = 2;
        con.gridheight = 1;
        add(category, con);

        dateOfBeginningLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(dateOfBeginningLabel, con);

        dateOfBeginning.setPreferredSize(new Dimension(60, 20));
        con.gridx = 1;
        con.gridy = 3;
        con.gridheight = 1;
        add(dateOfBeginning, con);

        dateOfEndingLabel.setPreferredSize(new Dimension(60, 20));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(dateOfEndingLabel, con);

        dateOfEnding.setPreferredSize(new Dimension(60, 20));
        con.gridx = 1;
        con.gridy = 4;
        con.gridheight = 1;
        add(dateOfEnding, con);

        info.setPreferredSize(new Dimension(300, 25));
        con.gridx = 0;
        con.gridy = 5;
        con.weightx = 2;
        con.gridheight = 1;
        add(info, con);


        cancel.setPreferredSize(new Dimension(100, 20));
        con.gridx = 0;
        con.gridy = 6;
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
        private final static GroupInfo INSTANCE = new GroupInfo(StudentMenu.getInstance());
    }

    public static GroupInfo getInstance() {
        return GroupInfo.SingletonHolder.INSTANCE;
    }

    public void groupInf(){
        MyMessage myMessage = new MyMessage(MessageType.GROUP_INFO);
        Student student = new Student(((Student)ClientRunner.clientThread.getCurrentUser()).getIdStudent());
        myMessage.setStudent(student);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);

        while (ClientRunner.clientThread.flag == 0) { }

        Group group = ClientRunner.clientThread.getMyMessage().getGroup();

        groupNumber.setText(String.valueOf(group.getGroupNumber()));
        category.setText(group.getCategory());
        dateOfBeginning.setText(group.getDateOfBeginning());
        dateOfEnding.setText(group.getDateOfEnding());

        ClientRunner.clientThread.flag = 0;
    }
}


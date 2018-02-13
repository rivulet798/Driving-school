package gui.table.group;

import client.ClientRunner;
import message.MessageType;
import message.MyMessage;
import validation.ValidationData;

import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddGroup extends JDialog {
    public JLabel groupNumberLabel;
    public JLabel categoryLabel;
    public JLabel maxNumberOfStudentsLabel;
    public JLabel dateOfBeginningLabel;
    public JLabel idTeacherLabel;
    public JLabel info;

    public JTextField groupNumber;
    public JTextField category;
    public JTextField maxNumberOfStudents;
    public JComboBox idTeacher;

    public UtilDateModel model;
    public JDatePanelImpl datePanel;
    JDatePickerImpl date;

    public JButton cancel;
    public JButton add;

    String[] teachers;

    public int rowNumber;

    private AddGroup.MyHandler handler = new AddGroup.MyHandler();

    private AddGroup(JFrame owner) {
        super(owner, "Добавление группы", true);
        this.setBounds(owner.getBounds().x, owner.getBounds().y, 300, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        groupNumberLabel = new JLabel("<html><h3><i><center> Номер группы </center></i></h3></html>");
        groupNumberLabel.setHorizontalAlignment(JLabel.CENTER);
        categoryLabel = new JLabel("<html><h3><i><center> Категория </center></i></h3></html>");
        categoryLabel.setHorizontalAlignment(JLabel.CENTER);
        maxNumberOfStudentsLabel = new JLabel("<html><h3><i><center> Число студентов </center></i></h3></html>");
        maxNumberOfStudentsLabel.setHorizontalAlignment(JLabel.CENTER);
        dateOfBeginningLabel = new JLabel("<html><h3><i><center> Дата начала </center></i></h3></html>");
        dateOfBeginningLabel.setHorizontalAlignment(JLabel.CENTER);
        idTeacherLabel = new JLabel("<html><h3><i><center> Id учителя </center></i></h3></html>");
        idTeacherLabel.setHorizontalAlignment(JLabel.CENTER);

        info = new JLabel("<html><h3><i><center> Введите данные </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);

        groupNumber = new JTextField();
        groupNumber.setHorizontalAlignment(JTextField.CENTER);
        category = new JTextField();
        category.setHorizontalAlignment(JTextField.CENTER);
        maxNumberOfStudents = new JTextField();
        maxNumberOfStudents.setHorizontalAlignment(JTextField.CENTER);

        teachers = new String[1];
        teachers[0] = "";
        idTeacher = new JComboBox(teachers);
        groupNumber.setAlignmentX(LEFT_ALIGNMENT);
        ActionListener actionListener1 = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        };
        idTeacher.addActionListener(actionListener1);


        model = new UtilDateModel();
        datePanel = new JDatePanelImpl(model);
        date = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        cancel = new JButton("Отмена");
        cancel.addActionListener(handler);
        add = new JButton("Принять");
        add.addActionListener(handler);

        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(10, 10, 10, 10);
        con.fill = GridBagConstraints.HORIZONTAL;

        groupNumberLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 1;
        con.gridheight = 1;
        add(groupNumberLabel, con);

        groupNumber.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 1;
        con.gridheight = 1;
        add(groupNumber, con);

        categoryLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 2;
        con.gridheight = 1;
        add(categoryLabel, con);

        category.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 2;
        con.gridheight = 1;
        add(category, con);

        maxNumberOfStudentsLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(maxNumberOfStudentsLabel, con);

        maxNumberOfStudents.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 3;
        con.gridheight = 1;
        add(maxNumberOfStudents, con);

        dateOfBeginningLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(dateOfBeginningLabel, con);

        date.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 4;
        con.gridheight = 1;
        add(date, con);

        idTeacherLabel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 5;
        con.gridheight = 1;
        add(idTeacherLabel, con);

        idTeacher.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 5;
        con.gridheight = 1;
        add(idTeacher, con);


        info.setPreferredSize(new Dimension(10,25));
        con.gridx = 0;
        con.gridy = 6;
        con.gridwidth = 3;
        con.gridheight = 1;
        add(info, con);

        add.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 7;
        con.weightx = 1;
        con.gridheight = 1;
        add(add, con);

        cancel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 7;
        con.gridheight = 1;
        add(cancel, con);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                info.setText("");
                groupNumber.setBackground(Color.WHITE);
                category.setBackground(Color.WHITE);
                maxNumberOfStudents.setBackground(Color.WHITE);
                idTeacher.setBackground(Color.WHITE);
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
            }
            if (act.getSource() == add) {
                if (ValidationData.checkGroupNumber(groupNumber.getText()) && ValidationData.checkCategory(category.getText()) &&
                        ValidationData.checkMaxNumberOfStudents(maxNumberOfStudents.getText()) && date.getJFormattedTextField().getText().equals("") != true) {
                    if(rowNumber==-1) {
                        String[] strings = new String[6];
                        strings[0] = groupNumber.getText();
                        strings[1] = category.getText();
                        strings[2] = maxNumberOfStudents.getText();
                        strings[3] = date.getJFormattedTextField().getText();
                        strings[4] = calculateDateOfEnding(strings[3]);
                        String choice = (String)idTeacher.getSelectedItem();
                        String[] splitedChoice = choice.split(" ");
                        int idTeach = Integer.parseInt(splitedChoice[0]);
                        strings[5] = String.valueOf(idTeach);

                        GroupTable groupTable = GroupTable.getInstance();
                        groupTable.addRow(strings);
                    }

                    else{
                        if(rowNumber >= 0) {
                            GroupTable groupTable = GroupTable.getInstance();
                            String[] strings = new String[6];
                            strings[0] = groupNumber.getText();
                            strings[1] = category.getText();
                            strings[2] = maxNumberOfStudents.getText();
                            strings[3] = date.getJFormattedTextField().getText();
                            strings[4] = calculateDateOfEnding(strings[3]);
                            String choice = (String) idTeacher.getSelectedItem();
                            String[] splitedChoice = choice.split(" ");
                            int idTeach = Integer.parseInt(splitedChoice[0]);
                            strings[5] = String.valueOf(idTeach);

                            groupTable.changeRow(strings, rowNumber);
                        }
                    }
                }else{
                    if (!ValidationData.checkGroupNumber(groupNumber.getText())) {
                        groupNumber.setBackground(Color.red);
                    }
                    if (!ValidationData.checkCategory(category.getText())) {
                        category.setBackground(Color.red);
                    }
                    if (!ValidationData.checkMaxNumberOfStudents(maxNumberOfStudents.getText())) {
                        maxNumberOfStudents.setBackground(Color.red);
                    }
                    if (date.getJFormattedTextField().equals("")) {
                        date.setBackground(Color.red);
                    }
                }
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static AddGroup INSTANCE = new AddGroup(GroupTable.getInstance());
    }

    public static AddGroup getInstance() {
        return AddGroup.SingletonHolder.INSTANCE;
    }

    private static String calculateDateOfEnding(String dateOfBeginning){
        String array[] = dateOfBeginning.split("-");
        String result = new String();
        int month = Integer.parseInt(array[1]);
        if (month < 10){
            result = array[0]+"-"+array[1]+3+"-"+array[2];
        }
        if(month == 10){
            int year = Integer.parseInt(array[0])+1;
            result = year+"-01-"+array[2];
        }
        if(month == 11){
            int year = Integer.parseInt(array[0])+1;
            result = year+"-02-"+array[2];
        }
        if(month == 12){
            int year = Integer.parseInt(array[0])+1;
            result = year+"-03-"+array[2];
        }
        return result;
    }

    public void setTeacherCombobox() {
        MyMessage myMessage = new MyMessage(MessageType.ALL_TEACHERS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);
        while (ClientRunner.clientThread.flag == 0) {
        }

        if (ClientRunner.clientThread.getMyMessage().getTeachers().size() != 0) {
            idTeacher.removeAllItems();
            for (int i = 0; i < ClientRunner.clientThread.getMyMessage().getTeachers().size(); i++) {
                idTeacher.addItem(ClientRunner.clientThread.getMyMessage().getTeachers().get(i).getIdTeacher()+" "+
                        ClientRunner.clientThread.getMyMessage().getTeachers().get(i).getLastName());
            }
            groupNumber.updateUI();
        }else{
            info.setText("Нет подходящих учителей");
            setVisible(false);
        }
        ClientRunner.clientThread.flag = 0;
    }
}

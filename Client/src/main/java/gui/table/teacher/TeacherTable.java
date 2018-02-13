package gui.table.teacher;

import client.ClientRunner;
import gui.menu.AdminMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class TeacherTable extends JFrame {

    public JButton addButton;
    public JButton deleteButton;
    public JButton redactButton;
    public JButton cancelButton;
    public TeacherTableModel btm;
    public JTable teacherTable;
    public JScrollPane teacherTableScroolPage;
    public JLabel teacherLable;
    private MyHandler myHandler;
    public JLabel info;


    public String type;

    private TeacherTable(String type) {
        this.type = type;
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setLocation(110,80);

        info = new JLabel("<html><h3><i><center>  </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);

        btm = new TeacherTableModel();

        teacherLable = new JLabel("Список учителей");
        teacherLable.setHorizontalAlignment(JLabel.HORIZONTAL);

        teacherTable = new JTable(btm);
        teacherTableScroolPage = new JScrollPane(teacherTable);

        myHandler = new MyHandler();

        this.setLayout(new GridBagLayout());
        addButton = new JButton("Добавить");
        addButton.addActionListener(myHandler);
        deleteButton = new JButton("Удалить");
        deleteButton.addActionListener(myHandler);
        redactButton = new JButton("Редактировать");
        redactButton.addActionListener(myHandler);
        cancelButton = new JButton("Назад");
        cancelButton.addActionListener(myHandler);

        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(5, 5, 5, 5);
        con.fill = GridBagConstraints.HORIZONTAL;

        teacherLable.setPreferredSize(new Dimension(500,30));
        con.gridx = 0;
        con.gridy = 0;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(teacherLable, con);

        teacherTableScroolPage.setPreferredSize(new Dimension(800, 500));
        con.gridx = 0;
        con.gridy = 1;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(teacherTableScroolPage, con);

        info.setPreferredSize(new Dimension(10,25));
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        con.gridheight = 1;
        add(info, con);

        addButton.setPreferredSize(new Dimension(60, 30));
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(addButton, con);

        deleteButton.setPreferredSize(new Dimension(60, 30));
        con.gridx = 1;
        con.gridy = 3;
        con.gridheight = 1;
        add(deleteButton, con);

        redactButton.setPreferredSize(new Dimension(60, 30));
        con.gridx = 2;
        con.gridy = 3;
        con.gridheight = 1;
        add(redactButton, con);

        cancelButton.setPreferredSize(new Dimension(200, 30));
        con.gridx = 3;
        con.gridy = 3;
        con.gridheight = 1;
        add(cancelButton, con);


        this.setVisible(true);
        this.pack();
        this.update();
    }

    public void update(){
        btm.updateData();
        teacherTable.updateUI();
    }

    public void addRow(String[] strings){

        try {

            btm.addData(strings);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        teacherTable.updateUI();
    }

    public void changeRow(String[] strings, int row) {
        try {
            btm.changeRow(strings, row);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        teacherTable.updateUI();
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == addButton) {
                AddTeacher addTeacher = AddTeacher.getInstance();
                addTeacher.rowNumber = -1;
                addTeacher .add.setText("Добавить");
                addTeacher.login.setEnabled(true);
                addTeacher.login.setText("");
                addTeacher.password.setText("");
                addTeacher.passwordConfirm.setText("");
                addTeacher.lastName.setText("");
                addTeacher.firstName.setText("");
                addTeacher.patronymic.setText("");
                addTeacher.phone.setText("");
                addTeacher.experience.setText("");
                addTeacher.phone.setBackground(Color.WHITE);
                addTeacher.login.setBackground(Color.WHITE);
                addTeacher.password.setBackground(Color.WHITE);
                addTeacher.passwordConfirm.setBackground(Color.WHITE);
                addTeacher.lastName.setBackground(Color.WHITE);
                addTeacher.firstName.setBackground(Color.WHITE);
                addTeacher.patronymic.setBackground(Color.WHITE);
                addTeacher.experience.setBackground(Color.WHITE);
                addTeacher.setVisible(true);
                teacherTable.updateUI();
            }
            if(act.getSource() == deleteButton){
                try {
                    int r = teacherTable.getSelectedRow();
                    btm.removeRow(r);
                    teacherTable.updateUI();
                }catch (IndexOutOfBoundsException e){}
            }
            if(act.getSource() == redactButton){
                try {
                    AddTeacher addTeacher = AddTeacher.getInstance();
                    int r = teacherTable.getSelectedRow();
                    addTeacher.rowNumber = r;
                    addTeacher.login.setText(btm.getRow(r)[1]);
                    addTeacher.login.setEnabled(false);
                    addTeacher.password.setText(btm.getRow(r)[2]);
                    addTeacher.passwordConfirm.setText(btm.getRow(r)[2]);
                    addTeacher.lastName.setText(btm.getRow(r)[3]);
                    addTeacher.firstName.setText(btm.getRow(r)[4]);
                    addTeacher.patronymic.setText(btm.getRow(r)[5]);
                    addTeacher.phone.setText(btm.getRow(r)[6]);
                    addTeacher.experience.setText(btm.getRow(r)[7]);
                    addTeacher.phone.setBackground(Color.WHITE);
                    addTeacher.login.setBackground(Color.WHITE);
                    addTeacher.password.setBackground(Color.WHITE);
                    addTeacher.lastName.setBackground(Color.WHITE);
                    addTeacher.firstName.setBackground(Color.WHITE);
                    addTeacher.patronymic.setBackground(Color.WHITE);
                    addTeacher.experience.setBackground(Color.WHITE);
                    addTeacher.add.setText("Редактировать");
                    addTeacher.setVisible(true);
                }catch(IndexOutOfBoundsException e){}
            }
            if(act.getSource() == cancelButton) {
                AdminMenu adminMenu = AdminMenu.getInstance();
                adminMenu.setVisible(true);
                setVisible(false);
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static TeacherTable INSTANCE = new TeacherTable("Автошкола");
    }

    public static TeacherTable getInstance() {
        return TeacherTable.SingletonHolder.INSTANCE;
    }

}


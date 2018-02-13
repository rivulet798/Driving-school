package gui.table.student;

import client.ClientRunner;
import entity.ext.Admin;
import gui.menu.AdminMenu;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class StudentTable extends JFrame {

    public JButton addButton;
    public JButton deleteButton;
    public JButton redactButton;
    public JButton cancelButton;
    public StudentTableModel btm;
    public JTable studentTable;
    public JScrollPane studentTableScroolPage;
    public JLabel studentLable;
    private MyHandler myHandler;

    public String type;

    private StudentTable(String type) {
        this.type = type;
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setLocation(110,80);

        btm = new StudentTableModel();

        studentLable = new JLabel("Список студентов");
        studentLable.setHorizontalAlignment(JLabel.HORIZONTAL);

        studentTable = new JTable(btm);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(btm);
        studentTable.setRowSorter(sorter);

        studentTableScroolPage = new JScrollPane(studentTable);

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

        studentLable.setPreferredSize(new Dimension(500,30));
        con.gridx = 0;
        con.gridy = 0;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(studentLable, con);

        studentTableScroolPage.setPreferredSize(new Dimension(800, 500));
        con.gridx = 0;
        con.gridy = 1;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(studentTableScroolPage, con);

        addButton.setPreferredSize(new Dimension(60, 30));
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(addButton, con);

        deleteButton.setPreferredSize(new Dimension(60, 30));
        con.gridx = 1;
        con.gridy = 2;
        con.gridheight = 1;
        add(deleteButton, con);

        redactButton.setPreferredSize(new Dimension(60, 30));
        con.gridx = 2;
        con.gridy = 2;
        con.gridheight = 1;
        add(redactButton, con);

        cancelButton.setPreferredSize(new Dimension(200, 30));
        con.gridx = 3;
        con.gridy = 2;
        con.gridheight = 1;
        add(cancelButton, con);

        this.setVisible(true);
        this.pack();
        this.update();
    }


    public void update(){
        btm.updateData();
        studentTable.updateUI();
    }

    public void addRow(String[] strings){

        try {

            btm.addData(strings);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch(IndexOutOfBoundsException e){}

        studentTable.updateUI();
    }

    public void changeRow(String[] strings, int row) {
        try {
            btm.changeRow(strings, row);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        studentTable.updateUI();
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == addButton) {
                AddStudent addStudent = AddStudent.getInstance();
                addStudent.rowNumber = -1;
                addStudent.add.setText("Добавить");
                addStudent.login.setEnabled(true);
                addStudent.setGroupCombobox();
                addStudent.login.setText("");
                addStudent.password.setText("");
                addStudent.lastName.setText("");
                addStudent.firstName.setText("");
                addStudent.patronymic.setText("");
                addStudent.phone.setText("");
                addStudent.phone.setBackground(Color.WHITE);
                addStudent.login.setBackground(Color.WHITE);
                addStudent.password.setBackground(Color.WHITE);
                addStudent.lastName.setBackground(Color.WHITE);
                addStudent.firstName.setBackground(Color.WHITE);
                addStudent.patronymic.setBackground(Color.WHITE);

                addStudent.setVisible(true);
                studentTable.updateUI();

            }
            if(act.getSource() == deleteButton){
                try {
                    int r = studentTable.getSelectedRow();
                    btm.removeRow(r);
                    studentTable.updateUI();
                }catch (IndexOutOfBoundsException e){}
            }
            if(act.getSource() == redactButton){
                try {
                    AddStudent addStudent = AddStudent.getInstance();
                    int r = studentTable.getSelectedRow();
                    addStudent.rowNumber = r;
                    addStudent.login.setText(btm.getRow(r)[1]);
                    addStudent.login.setEnabled(false);
                    addStudent.password.setText(btm.getRow(r)[2]);
                    addStudent.passwordConfirm.setText(btm.getRow(r)[2]);
                    addStudent.lastName.setText(btm.getRow(r)[3]);
                    addStudent.firstName.setText(btm.getRow(r)[4]);
                    addStudent.patronymic.setText(btm.getRow(r)[5]);
                    addStudent.phone.setText(btm.getRow(r)[6]);
                    addStudent.setGroupCombobox();
                    addStudent.phone.setBackground(Color.WHITE);
                    addStudent.login.setBackground(Color.WHITE);
                    addStudent.password.setBackground(Color.WHITE);
                    addStudent.lastName.setBackground(Color.WHITE);
                    addStudent.firstName.setBackground(Color.WHITE);
                    addStudent.patronymic.setBackground(Color.WHITE);
                    addStudent.add.setText("Редактировать");
                    addStudent.setVisible(true);
                }catch(IndexOutOfBoundsException e){}
            }
            if(act.getSource() == cancelButton){
                AdminMenu adminMenu = AdminMenu.getInstance();
                adminMenu.setVisible(true);
                setVisible(false);
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static StudentTable INSTANCE = new StudentTable("Автошкола");
    }

    public static StudentTable getInstance() {
        return StudentTable.SingletonHolder.INSTANCE;
    }

}


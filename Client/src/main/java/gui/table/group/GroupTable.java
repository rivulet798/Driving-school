package gui.table.group;

import client.ClientRunner;
import entity.ext.Group;
import gui.menu.AdminMenu;
import message.MessageType;
import message.MyMessage;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class GroupTable extends JFrame {

    public JButton addButton;
    public JButton redactButton;
    public JButton deleteButton;
    public JButton cancelButton;
    public GroupTableModel btm;
    public JTable groupTable;
    public JScrollPane groupTableScroolPage;
    public JLabel groupLable;
    public JLabel info;
    private MyHandler myHandler;

    public String type;

    private GroupTable(String type) {
        this.type = type;
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.setLocationRelativeTo(null);
        this.setLocation(200,80);

        btm = new GroupTableModel();

        groupLable = new JLabel("Список групп");
        groupLable.setHorizontalAlignment(JLabel.HORIZONTAL);

        groupTable = new JTable(btm);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(btm);
        groupTable.setRowSorter(sorter);

        groupTableScroolPage = new JScrollPane(groupTable);

        myHandler = new MyHandler();


        this.setLayout(new GridBagLayout());
        info = new JLabel("<html><h3><i><center>  </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);

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

        groupLable.setPreferredSize(new Dimension(500,30));
        con.gridx = 0;
        con.gridy = 0;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(groupLable, con);

        groupTableScroolPage.setPreferredSize(new Dimension(700, 500));
        con.gridx = 0;
        con.gridy = 1;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(groupTableScroolPage, con);

        info.setPreferredSize(new Dimension(10,25));
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        con.gridheight = 1;
        add(info, con);

        addButton.setPreferredSize(new Dimension(40, 30));
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(addButton, con);

        deleteButton.setPreferredSize(new Dimension(40, 30));
        con.gridx = 1;
        con.gridy = 3;
        con.gridheight = 1;
        add(deleteButton, con);

        redactButton.setPreferredSize(new Dimension(40, 30));
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
        groupTable.updateUI();
    }

    public void addRow(String[] strings){

        try {

            btm.addData(strings);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
        }

        groupTable.updateUI();
    }


    public void changeRow(String[] strings, int row) {
        try {
            btm.changeRow(strings, row);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }catch (IndexOutOfBoundsException e){}
        groupTable.updateUI();
    }




    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == addButton) {
                AddGroup addGroup = AddGroup.getInstance();
                addGroup.rowNumber = -1;
                addGroup.add.setText("Добавить");
                addGroup.groupNumber.setEnabled(true);
                addGroup.setTeacherCombobox();
                addGroup.setVisible(true);
                groupTable.updateUI();
            }
            if(act.getSource() == deleteButton){
                try{
                    int r = groupTable.getSelectedRow();
                    btm.removeRow(r);
                    info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
                    groupTable.updateUI();
                }catch (IndexOutOfBoundsException e){}
            }
            if(act.getSource() == redactButton){
                try {
                    AddGroup addGroup = AddGroup.getInstance();
                    int r = groupTable.getSelectedRow();
                    addGroup.rowNumber = r;
                    addGroup.groupNumber.setText(btm.getRow(r)[0]);
                    addGroup.groupNumber.setEnabled(false);
                    addGroup.category.setText(btm.getRow(r)[1]);
                    addGroup.maxNumberOfStudents.setText(btm.getRow(r)[2]);
                    addGroup.date.setToolTipText(btm.getRow(r)[3]);
                    addGroup.add.setText("Редактировать");
                    addGroup.setTeacherCombobox();
                    addGroup.setVisible(true);
                }catch(IndexOutOfBoundsException e){}
            }
            if(act.getSource() == cancelButton){
                try {
                    setVisible(false);
                    AdminMenu.getInstance().setVisible(true);
                }catch(IndexOutOfBoundsException e){}
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static GroupTable INSTANCE = new GroupTable("Автошкола");
    }

    public static GroupTable getInstance() {
        return GroupTable.SingletonHolder.INSTANCE;
    }

}


package gui.table.administrator;

import client.ClientRunner;
import gui.menu.AdminMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class AdministratorTable extends JFrame {

    public JButton addButton;
    public JButton deleteButton;
    public JButton redactButton;
    public JButton cancelButton;
    public AdministratorTableModel btm;
    public JTable administratorTable;
    public JScrollPane administratorTableScroolPage;
    public JLabel administratorLable;
    private MyHandler myHandler;

    public String type;

    private AdministratorTable(String type) {
        this.type = type;
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(new Dimension(800, 600));
        this.setLocation(300, 80);

        btm = new AdministratorTableModel();

        administratorLable = new JLabel("Список администраторов");
        administratorLable.setHorizontalAlignment(JLabel.HORIZONTAL);

        administratorTable = new JTable(btm);
        administratorTableScroolPage = new JScrollPane(administratorTable);

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

        administratorLable.setPreferredSize(new Dimension(700, 30));
        con.gridx = 0;
        con.gridy = 0;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(administratorLable, con);


        administratorTableScroolPage.setPreferredSize(new Dimension(700, 500));
        con.gridx = 0;
        con.gridy = 1;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(administratorTableScroolPage, con);

        addButton.setPreferredSize(new Dimension(40, 30));
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(addButton, con);

        deleteButton.setPreferredSize(new Dimension(40, 30));
        con.gridx = 1;
        con.gridy = 2;
        con.gridheight = 1;
        add(deleteButton, con);

        redactButton.setPreferredSize(new Dimension(40, 30));
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

    public void update() {
        btm.updateData();
        administratorTable.updateUI();
    }

    public void addRow(String[] strings) {

        try {

            btm.addData(strings);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        administratorTable.updateUI();
    }

    public void changeRow(String[] strings, int row) {
        try {
            btm.changeRow(strings, row);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IndexOutOfBoundsException e){
        }
        administratorTable.updateUI();
    }


    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == addButton) {
                AddAdministrator addAdministrator = AddAdministrator.getInstance();
                addAdministrator.rowNumber = -1;
                addAdministrator.add.setText("Добавить");
                addAdministrator.login.setEnabled(true);
                addAdministrator.login.setText("");
                addAdministrator.password.setText("");
                addAdministrator.login.setBackground(Color.WHITE);
                addAdministrator.password.setBackground(Color.WHITE);
                addAdministrator.setVisible(true);
                administratorTable.updateUI();
            }
            if (act.getSource() == deleteButton) {
                try {
                    int r = administratorTable.getSelectedRow();
                    btm.removeRow(r);
                    administratorTable.updateUI();
                }catch(IndexOutOfBoundsException e){}
            }
            if (act.getSource() == redactButton) {
                try {
                    AddAdministrator addAdministrator = AddAdministrator.getInstance();
                    int r = administratorTable.getSelectedRow();
                    addAdministrator.rowNumber = r;
                    addAdministrator.login.setText(btm.getRow(r)[1]);
                    addAdministrator.login.setEnabled(false);
                    addAdministrator.password.setText(btm.getRow(r)[2]);
                    addAdministrator.add.setText("Редактировать");
                    addAdministrator.login.setBackground(Color.WHITE);
                    addAdministrator.password.setBackground(Color.WHITE);
                    addAdministrator.setVisible(true);
                }catch (IndexOutOfBoundsException e){}
            }
            if(act.getSource() == cancelButton){
                AdminMenu adminMenu = AdminMenu.getInstance();
                adminMenu.setVisible(true);
                setVisible(false);
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static AdministratorTable INSTANCE = new AdministratorTable("Автошкола");
    }

    public static AdministratorTable getInstance() {
        return AdministratorTable.SingletonHolder.INSTANCE;
    }

}


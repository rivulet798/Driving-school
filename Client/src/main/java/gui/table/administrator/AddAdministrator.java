package gui.table.administrator;

import validation.ValidationData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddAdministrator extends JDialog {
    public JLabel loginLabel;
    public JLabel passwordLabel;
    public JLabel info;

    public JTextField login;
    public JTextField password;

    public JButton cancel;
    public JButton add;

    public int rowNumber;

    private AddAdministrator.MyHandler handler = new AddAdministrator.MyHandler();

    private static class SingletonHolder { // nested class
        private final static AddAdministrator INSTANCE = new AddAdministrator(AdministratorTable.getInstance());
    }

    public static AddAdministrator getInstance() {
        return AddAdministrator.SingletonHolder.INSTANCE;
    }

    private AddAdministrator(JFrame owner) {
        super(owner, "Добавление", true);
        this.setBounds(owner.getBounds().x, owner.getBounds().y, 300, 300);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                info.setText("Введите данные");
                login.setBackground(Color.WHITE);
                password.setBackground(Color.WHITE);
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

        loginLabel = new JLabel("<html><h3><i><center> Логин </center></i></h3></html>");
        loginLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordLabel = new JLabel("<html><h3><i><center> Пароль </center></i></h3></html>");
        passwordLabel.setHorizontalAlignment(JLabel.CENTER);
        info = new JLabel("<html><h3><i><center> Введите данные </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);

        login = new JTextField();
        login.setHorizontalAlignment(JTextField.CENTER);
        password = new JPasswordField();
        password.setHorizontalAlignment(JPasswordField.CENTER);

        cancel = new JButton("Отмена");
        cancel.addActionListener(handler);
        add = new JButton("Принять");
        add.addActionListener(handler);

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

        info.setPreferredSize(new Dimension(300, 20));
        con.gridx = 0;
        con.gridy = 3;
        con.weightx = 2;
        con.gridheight = 1;
        add(info, con);

        add.setPreferredSize(new Dimension(50, 20));
        con.gridx = 0;
        con.gridy = 4;
//        con.weightx = 1;
        con.gridheight = 1;
        add(add, con);

        cancel.setPreferredSize(new Dimension(50, 20));
        con.gridx = 1;
        con.gridy = 4;
        con.gridheight = 1;
        add(cancel, con);
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == cancel) {
                login.setText("");
                password.setText("");
                setVisible(false);
            }
            if (act.getSource() == add) {
                if (ValidationData.checkLogin(login.getText()) && ValidationData.checkPassword(password.getText().toCharArray()))
                {
                    if (rowNumber == -1) {
                        String[] strings = new String[4];
                        strings[0] = "0";
                        strings[1] = login.getText();
                        strings[2] = password.getText();
                        AdministratorTable administratorTable = AdministratorTable.getInstance();
                        administratorTable.addRow(strings);
                    } else {
                        AdministratorTable administratorTable = AdministratorTable.getInstance();
                        String[] strings = new String[4];
                        strings[0] = administratorTable.btm.getRow(rowNumber)[0];
                        strings[1] = login.getText();
                        strings[2] = password.getText();
                        administratorTable.changeRow(strings, rowNumber);
                    }
                }
                else{
                    if (!ValidationData.checkLogin(login.getText())) {
                        login.setBackground(Color.red);
                        info.setText("Некорректные данные");
                    }
                    }
                    if (!ValidationData.checkPassword(password.getText().toCharArray())) {
                        password.setBackground(Color.red);
                        info.setText("Некорректные данные");
                    }

                }
        }
    }


}

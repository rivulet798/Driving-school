package gui.menu;

import gui.Window;
import gui.dialog.Authorization;
import gui.dialog.Registration;
import gui.table.administrator.AdministratorTable;
import gui.table.group.GroupTable;
import gui.table.student.StudentTable;
import gui.table.teacher.TeacherTable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class AdminMenu extends JFrame {
    public static final String FILENAME = "/images/menu.png";
    public Font font;
    public JButton adminButton;
    public JButton teacherButton;
    public JButton studentButton;
    public JButton groupButton;
    public JButton exit;
    public JLabel welcome;
    private MyHandler handler = new MyHandler();
    public JComponent background;

    private AdminMenu(String s) {
        super(s);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(600, 500);
        this.setLocationRelativeTo(null);

        background = new JComponent() {
            @Override
            public void paintComponent(Graphics graphics) {
                Image img = null;
                try {
                    img = ImageIO.read(getClass().getResource(FILENAME));
                } catch (Exception e) {
                    System.out.println( e.getMessage());
                }
                graphics.drawImage(img, 0, 0, 600, 500, null);
            }
        };
        this.setContentPane(background);

        font = new Font("TimesRoman", Font.BOLD, 18);

        adminButton = new JButton("Работа с админами");
        adminButton.setFont(font);
        adminButton.addActionListener(handler);

        teacherButton = new JButton("Работа с учителями");
        teacherButton.setFont(font);
        teacherButton.addActionListener(handler);

        studentButton = new JButton("Работа со студентами");
        studentButton.setFont(font);
        studentButton.addActionListener(handler);

        groupButton = new JButton("Работа с группами");
        groupButton.setFont(font);
        groupButton.addActionListener(handler);

        exit = new JButton("Выход из системы");
        exit.setFont(font);
        exit.addActionListener(handler);

        welcome = new JLabel();
        welcome.setText("<html><h2><i> Меню администратора </i></h2></html>");
        welcome.setHorizontalAlignment(JLabel.CENTER);
        welcome.setForeground(Color.WHITE);

        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(20, 100, 20, 100);
        con.fill = GridBagConstraints.HORIZONTAL;

        welcome.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 0;
        con.gridheight = 2;
        add(welcome, con);

        adminButton.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 2;
        con.gridheight = 1;
        add(adminButton, con);

        teacherButton.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(teacherButton, con);

        studentButton.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(studentButton, con);

        groupButton.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 5;
        con.gridheight = 1;
        add(groupButton, con);

        exit.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 6;
        con.gridheight = 1;
        add(exit, con);
    }

    private static class SingletonHolder { // nested class
        private final static AdminMenu INSTANCE = new AdminMenu("АВТОШКОЛА");
    }

    public static AdminMenu getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == adminButton) {
                 setVisible(false);
                 AdministratorTable administratorTable = AdministratorTable.getInstance();
                 administratorTable.update();
                 administratorTable.setVisible(true);
            }
            if(act.getSource() == teacherButton){
                 setVisible(false);
                 TeacherTable teacherTable = TeacherTable.getInstance();
                 teacherTable.update();
                 teacherTable.setVisible(true);
            }
            if(act.getSource() == studentButton){
                setVisible(false);
                StudentTable studentTable = StudentTable.getInstance();
                studentTable.update();
                studentTable.setVisible(true);
            }
            if(act.getSource() == groupButton){
                setVisible(false);
                GroupTable groupTable = GroupTable.getInstance();
                groupTable.update();
                groupTable.setVisible(true);
            }
            if (act.getSource() == exit) {
                setVisible(false);
                gui.Window window = Window.getInstance();
                window.setVisible(true);

            }
        }
    }

}
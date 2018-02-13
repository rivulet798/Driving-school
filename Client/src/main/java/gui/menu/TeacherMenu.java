package gui.menu;


import gui.Window;
import gui.dialog.Authorization;
import gui.dialog.RedactTeacher;
import gui.dialog.Registration;
import gui.table.administrator.AdministratorTable;
import gui.table.group.GroupTable;
import gui.table.student.StudentTable;
import gui.table.teacher.TeacherTable;
import gui.table.teacher.work_with_groups.TeacherGroupTable;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class TeacherMenu extends JFrame {
    public static final String FILENAME = "/images/menu.png";
    public Font font;
    public JButton redactButton;
    public JButton groupButton;
    public JButton exit;
    public JLabel welcome;
    private MyHandler handler = new MyHandler();
    public JComponent background;

    private TeacherMenu(String s) {
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

        redactButton = new JButton("Редактировать данные");
        redactButton.setFont(font);
        redactButton.addActionListener(handler);

        groupButton = new JButton("Просмотр групп");
        groupButton.setFont(font);
        groupButton.addActionListener(handler);

        exit = new JButton("Выход из системы");
        exit.setFont(font);
        exit.addActionListener(handler);

        welcome = new JLabel();
        welcome.setText("<html><h2><i> Меню преподавателя </i></h2></html>");
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

        redactButton.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 2;
        con.gridheight = 1;
        add(redactButton, con);

        groupButton.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(groupButton, con);

        exit.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(exit, con);
    }

    private static class SingletonHolder { // nested class
        private final static TeacherMenu INSTANCE = new TeacherMenu("АВТОШКОЛА");
    }

    public static TeacherMenu getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == redactButton) {
                RedactTeacher.getInstance().setVisible(true);
            }
            if(act.getSource() == groupButton){
                TeacherGroupTable teacherGroupTable = TeacherGroupTable.getInstance();
                teacherGroupTable.update();
                teacherGroupTable.setVisible(true);
                setVisible(false);
            }
            if (act.getSource() == exit) {
                setVisible(false);
                gui.Window window = Window.getInstance();
                window.setVisible(true);
            }
        }
    }

}
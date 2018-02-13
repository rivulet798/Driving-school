package gui.menu;


import client.ClientRunner;
import entity.ext.Student;
import gui.Window;
import gui.dialog.Authorization;
import gui.dialog.RedactStudent;
import gui.dialog.RedactTeacher;
import gui.dialog.Registration;
import gui.table.administrator.AdministratorTable;
import gui.table.group.GroupTable;
import gui.table.student.StudentTable;
import gui.table.teacher.TeacherTable;
import gui.table.teacher.work_with_groups.TeacherGroupTable;
import gui.table.teacher.work_with_lessons.TeacherLessonTable;
import gui.windows.GroupInfo;
import gui.windows.TeacherInfo;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class StudentMenu extends JFrame {
    public static final String FILENAME = "/images/menu.png";
    public Font font;
    public JButton redactButton;
    public JButton lessonButton;
    public JButton teacherInfoButton;
    public JButton groupInfoButton;
    public JButton exit;
    public JLabel welcome;
    private MyHandler handler = new MyHandler();
    public JComponent background;


    private StudentMenu(String s) {
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

        lessonButton = new JButton("Просмотреть успеваемость");
        lessonButton.setFont(font);
        lessonButton.addActionListener(handler);

        teacherInfoButton = new JButton("Информация об учителе");
        teacherInfoButton.setFont(font);
        teacherInfoButton.addActionListener(handler);

        groupInfoButton = new JButton("Информация о группе");
        groupInfoButton.setFont(font);
        groupInfoButton.addActionListener(handler);

        exit = new JButton("Выход из системы");
        exit.setFont(font);
        exit.addActionListener(handler);

        welcome = new JLabel();
        welcome.setText("<html><h2><i> Меню студента </i></h2></html>");
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

        lessonButton.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 3;
        con.gridheight = 1;
        add(lessonButton, con);

        teacherInfoButton.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 4;
        con.gridheight = 1;
        add(teacherInfoButton, con);

        groupInfoButton.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 5;
        con.gridheight = 1;
        add(groupInfoButton, con);

        exit.setPreferredSize(new Dimension(200, 50));
        con.gridx = 0;
        con.gridy = 6;
        con.gridheight = 1;
        add(exit, con);
    }

    private static class SingletonHolder { // nested class
        private final static StudentMenu INSTANCE = new StudentMenu("АВТОШКОЛА");
    }

    public static StudentMenu getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == redactButton) {
                  RedactStudent redactStudent = RedactStudent.getInstance();
                  redactStudent.setVisible(true);

                  setVisible(false);
            }

            if(act.getSource() == lessonButton){
                TeacherLessonTable teacherLessonTable = TeacherLessonTable.getInstance();
                int idStudent = ((Student)ClientRunner.clientThread.getCurrentUser()).getIdStudent();
                teacherLessonTable.update(idStudent);
                setVisible(false);
                teacherLessonTable.changeButton.setEnabled(false);
                teacherLessonTable.fileButton.setEnabled(false);
                teacherLessonTable.setVisible(true);

            }

            if(act.getSource() == teacherInfoButton){
                TeacherInfo teacherInfo = TeacherInfo.getInstance();
                teacherInfo.teacherInf();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setVisible(false);
                teacherInfo.setVisible(true);
            }

            if(act.getSource() == groupInfoButton){
                GroupInfo groupInfo = GroupInfo.getInstance();
                groupInfo.groupInf();
                setVisible(false);
                groupInfo.setVisible(true);
            }

            if (act.getSource() == exit) {
                setVisible(false);
                gui.Window window = Window.getInstance();
                window.setVisible(true);
            }
        }
    }


}
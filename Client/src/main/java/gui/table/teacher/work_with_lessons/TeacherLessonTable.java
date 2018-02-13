package gui.table.teacher.work_with_lessons;


import client.ClientRunner;
import entity.ext.Teacher;
import gui.menu.StudentMenu;
import gui.menu.TeacherMenu;
import gui.table.group.GroupTableModel;
import gui.table.teacher.work_with_students.TeacherStudentTable;
import gui.windows.Diagram;
import message.MessageType;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TeacherLessonTable extends JFrame {

    public JButton changeButton;
    public JButton cancelButton;
    public JButton diagramButton;
    public JButton fileButton;
    public TeacherLessonTableModel btm;
    public JTable teacherLessonTable;
    public JScrollPane teacherLessonTableScroolPage;
    public JLabel teacherLessonLable;
    public JLabel info;
    private MyHandler myHandler;

    private int idCurrentStudent;

    public String type;

    private TeacherLessonTable(String type) {
        this.type = type;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                TeacherStudentTable teacherStudentTable = TeacherStudentTable.getInstance();
                teacherStudentTable.setVisible(true);
            }
        });
        this.setLocationRelativeTo(null);
        this.setLocation(120,80);

        btm = new TeacherLessonTableModel();

        teacherLessonLable = new JLabel("Успеваемость студента");
        teacherLessonLable.setHorizontalAlignment(JLabel.HORIZONTAL);

        teacherLessonTable = new JTable(btm);
        teacherLessonTableScroolPage = new JScrollPane(teacherLessonTable);

        myHandler = new MyHandler();


        this.setLayout(new GridBagLayout());
        info = new JLabel("<html><h3><i><center>  </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);


        changeButton = new JButton("Поставить зачет");
        changeButton.addActionListener(myHandler);

        diagramButton = new JButton("Диаграмма успеваемости");
        diagramButton.addActionListener(myHandler);

        fileButton = new JButton("Запись отчета");
        fileButton.addActionListener(myHandler);

        cancelButton = new JButton("Назад");
        cancelButton.addActionListener(myHandler);

        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(5, 5, 5, 5);
        con.fill = GridBagConstraints.HORIZONTAL;

        teacherLessonLable.setPreferredSize(new Dimension(500,30));
        con.gridx = 0;
        con.gridy = 0;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(teacherLessonLable, con);

        teacherLessonTableScroolPage.setPreferredSize(new Dimension(1000, 500));
        con.gridx = 0;
        con.gridy = 1;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(teacherLessonTableScroolPage, con);

        info.setPreferredSize(new Dimension(10,25));
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        con.gridheight = 1;
        add(info, con);

        changeButton.setPreferredSize(new Dimension(40, 30));
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(changeButton, con);

        diagramButton.setPreferredSize(new Dimension(40, 30));
        con.gridx = 1;
        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(diagramButton, con);

        fileButton.setPreferredSize(new Dimension(40, 30));
        con.gridx = 2;
        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(fileButton, con);

        cancelButton.setPreferredSize(new Dimension(200, 30));
        con.gridx = 3;
        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(cancelButton, con);

        this.setVisible(true);
        this.pack();
    }

    public void changeRow(String[] strings, int row , int idCurrentStudent) {
        try {
            btm.changeRow(strings, row, idCurrentStudent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        teacherLessonTable.updateUI();
    }

    public void update(int idStudent){
        if(ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.DEFAULT){
            info.setText("");
        }
        else {
            info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
        }
            idCurrentStudent = idStudent;
            btm.updateData(idStudent);
            teacherLessonTable.updateUI();

    }



    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == changeButton) {
                try {
                    //здесь будет функция для изменения статуса урока
                    if(ClientRunner.clientThread.getCurrentUser() instanceof Teacher) {
                        int r = teacherLessonTable.getSelectedRow();
                        String lessonName = btm.getRow(r)[0];
                        String status = btm.getRow(r)[1];
                        String[] row = new String[2];
                        row[0] = lessonName;
                        row[1] = status;
                        if (status.equals("Незачет")) {
                            btm.changeRow(row, r, idCurrentStudent);
                            TeacherLessonTable teacherLessonTable = TeacherLessonTable.getInstance();
                            teacherLessonTable.update(idCurrentStudent);
                            teacherLessonTable.setVisible(true);
                        } else {
                            info.setText("Зачет уже поставлен");
                        }
                    }
                    else{
                        info.setText("Зачет может поставить только преподаватель");
                    }

                }catch(IndexOutOfBoundsException e){} catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (act.getSource() == diagramButton) {
                    Diagram diagram = Diagram.getInstance();
                    diagram.pastTestInf();
                    diagram.setVisible(true);
                    setVisible(false);
            }

            if (act.getSource() == fileButton) {
                Diagram diagram = Diagram.getInstance();
                diagram.writeFile();
            }

            if (act.getSource() == cancelButton) {
                if(ClientRunner.clientThread.getCurrentUser() instanceof Teacher) {
                    TeacherStudentTable teacherStudentTable = TeacherStudentTable.getInstance();
                    teacherStudentTable.setVisible(true);
                    setVisible(false);
                }
                else {
                    StudentMenu studentMenu = StudentMenu.getInstance();
                    studentMenu.setVisible(true);
                    setVisible(false);
                }
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static TeacherLessonTable INSTANCE = new TeacherLessonTable("Автошкола");
    }

    public static TeacherLessonTable getInstance() {
        return TeacherLessonTable.SingletonHolder.INSTANCE;
    }

}


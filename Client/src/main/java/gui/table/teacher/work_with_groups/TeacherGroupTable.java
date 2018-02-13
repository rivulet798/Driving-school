package gui.table.teacher.work_with_groups;


import entity.ext.Teacher;
import gui.menu.TeacherMenu;
import gui.table.group.GroupTableModel;
import gui.table.teacher.work_with_students.TeacherStudentTable;
import gui.table.teacher.work_with_students.TeacherStudentTableModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TeacherGroupTable extends JFrame {

    public JButton showButton;
    public JButton cancelButton;
    public TeacherGroupTableModel btm;
    public JTable teacherGroupTable;
    public JScrollPane teacherGroupTableScroolPage;
    public JLabel teacherGroupLable;
    public JLabel info;
    private MyHandler myHandler;

    public String type;

    private TeacherGroupTable(String type) {
        this.type = type;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                TeacherMenu teacherMenu = TeacherMenu.getInstance();
                teacherMenu.setVisible(true);
            }
        });
        this.setLocationRelativeTo(null);
        this.setLocation(120,80);

        btm = new TeacherGroupTableModel();

        teacherGroupLable = new JLabel("Список групп преподавателя");
        teacherGroupLable.setHorizontalAlignment(JLabel.HORIZONTAL);

        teacherGroupTable = new JTable(btm);
        teacherGroupTableScroolPage = new JScrollPane(teacherGroupTable);

        myHandler = new MyHandler();


        this.setLayout(new GridBagLayout());
        info = new JLabel("<html><h3><i><center>  </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);


        showButton = new JButton("Просмотр студентов группы");
        showButton.addActionListener(myHandler);

        cancelButton = new JButton("Назад");
        cancelButton.addActionListener(myHandler);

        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(5, 5, 5, 5);
        con.fill = GridBagConstraints.HORIZONTAL;

        teacherGroupLable.setPreferredSize(new Dimension(500,30));
        con.gridx = 0;
        con.gridy = 0;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(teacherGroupLable, con);

        teacherGroupTableScroolPage.setPreferredSize(new Dimension(1000, 500));
        con.gridx = 0;
        con.gridy = 1;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(teacherGroupTableScroolPage, con);

        info.setPreferredSize(new Dimension(10,25));
        con.gridx = 0;
        con.gridy = 2;
        con.gridwidth = 3;
        con.gridheight = 1;
        add(info, con);

        showButton.setPreferredSize(new Dimension(60, 30));
        con.gridx = 0;
        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(showButton, con);

        cancelButton.setPreferredSize(new Dimension(60, 30));
        con.gridx = 1;
        con.gridy = 3;
        con.gridwidth = 1;
        con.gridheight = 1;
        add(cancelButton, con);

        this.setVisible(true);
        this.pack();
        this.update();
    }


    public void update(){
        btm.updateData();
        teacherGroupTable.updateUI();
    }



    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == showButton) {
                try {
                    int r = teacherGroupTable.getSelectedRow();
                    int groupNumber = Integer.parseInt(btm.getRow(r)[0]);
                    TeacherStudentTable teacherStudentTable = TeacherStudentTable.getInstance();
                    teacherStudentTable.update(groupNumber);
                    teacherStudentTable.setVisible(true);
                    setVisible(false);

                }catch(IndexOutOfBoundsException e){}
            }

            if (act.getSource() == cancelButton) {
                TeacherMenu teacherMenu = TeacherMenu.getInstance();
                teacherMenu.setVisible(true);
                setVisible(false);
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static TeacherGroupTable INSTANCE = new TeacherGroupTable("Автошкола");
    }

    public static TeacherGroupTable getInstance() {
        return TeacherGroupTable.SingletonHolder.INSTANCE;
    }

}


package gui.table.teacher.work_with_students;



import gui.table.teacher.work_with_groups.TeacherGroupTable;
import gui.table.teacher.work_with_lessons.TeacherLessonTable;

import javax.swing.*;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class TeacherStudentTable extends JFrame {

    public JButton showButton;
    public JButton cancelButton;
    public TeacherStudentTableModel btm;
    public JTable teacherStudentTable;
    public JScrollPane teacherStudentTableScroolPage;
    public JLabel teacherStudentLable;
    public JLabel info;
    private MyHandler myHandler;

    public int rowNumber;

    public String type;

    private TeacherStudentTable(String type) {
        this.type = type;
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                TeacherGroupTable teacherGroupTable = TeacherGroupTable.getInstance();
                teacherGroupTable.setVisible(true);
                setVisible(false);
            }
        });
        this.setLocationRelativeTo(null);
        this.setLocation(120,80);

        btm = new TeacherStudentTableModel();

        teacherStudentLable = new JLabel("Список студентов группы");
        teacherStudentLable.setHorizontalAlignment(JLabel.HORIZONTAL);

        teacherStudentTable = new JTable(btm);

        RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(btm);
        teacherStudentTable.setRowSorter(sorter);

        teacherStudentTableScroolPage = new JScrollPane(teacherStudentTable);

        myHandler = new MyHandler();


        this.setLayout(new GridBagLayout());
        info = new JLabel("<html><h3><i><center>  </center></i></h3></html>");
        info.setHorizontalAlignment(JLabel.CENTER);


        showButton = new JButton("Просмотр успеваемости студента");
        showButton.addActionListener(myHandler);

        cancelButton = new JButton("Назад");
        cancelButton.addActionListener(myHandler);

        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(5, 5, 5, 5);
        con.fill = GridBagConstraints.HORIZONTAL;

        teacherStudentLable.setPreferredSize(new Dimension(500,30));
        con.gridx = 0;
        con.gridy = 0;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(teacherStudentLable, con);

        teacherStudentTableScroolPage.setPreferredSize(new Dimension(1000, 500));
        con.gridx = 0;
        con.gridy = 1;
        con.gridheight = 1;
        con.gridwidth = 3;
        add(teacherStudentTableScroolPage, con);

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
    }


    public void update(int numberGroup){
        btm.updateData(numberGroup);
        teacherStudentTable.updateUI();
    }



    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == showButton) {
                try {
                    int r = teacherStudentTable.getSelectedRow();
                    int idSt = Integer.parseInt(btm.getRow(r)[0]);
                    TeacherLessonTable teacherLessonTable = TeacherLessonTable.getInstance();
                    teacherLessonTable.update(idSt);
                    teacherLessonTable.changeButton.setEnabled(true);
                    teacherLessonTable.fileButton.setEnabled(true);
                    teacherLessonTable.setVisible(true);
                    setVisible(false);

                }catch(IndexOutOfBoundsException e){}
            }

            if (act.getSource() == cancelButton) {
                TeacherGroupTable teacherGroupTable = TeacherGroupTable.getInstance();
                teacherGroupTable.setVisible(true);
                setVisible(false);
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static TeacherStudentTable INSTANCE = new TeacherStudentTable("Автошкола");
    }

    public static TeacherStudentTable getInstance() {
        return TeacherStudentTable.SingletonHolder.INSTANCE;
    }

}


package gui.windows;

import client.ClientRunner;
import entity.ext.Admin;
import entity.ext.Group;
import entity.ext.Student;
import entity.ext.Teacher;
import gui.menu.StudentMenu;
import gui.menu.TeacherMenu;
import gui.table.administrator.AddAdministrator;
import gui.menu.AdminMenu;
import gui.table.teacher.work_with_lessons.TeacherLessonTable;
import message.MessageType;
import message.MyMessage;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import validation.ValidationData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class Diagram extends JFrame {

    public DefaultCategoryDataset data;
    public JButton cancel;
    public ChartPanel frame;

    private MyHandler handler = new MyHandler();

    private Diagram(JFrame owner) {
        this.setLocation(250, 120);
        this.setSize(1000, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);


        cancel = new JButton("Назад");
        cancel.addActionListener(handler);

        setLayout(new GridBagLayout());
        GridBagConstraints con = new GridBagConstraints();

        con.weightx = 1;
        con.insets = new Insets(20, 10, 20, 10);
        con.fill = GridBagConstraints.HORIZONTAL;


        cancel.setPreferredSize(new Dimension(100, 20));
        con.gridx = 0;
        con.gridy = 6;
        con.gridheight = 1;
        add(cancel, con);

        data = new DefaultCategoryDataset();

        JFreeChart chart = ChartFactory.createBarChart("Диаграмма ", "Название теста", "Количество зачетов", data, PlotOrientation.VERTICAL, true, true, false);
        frame = new ChartPanel(chart);
        frame.setSize(950, 400);
        frame.setVisible(true);
        add(frame);
    }

    public class MyHandler implements ActionListener {
        public void actionPerformed(ActionEvent act) {
            if (act.getSource() == cancel) {
                if (ClientRunner.clientThread.getCurrentUser() instanceof Teacher) {
                    TeacherLessonTable teacherLessonTable = TeacherLessonTable.getInstance();
                    teacherLessonTable.setVisible(true);
                    setVisible(false);
                } else {
                    StudentMenu studentMenu = StudentMenu.getInstance();
                    studentMenu.setVisible(true);
                    setVisible(false);
                }
            }
        }
    }

    private static class SingletonHolder { // nested class
        private final static Diagram INSTANCE = new Diagram(StudentMenu.getInstance());

    }

    public static Diagram getInstance() {
        return Diagram.SingletonHolder.INSTANCE;
    }

    public void pastTestInf() {

        MyMessage myMessage = new MyMessage(MessageType.PAST_TEST_INFO);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);

        while (ClientRunner.clientThread.flag == 0) {
        }


        ArrayList<Integer> array = (ArrayList<Integer>) ClientRunner.clientThread.getMyMessage().getPastTest();


//        data = new DefaultCategoryDataset();
        data.setValue(array.get(0), "Общие положения", "Общие положения");
        data.setValue(array.get(1), "Права и обязанности водителей", "Права и обязанности водителей");
        data.setValue(array.get(2), "Сигналы регулировщика и светофоров", "Сигналы регулировщика и светофоров");
        data.setValue(array.get(3), "Маневрирование", "Маневрирование");
        data.setValue(array.get(4), "Скорость движения транспортных средств", "Скорость движения транспортных средств");
        data.setValue(array.get(5), "Обгон, встречный разъезд", "Обгон, встречный разъезд");
        data.setValue(array.get(6), "Проезд перекрестков", "Проезд перекрестков");
        data.setValue(array.get(7), "Железнодорожные переезды", "Железнодорожные переезды");
        data.setValue(array.get(8), "Медицина", "Медицина");
        data.setValue(array.get(9), "Перевозка пассажиров", "Перевозка пассажиров");

        ClientRunner.clientThread.flag = 0;
    }

    public void writeFile() {

        MyMessage myMessage = new MyMessage(MessageType.PAST_TEST_INFO);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);

        while (ClientRunner.clientThread.flag == 0) {
        }


        ArrayList<Integer> array = (ArrayList<Integer>) ClientRunner.clientThread.getMyMessage().getPastTest();

        PrintWriter pw = null;
        try {
            String string = "";
            pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("Client/reports/tests.txt"), "UTF-8"));


            pw.println(" Информация об успеваемости студентов: ");
            pw.println("_________________________________________________");
            pw.println("|                      |                        |");
            pw.println("| Название теста       | Количество зачетов     |");
            pw.println("|______________________|________________________|");
            pw.println("|                      |                        |");
            pw.println("| Общие положения      |" + "            " + array.get(0) +"           |");
            pw.println("|______________________|____________________    |");
            pw.println("|                      |                        |");
            pw.println("| Права  водителей     |" + "            " + array.get(1) + "           |");
            pw.println("|______________________|________________________|");
            pw.println("|                      |                        |");
            pw.println("| Сигналы регулировщика|" + "            " + array.get(2) + "           |");
            pw.println("|______________________|________________________|");
            pw.println("|                      |                        |");
            pw.println("| Маневрирование       |" + "            " + array.get(3) + "           |");
            pw.println("|______________________|________________________|");
            pw.println("|                      |                        |");
            pw.println("| Скорость движения ТС |" + "            " + array.get(4) + "           |");
            pw.println("|______________________|________________________|");
            pw.println("|                      |                        |");
            pw.println("| Обгон                |" + "            " + array.get(5) + "           |");
            pw.println("|______________________|________________________|");
            pw.println("|                      |                        |");
            pw.println("| Проезд перекрестков  |" + "            " + array.get(6) + "           |");
            pw.println("|______________________|________________________|");
            pw.println("|                      |                        |");
            pw.println("| Железнодор. переезды |" + "            " + array.get(7) + "           |");
            pw.println("|______________________|________________________|");
            pw.println("|                      |                        |");
            pw.println("| Медицина             |" + "            " + array.get(8) + "           |");
            pw.println("|______________________|________________________|");
            pw.println("|                      |                        |");
            pw.println("| Перевозка пассажиров |" + "            " + array.get(9) + "           |");
            pw.println("|______________________|________________________|");



            pw.close();

            ClientRunner.clientThread.flag = 0;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}


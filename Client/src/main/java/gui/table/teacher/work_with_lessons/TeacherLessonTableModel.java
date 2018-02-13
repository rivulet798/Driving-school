package gui.table.teacher.work_with_lessons;

import client.ClientRunner;
import entity.ext.Group;
import entity.ext.Student;
import entity.ext.Teacher;
import message.MessageType;
import message.MyMessage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class TeacherLessonTableModel extends AbstractTableModel {

    private  int columnCount = 2;
    private ArrayList<String[]> dataArrayList;


    public TeacherLessonTableModel(){
        dataArrayList = new ArrayList<>();
        for(int i =0; i< dataArrayList.size();i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return columnCount;
    }

    @Override
    public String getColumnName(int columnIndex){
        switch (columnIndex){
            case 0: return "Название теста";
            case 1: return "Статус";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public void updateData(int idStudent)  {
        dataArrayList = new ArrayList<String[]>();
        for(int i =0; i< dataArrayList.size();i++){
            dataArrayList.add(new String[getColumnCount()]);
        }

        Student student = new Student(idStudent);
        MyMessage myMessage = new MyMessage(student);
        myMessage.setMessageType(MessageType.LESSONS_OF_STUDENT);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);
        while (ClientRunner.clientThread.flag == 0) {
        }

        for(Map.Entry<String, Integer> lesson : ClientRunner.clientThread.getMyMessage().getLessons().entrySet()){
            String[] rowTable = new String[2];
            rowTable[0] = lesson.getKey();
            if(lesson.getValue() == 0){
                rowTable[1] = "Незачет";
            }
            else {
                rowTable[1] = "Зачет";
            }

            dataArrayList.add(rowTable);
        }
        ClientRunner.clientThread.flag = 0;
    }

    public String[] getRow(int index){
        return dataArrayList.get(index);
    }

    public void clearRow(){
        dataArrayList = new ArrayList<>();
    }

    public void changeRow(String[] row, int index, int idStudent) throws InterruptedException{

        Map<String, Integer> lesson = new HashMap<>();
        int status;
        if(row[1].equals("Незачет")){
            status = 1;

        lesson.put(row[0],status);
        MyMessage message = new MyMessage(lesson);
        Student student = new Student(idStudent);
        message.setStudent(student);
        message.setMessageType(MessageType.REDACT_LESSONS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) { }
        dataArrayList.set(index,row);
        fireTableRowsUpdated(0,getRowCount());
        }
        ClientRunner.clientThread.flag = 0;
    }


}

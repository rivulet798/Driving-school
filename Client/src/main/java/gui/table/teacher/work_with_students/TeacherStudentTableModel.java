package gui.table.teacher.work_with_students;

import client.ClientRunner;
import entity.ext.Group;
import entity.ext.Student;
import entity.ext.Teacher;
import message.MessageType;
import message.MyMessage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TeacherStudentTableModel extends AbstractTableModel {

    private  int columnCount = 5;
    private ArrayList<String[]> dataArrayList;

    public TeacherStudentTableModel(){
        dataArrayList = new ArrayList<String[]>();
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
            case 0: return "#id";
            case 1: return "Фамилия";
            case 2: return "Имя";
            case 3: return "Отчество";
            case 4: return "Телефон";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public void updateData(int groupNumber)  {
        dataArrayList = new ArrayList<String[]>();
        for(int i =0; i< dataArrayList.size();i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
        Group group = new Group(groupNumber);
        MyMessage myMessage = new MyMessage(group);
        myMessage.setMessageType(MessageType.STUDENTS_OF_GROUP);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);
        while (ClientRunner.clientThread.flag == 0) { }
        for(Student student : ClientRunner.clientThread.getMyMessage().getStudents()){

            String[] rowTable = new String[5];
            rowTable[0] = String.valueOf(student.getIdStudent());
            rowTable[1] = student.getLastName();
            rowTable[2] = student.getFirstName();
            rowTable[3] = student.getPatronymic();
            rowTable[4] = student.getPhone();

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
}

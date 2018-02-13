package gui.table.student;

import client.ClientRunner;
import entity.ext.Admin;
import entity.ext.Student;
import entity.ext.Teacher;
import message.MessageType;
import message.MyMessage;
import sun.plugin2.message.Message;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class StudentTableModel extends AbstractTableModel {

    private  int columnCount = 8;
    private ArrayList<String[]> dataArrayList;

    public StudentTableModel(){
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
            case 0: return "#id";
            case 1: return "Логин";
            case 2: return "Пароль";
            case 3: return "Фамилия";
            case 4: return "Имя";
            case 5: return "Отчество";
            case 6: return "Номер телефона";
            case 7: return "Номер группы";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public String[] getRow(int index){
        return dataArrayList.get(index);
    }

    public void updateData()  {
        dataArrayList = new ArrayList<String[]>();
        for(int i =0; i< dataArrayList.size();i++){
            dataArrayList.add(new String[getColumnCount()]);
        }

        MyMessage myMessage = new MyMessage(MessageType.ALL_STUDENTS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);
        while (ClientRunner.clientThread.flag == 0) {
        }
        for(Student student : ClientRunner.clientThread.getMyMessage().getStudents()){
            String[] rowTable = new String[8];
            rowTable[0] = Integer.toString(student.getIdStudent());
            rowTable[1] = student.getLogin();
            rowTable[2] = student.getPassword();
            rowTable[3] = student.getLastName();
            rowTable[4] = student.getFirstName();
            rowTable[5] = student.getPatronymic();
            rowTable[6] = student.getPhone();
            rowTable[7] = Integer.toString(student.getGroupNumber());
            dataArrayList.add(rowTable);
        }
        ClientRunner.clientThread.flag = 0;
    }

    public void addData(String[] row) throws InterruptedException {
        String[] rowTable;
        rowTable = row;
        AddStudent addStudent = AddStudent.getInstance().getInstance();
        String choice = (String)addStudent.groupNumber.getSelectedItem();
        String[] splitedChoice = choice.split(" ");
        int gNumber = Integer.parseInt(splitedChoice[0]);
        MyMessage message = new MyMessage(new Student(addStudent.login.getText(),addStudent.password.getText(),
                addStudent.lastName.getText(), addStudent.firstName.getText(),addStudent.patronymic.getText(),
                addStudent.phone.getText(), gNumber));
        message.setMessageType(MessageType.ADD_STUDENT);
        ClientRunner.clientThread.flag = 0;
        System.out.println(ClientRunner.clientThread);
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        Student student = ClientRunner.clientThread.getMyMessage().getStudent();
        if(student == null){
            AddStudent.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
        }
        else{
            AddStudent.getInstance().info.setText(MessageType.SUCCESSFUL.toString());
            int id = student.getIdStudent();
            rowTable[0] = Integer.toString(id);
            dataArrayList.add(rowTable);
            fireTableRowsUpdated(0,getRowCount());
            addStudent.login.setText("");
            addStudent.password.setText("");
            addStudent.passwordConfirm.setText("");
            addStudent.firstName.setText("");
            addStudent.lastName.setText("");
            addStudent.patronymic.setText("");
            addStudent.phone.setText("");
        }
        ClientRunner.clientThread.flag = 0;
    }

    public void removeRow(int index){
        String[] s = dataArrayList.get(index);
        MyMessage message = new MyMessage(new Student(Integer.parseInt(s[0]), s[1], s[2],s[3],s[4],s[5],s[6],Integer.parseInt(s[7])));
        message.setMessageType(MessageType.DELETE_STUDENT);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        dataArrayList.remove(index);
        ClientRunner.clientThread.flag = 0; ClientRunner.clientThread.flag = 0;
    }

    public void changeRow(String[] row, int index) throws InterruptedException{

        String[] rowTable;
        rowTable = row;
        AddStudent addStudent = AddStudent.getInstance();
        MyMessage message = new MyMessage(new Student(Integer.parseInt(row[0]),row[1],row[2],row[3],row[4],row[5],row[6],Integer.parseInt(row[7])));
        message.setMessageType(MessageType.REDACT_STUDENTS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        Student student = ClientRunner.clientThread.getMyMessage().getStudent();
        if(ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.SAME_PHONE_NUMBERS ||
                ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.WRONG_GROUP_NUMBER){
            AddStudent.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
    }
        else{
            AddStudent.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
            dataArrayList.set(index,row);
            fireTableRowsUpdated(0,getRowCount());
        }
        ClientRunner.clientThread.flag = 0;
    }
}

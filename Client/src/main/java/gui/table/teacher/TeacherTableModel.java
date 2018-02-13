package gui.table.teacher;

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

public class TeacherTableModel extends AbstractTableModel {

    private  int columnCount = 8;
    private ArrayList<String[]> dataArrayList;

    public TeacherTableModel(){
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
            case 7: return "Опыт работы";
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

        MyMessage myMessage = new MyMessage(MessageType.ALL_TEACHERS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);
        while (ClientRunner.clientThread.flag == 0) {
        }
        for(Teacher teacher : ClientRunner.clientThread.getMyMessage().getTeachers()){
            String[] rowTable = new String[8];
            rowTable[0] = Integer.toString(teacher.getIdTeacher());
            rowTable[1] = teacher.getLogin();
            rowTable[2] = teacher.getPassword();
            rowTable[3] = teacher.getLastName();
            rowTable[4] = teacher.getFirstName();
            rowTable[5] = teacher.getPatronymic();
            rowTable[6] = teacher.getPhone();
            rowTable[7] = Integer.toString(teacher.getExperience());
            dataArrayList.add(rowTable);
        }
        ClientRunner.clientThread.flag = 0;
    }

    public void addData(String[] row) throws InterruptedException {
        String[] rowTable;
        rowTable = row;
        AddTeacher addTeacher = AddTeacher.getInstance().getInstance();
        MyMessage message = new MyMessage(new Teacher(addTeacher.login.getText(),addTeacher.password.getText(),
                addTeacher.lastName.getText(), addTeacher.firstName.getText(),addTeacher.patronymic.getText(),
                addTeacher.phone.getText(), parseInt(addTeacher.experience.getText())));
        message.setMessageType(MessageType.ADD_TEACHER);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        Teacher teacher = ClientRunner.clientThread.getMyMessage().getTeacher();

        if(teacher == null){
            AddTeacher.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
        }
        else{
            AddTeacher.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
            int id = teacher.getIdTeacher();
            rowTable[0] = Integer.toString(id);
            dataArrayList.add(rowTable);
            fireTableRowsUpdated(0,getRowCount());
        }
        ClientRunner.clientThread.flag = 0;
    }

    public void removeRow(int index){
        String[] s = dataArrayList.get(index);
        MyMessage message = new MyMessage(new Teacher(parseInt(s[0]), s[1], s[2],s[3],s[4],s[5],s[6],parseInt(s[7])));
        message.setMessageType(MessageType.DELETE_TEACHER);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        message.setMessageType(ClientRunner.clientThread.getMyMessage().getMessageType());
        if(ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.SUCCESSFUL){
            dataArrayList.remove(index);
        }
        TeacherTable groupTable = TeacherTable.getInstance();
        groupTable.info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
        ClientRunner.clientThread.flag = 0;
    }

    public void changeRow(String[] row, int index) throws InterruptedException{

        String[] rowTable;
        rowTable = row;
        AddTeacher addTeacher = AddTeacher.getInstance();
        MyMessage message = new MyMessage(new Teacher(Integer.parseInt(row[0]),row[1],row[2],row[3],row[4],row[5],row[6],Integer.parseInt(row[7])));
        message.setMessageType(MessageType.REDACT_TEACHERS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        Teacher teacher = ClientRunner.clientThread.getMyMessage().getTeacher();
        if(ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.SAME_PHONE_NUMBERS){
            AddTeacher.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
        }
        else{
            AddTeacher.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
            dataArrayList.set(index,row);
            fireTableRowsUpdated(0,getRowCount());
        }
        ClientRunner.clientThread.flag = 0;
    }
}

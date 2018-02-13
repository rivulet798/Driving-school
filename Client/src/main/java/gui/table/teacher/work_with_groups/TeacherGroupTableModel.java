package gui.table.teacher.work_with_groups;

import client.ClientRunner;
import entity.ext.Group;
import entity.ext.Teacher;
import message.MessageType;
import message.MyMessage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class TeacherGroupTableModel extends AbstractTableModel {

    private  int columnCount = 5;
    private ArrayList<String[]> dataArrayList;

    public TeacherGroupTableModel(){
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
            case 0: return "Номер группы";
            case 1: return "Категория";
            case 2: return "Число студентов";
            case 3: return "Дата начала";
            case 4: return "Дата окончания";
        }
        return "";
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public void updateData()  {
        dataArrayList = new ArrayList<String[]>();
        for(int i =0; i< dataArrayList.size();i++){
            dataArrayList.add(new String[getColumnCount()]);
        }
        Teacher teacher =  (Teacher)ClientRunner.clientThread.getCurrentUser();
        MyMessage myMessage = new MyMessage(teacher);
        myMessage.setMessageType(MessageType.GROUPS_OF_TEACHER);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);
        while (ClientRunner.clientThread.flag == 0) {
        }
        for(Group group : ClientRunner.clientThread.getMyMessage().getGroups()){
            String[] rowTable = new String[5];
            rowTable[0] = Integer.toString(group.getGroupNumber());
            rowTable[1] = group.getCategory();
            rowTable[2] = Integer.toString(group.getMaxNumberOfStudents());
            rowTable[3] = group.getDateOfBeginning();
            rowTable[4] = calculateDateOfEnding(rowTable[3]);

            dataArrayList.add(rowTable);
        }
        ClientRunner.clientThread.flag = 0;
    }


    public String[] getRow(int index){
        return dataArrayList.get(index);
    }

    private static String calculateDateOfEnding(String dateOfBeginning){
        String array[] = dateOfBeginning.split("-");
        String result = new String();
        int month = Integer.parseInt(array[1]);
        if (month < 10){
            month += 3;
            result = array[0]+"-0"+month+"-"+array[2];
        }
        if(month == 10){
            int year = Integer.parseInt(array[0])+1;
            result = year+"-01-"+array[2];
        }
        if(month == 11){
            int year = Integer.parseInt(array[0])+1;
            result = year+"-02-"+array[2];
        }
        if(month == 12){
            int year = Integer.parseInt(array[0])+1;
            result = year+"-03-"+array[2];
        }
        return result;
    }

    public void clearRow(){
        dataArrayList = new ArrayList<>();
    }

}

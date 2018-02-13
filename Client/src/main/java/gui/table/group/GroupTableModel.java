package gui.table.group;

import client.ClientRunner;
import entity.ext.Group;
import message.MessageType;
import message.MyMessage;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class GroupTableModel extends AbstractTableModel {

    private  int columnCount = 6;
    private ArrayList<String[]> dataArrayList;

    public GroupTableModel(){
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
            case 5: return "Учитель";
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

        MyMessage myMessage = new MyMessage(MessageType.ALL_GROUPS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);

        while (ClientRunner.clientThread.flag == 0) {
        }

        for(Group group : ClientRunner.clientThread.getMyMessage().getGroups()){
            String[] rowTable = new String[6];
            rowTable[0] = Integer.toString(group.getGroupNumber());
            rowTable[1] = group.getCategory();
            rowTable[2] = Integer.toString(group.getMaxNumberOfStudents());
            rowTable[3] = group.getDateOfBeginning();
            rowTable[4] = calculateDateOfEnding(rowTable[3]);
            rowTable[5] = Integer.toString(group.getIdTeacher());

            dataArrayList.add(rowTable);
        }
        ClientRunner.clientThread.flag = 0;
    }

    public void changeRow(String[] row, int index) throws InterruptedException{

        String[] rowTable;
        rowTable = row;
        AddGroup addGroup = AddGroup.getInstance();
        MyMessage message = new MyMessage(new Group(Integer.parseInt(row[0]),row[1],Integer.parseInt(row[2]),row[3],row[4],Integer.parseInt(row[5])));
        message.setMessageType(MessageType.REDACT_GROUPS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        Group group = ClientRunner.clientThread.getMyMessage().getGroup();
        if(group == null){
            AddGroup.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
        }
        else{
            AddGroup.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
            dataArrayList.set(index,row);
            fireTableRowsUpdated(0,getRowCount());
        }
        ClientRunner.clientThread.flag = 0;
    }

    public void addData(String[] row) throws InterruptedException {
        String[] rowTable;
        rowTable = row;
        AddGroup addGroup = AddGroup.getInstance().getInstance();
        MyMessage message = new MyMessage(new Group(Integer.parseInt(row[0]),row[1],
                Integer.parseInt(row[2]), row[3],
                calculateDateOfEnding(row[3]),Integer.parseInt(row[5])));
        message.setMessageType(MessageType.ADD_GROUP);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        Group group = ClientRunner.clientThread.getMyMessage().getGroup();
        if(group == null){
            AddGroup.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
        }
        else{
            AddGroup.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
            dataArrayList.add(rowTable);
            fireTableRowsUpdated(0,getRowCount());
        }
        ClientRunner.clientThread.flag = 0;
    }

    public void removeRow(int index){
        String[] s = dataArrayList.get(index);
        MyMessage message = new MyMessage(new Group(parseInt(s[0]), s[1], parseInt(s[2]),s[3],s[4],parseInt(s[5])));
        message.setMessageType(MessageType.DELETE_GROUP);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        message.setMessageType(ClientRunner.clientThread.getMyMessage().getMessageType());
        if(ClientRunner.clientThread.getMyMessage().getMessageType() == MessageType.SUCCESSFUL){
            dataArrayList.remove(index);
        }
        GroupTable groupTable = GroupTable.getInstance();
        groupTable.info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
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

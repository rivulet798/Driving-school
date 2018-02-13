package gui.table.administrator;

import client.ClientRunner;
import entity.ext.Admin;
import message.MessageType;
import message.MyMessage;
import sun.plugin2.message.Message;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

import static java.lang.Integer.parseInt;

public class AdministratorTableModel extends AbstractTableModel {

    private  int columnCount = 3;
    private ArrayList<String[]> dataArrayList;

    public AdministratorTableModel(){
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

        MyMessage myMessage = new MyMessage(MessageType.ALL_ADMINS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(myMessage);

        while (ClientRunner.clientThread.flag == 0) {
        }

        for(Admin admin : ClientRunner.clientThread.getMyMessage().getAdmins()){
            String[] rowTable = new String[3];
            rowTable[0] = Integer.toString(admin.getIdAdmin());
            rowTable[1] = admin.getLogin();
            rowTable[2] = admin.getPassword();
            dataArrayList.add(rowTable);
        }
        ClientRunner.clientThread.flag = 0;
    }

    public String[] getRow(int index){
        return dataArrayList.get(index);
    }

    public void addData(String[] row) throws InterruptedException {
        String[] rowTable;
        rowTable = row;
        AddAdministrator addAdministrator = AddAdministrator.getInstance();
        MyMessage message = new MyMessage(new Admin(addAdministrator.login.getText(),addAdministrator.password.getText()));
        message.setMessageType(MessageType.ADD_ADMIN);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);

        while (ClientRunner.clientThread.flag == 0) {
        }

        Admin administrator = ClientRunner.clientThread.getMyMessage().getAdmin();
        if(administrator == null){
            AddAdministrator.getInstance().info.setText(MessageType.SAME_LOGINS.toString());
        }
        else{
            AddAdministrator.getInstance().info.setText(MessageType.SUCCESSFUL.toString());
            int id = administrator.getIdAdmin();
            rowTable[0] = Integer.toString(id);
            dataArrayList.add(rowTable);
            fireTableRowsUpdated(0,getRowCount());
            addAdministrator.login.setText("");
            addAdministrator.password.setText("");
        }

        ClientRunner.clientThread.flag = 0;
    }

    public void removeRow(int index){
        String[] s = dataArrayList.get(index);
        MyMessage message = new MyMessage(new Admin(parseInt(s[0]), s[1], s[2]));
        message.setMessageType(MessageType.DELETE_ADMIN);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);
        while (ClientRunner.clientThread.flag == 0) {
        }
        dataArrayList.remove(index);
        ClientRunner.clientThread.flag = 0;
    }

    public void changeRow(String[] row, int index) throws InterruptedException{

        String[] rowTable;
        rowTable = row;
        AddAdministrator addAdministrator = AddAdministrator.getInstance();
        MyMessage message = new MyMessage(new Admin(row[0],row[1]));
        message.setMessageType(MessageType.REDACT_ADMINS);
        ClientRunner.clientThread.flag = 0;
        ClientRunner.clientThread.setMyMessage(message);

        while (ClientRunner.clientThread.flag == 0) {
        }

        Admin admin = ClientRunner.clientThread.getMyMessage().getAdmin();

        AddAdministrator.getInstance().info.setText(ClientRunner.clientThread.getMyMessage().getMessageType().toString());
        dataArrayList.set(index,row);
        fireTableRowsUpdated(0,getRowCount());

        ClientRunner.clientThread.flag = 0;
    }
}

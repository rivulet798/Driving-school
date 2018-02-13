package client;

import entity.AbstractEntity;
import message.MessageType;
import message.MyMessage;

import javax.xml.bind.SchemaOutputResolver;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientThread extends Thread {
    public volatile int flag;
    private static ObjectOutput oos;
    private ObjectInput ois;
    public Socket clientSocket;
    private MyMessage myMessage;
    private MyMessage defaultMessage;
    private InetAddress address;
    private AbstractEntity currentUser;

    public ClientThread(int port) {
        try {
            flag = 0;
            defaultMessage = new MyMessage();
            defaultMessage.setMessageType(MessageType.DEFAULT);
            myMessage = defaultMessage;
            address = InetAddress.getLocalHost();
            clientSocket = new Socket(address, port);
            oos = new ObjectOutputStream(clientSocket.getOutputStream());
            ois = new ObjectInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public MyMessage getMyMessage() {
        return myMessage;
    }

    public void setMyMessage(MyMessage myMessage) {
        this.myMessage = myMessage;
    }

    public AbstractEntity getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(AbstractEntity currentUser) {
        this.currentUser = currentUser;
    }

    public void run() {
        try {
            String msg;
            do {
                switch (myMessage.getMessageType()) {
                    case CONNECT:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        if(myMessage.getMessageType() == MessageType.IS_CONNECT) {
                            System.out.println(MessageType.CONNECT);
                        }
                        else{
                            throw new RuntimeException();
                        }
                        myMessage = defaultMessage;
                        break;
                    case DISCONNECT:
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case ACCOUNT_REGISTRATION:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case AUTHORIZATION:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case ADD_ADMIN:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case DELETE_ADMIN:
                        oos.writeObject(myMessage);
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case ADD_TEACHER:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case DELETE_TEACHER:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case ADD_STUDENT:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case DELETE_STUDENT:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0){
                        }
                        myMessage = defaultMessage;
                        break;
                    case ADD_GROUP:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case DELETE_GROUP:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case ALL_ADMINS:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        //System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case ALL_STUDENTS:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        //System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case ALL_TEACHERS:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        //System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case ALL_GROUPS:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        //System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case REDACT_ADMINS:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case REDACT_GROUPS:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case REDACT_STUDENTS:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case REDACT_TEACHERS:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case REDACT_TEACHER:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case REDACT_STUDENT:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case GROUPS_OF_TEACHER:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case STUDENTS_OF_GROUP:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case LESSONS_OF_STUDENT:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case REDACT_LESSONS:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case GROUP_INFO:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case TEACHER_INFO:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                    case PAST_TEST_INFO:
                        oos.writeObject(myMessage);
                        myMessage = (MyMessage) ois.readObject();
                        System.out.println(myMessage.getMessageType());
                        flag = 1;
                        while (flag != 0) {
                        }
                        myMessage = defaultMessage;
                        break;
                }
            } while (myMessage.getMessageType() != MessageType.DISCONNECT);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

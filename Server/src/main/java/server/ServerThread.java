package server;

import message.MessageType;
import message.MyMessage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerThread extends Thread {
    private ObjectOutput oos;//передача
    private ObjectInput ois;//чтение
    private Socket socket;
    private MyMessage myMessage;
    private MyMessage defaultMessage;
    private Business business;


    public ServerThread(Socket accept) throws IOException {
        this.socket = accept;
    }

    @Override
    public void run() {
        try {
            System.out.println(socket.getInetAddress().getHostName()+"  " + socket.getInetAddress());
            System.out.println("Порт: " + socket.getLocalPort());

            business = new Business();

            oos = new ObjectOutputStream(socket.getOutputStream());
            ois = new ObjectInputStream(socket.getInputStream());

            defaultMessage = new MyMessage();
            defaultMessage.setMessageType(MessageType.DEFAULT);

            myMessage = defaultMessage;
            do {
                myMessage = (MyMessage) ois.readObject();
                switch (myMessage.getMessageType()) {
                    case CONNECT:
                        myMessage = new MyMessage(MessageType.IS_CONNECT);
                        oos.writeObject(myMessage);
                        System.out.println(MessageType.CONNECT);
                        myMessage = defaultMessage;
                        break;
                    case DISCONNECT:
                        System.out.println("Один клиент отключился");
                        myMessage = defaultMessage;
                        break;
                    case ACCOUNT_REGISTRATION:
                        System.out.println(myMessage.getMessageType());
                        myMessage = new MyMessage(business.AccRegistration(myMessage));
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case AUTHORIZATION:
                        myMessage = business.authorization(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case ADD_ADMIN:
                        myMessage = business.AddAdministrator(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case DELETE_ADMIN:
                        myMessage = business.deleteAdmin(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case ADD_TEACHER:
                        myMessage = business.AddTeacher(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case DELETE_TEACHER:
                        myMessage.setMessageType(business.deleteTeacher(myMessage));
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case ADD_STUDENT:
                        myMessage = business.AddStudent(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case DELETE_STUDENT:
                        myMessage = business.deleteStudent(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case ADD_GROUP:
                        myMessage = business.AddGroup(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case DELETE_GROUP:
                        myMessage.setMessageType(business.deleteGroup(myMessage));
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case ALL_ADMINS:
                        myMessage = business.allAdmins(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case ALL_STUDENTS:
                        myMessage = business.allStudents(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case ALL_TEACHERS:
                        myMessage = business.allTeachers(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case ALL_GROUPS:
                        myMessage = business.allGroups(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case REDACT_ADMINS:
                        myMessage = business.redactAdministrators(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case REDACT_GROUPS:
                        myMessage = business.redactGroups(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case REDACT_STUDENTS:
                        myMessage = business.redactStudents(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case REDACT_TEACHERS:
                        myMessage = business.redactTeachers(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case REDACT_TEACHER:
                        myMessage = business.redactTeacher(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case REDACT_STUDENT:
                        myMessage = business.redactStudent(myMessage);
                        System.out.println(myMessage.getMessageType());
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case GROUPS_OF_TEACHER:
                        myMessage = business.groupsOfTeacher(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case STUDENTS_OF_GROUP:
                        myMessage = business.studentsOfGroup(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case LESSONS_OF_STUDENT:
                        myMessage = business.lessonsOfStudent(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case REDACT_LESSONS:
                        myMessage = business.redactLessons(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case GROUP_INFO:
                        myMessage = business.groupOfStudent(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case TEACHER_INFO:
                        myMessage = business.teacherOfStudent(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                    case PAST_TEST_INFO:
                        myMessage = business.pastTest(myMessage);
                        oos.writeObject(myMessage);
                        myMessage = defaultMessage;
                        break;
                }
            } while (myMessage.getMessageType() != MessageType.DISCONNECT);
        } catch (IOException e) {
            System.out.println("Client disconnected");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
import java.util.*;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.*;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = JOptionPane.showInputDialog("Enter the host address to connect to:", "localhost");
        int port = Integer.parseInt(JOptionPane.showInputDialog("Enter the port number to connect to:", "7777"));

        Socket socket = new Socket(host, port);
        System.out.println("Connected to " + host + ":" + port);

        OutputStream outputStream = socket.getOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        InputStream inputStream = socket.getInputStream();
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        Message messageSend = null;
        Message messageGet;

        String username;
        String password;
        boolean loggedIn = false;

        String[] perms = null;
        String userID = "";
        ArrayList<Chatroom> rooms = null;

        while (!loggedIn) {
            JPanel loginPanel = new JPanel(new GridLayout(2, 2));
            JTextField usernameField = new JTextField();
            JTextField passwordField = new JPasswordField();
            loginPanel.add(new JLabel("Username:"));
            loginPanel.add(usernameField);
            loginPanel.add(new JLabel("Password:"));
            loginPanel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(null, loginPanel, "Login", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                username = usernameField.getText();
                password = passwordField.getText();
                String loginAttempt = username + "-" + password;
                messageSend = new Message(MsgType.LOGIN, MsgStatus.UNDEFINED, loginAttempt, null);

                objectOutputStream.writeObject(messageSend);
                messageGet = (Message) objectInputStream.readObject();

                if (messageGet.getStatus() == MsgStatus.SUCCESS) {
                    String str = "Login success!";
                    System.out.println(str);
                    loggedIn = true;
                    rooms = new ArrayList<>(messageGet.getRooms());
                    perms = messageGet.getText().split("");
                    userID = perms[4];
                    System.out.println(messageGet.getText());
                    JOptionPane.showMessageDialog(null, "Login success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (messageGet.getStatus() == MsgStatus.FAIL) {
                    String str = "Login fail!";
                    System.out.println(str);
                    JOptionPane.showMessageDialog(null, "Login failed! Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // User clicked Cancel or closed the dialog
                System.exit(0);
            }
        }

        boolean loggedOut = false;
        int menuSize = 5;
        String[] chats = {"View All Logs (Admin)", "Anya-Derion Direct Messages", "Anya-Derion-Admin Group Chat", "Anya-Admin Direct Message", "Logout"};
        chats[menuSize - 1] = "Logout";
        int choice;
        
        do {
            choice = JOptionPane.showOptionDialog(null,
                    "Select a Chatroom!",
                    "Communication Program!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    chats,
                    chats[menuSize - 1]);

            switch (choice) {
            case 0: {
                if (Integer.valueOf(perms[0]) == 0) {
                    JOptionPane.showMessageDialog(null, "You do not have access to this chatroom!");
                    break;
                }
                
                // Display messages in the room
                ArrayList<String> logPrint = new ArrayList<>(rooms.get(0).printLogs());
                StringBuilder logMessages = new StringBuilder();
                for (String log : logPrint) {
                    logMessages.append(log).append("\n");
                }

                // Show messages in a dialog
                JOptionPane.showMessageDialog(null, logMessages.toString(), "Messages in Chatroom " + rooms.get(0).getID(), JOptionPane.PLAIN_MESSAGE);
                break;
            }

            case 1: {
                if (Integer.valueOf(perms[1]) == 0) {
                    JOptionPane.showMessageDialog(null, "You do not have access to this chatroom!");
                    break;
                }

                // Display messages in the room
                ArrayList<String> logPrint = new ArrayList<>(rooms.get(1).printLogs());
                StringBuilder logMessages = new StringBuilder();
                for (String log : logPrint) {
                    logMessages.append(log).append("\n");
                }
                
                // Create a panel to hold the message display and input components
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                // Add message display to the panel
                JTextArea messageArea = new JTextArea(10, 30);
                messageArea.setText(logMessages.toString());
                messageArea.setEditable(false);
                panel.add(new JScrollPane(messageArea), BorderLayout.CENTER);

                // Add input field for sending a message
                JTextField messageField = new JTextField(30);
                panel.add(messageField, BorderLayout.SOUTH);

                // Show custom dialog
                int option = JOptionPane.showConfirmDialog(null, panel, "Chat Room", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String newMsg = messageField.getText();
                    String toParse = rooms.get(1).getID() + "-" + userID + "-" + newMsg;
                    Date date = new Date();
                    String finalText = rooms.get(1).getID() + "-" + date.getCurrentDate() + "-" + userID + "-" + newMsg;
                    System.out.println(finalText);

                    // Send the message
                    messageSend = new Message(MsgType.DIRECTMESSAGE, MsgStatus.UNDEFINED, toParse, null);
                    objectOutputStream.writeObject(messageSend);
                    messageGet = (Message) objectInputStream.readObject();
                    JOptionPane.showMessageDialog(null, "Message sent!", "Epic", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            }
            case 2: {
                if (Integer.valueOf(perms[2]) == 0) {
                    JOptionPane.showMessageDialog(null, "You do not have access to this chatroom!");
                    break;
                }

                // Display messages in the room
                ArrayList<String> logPrint = new ArrayList<>(rooms.get(2).printLogs());
                StringBuilder logMessages = new StringBuilder();
                for (String log : logPrint) {
                    logMessages.append(log).append("\n");
                }
                
                // Create a panel to hold the message display and input components
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                // Add message display to the panel
                JTextArea messageArea = new JTextArea(10, 30);
                messageArea.setText(logMessages.toString());
                messageArea.setEditable(false);
                panel.add(new JScrollPane(messageArea), BorderLayout.CENTER);

                // Add input field for sending a message
                JTextField messageField = new JTextField(30);
                panel.add(messageField, BorderLayout.SOUTH);

                // Show custom dialog
                int option = JOptionPane.showConfirmDialog(null, panel, "Chat Room", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String newMsg = messageField.getText();
                    String toParse = rooms.get(2).getID() + "-" + userID + "-" + newMsg;
                    Date date = new Date();
                    String finalText = rooms.get(2).getID() + "-" + date.getCurrentDate() + "-" + userID + "-" + newMsg;
                    System.out.println(finalText);

                    // Send the message
                    messageSend = new Message(MsgType.DIRECTMESSAGE, MsgStatus.UNDEFINED, toParse, null);
                    objectOutputStream.writeObject(messageSend);
                    messageGet = (Message) objectInputStream.readObject();
                    JOptionPane.showMessageDialog(null, "Message sent!", "Epic", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            }
            case 3: {
                if (Integer.valueOf(perms[3]) == 0) {
                    JOptionPane.showMessageDialog(null, "You do not have access to this chatroom!");
                    break;
                }

                // Display messages in the room
                ArrayList<String> logPrint = new ArrayList<>(rooms.get(3).printLogs());
                StringBuilder logMessages = new StringBuilder();
                for (String log : logPrint) {
                    logMessages.append(log).append("\n");
                }
                
                // Create a panel to hold the message display and input components
                JPanel panel = new JPanel();
                panel.setLayout(new BorderLayout());

                // Add message display to the panel
                JTextArea messageArea = new JTextArea(10, 30);
                messageArea.setText(logMessages.toString());
                messageArea.setEditable(false);
                panel.add(new JScrollPane(messageArea), BorderLayout.CENTER);

                // Add input field for sending a message
                JTextField messageField = new JTextField(30);
                panel.add(messageField, BorderLayout.SOUTH);

                // Show custom dialog
                int option = JOptionPane.showConfirmDialog(null, panel, "Chat Room", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String newMsg = messageField.getText();
                    String toParse = rooms.get(3).getID() + "-" + userID + "-" + newMsg;
                    Date date = new Date();
                    String finalText = rooms.get(3).getID() + "-" + date.getCurrentDate() + "-" + userID + "-" + newMsg;
                    System.out.println(finalText);

                    // Send the message
                    messageSend = new Message(MsgType.DIRECTMESSAGE, MsgStatus.UNDEFINED, toParse, null);
                    objectOutputStream.writeObject(messageSend);
                    messageGet = (Message) objectInputStream.readObject();
                    JOptionPane.showMessageDialog(null, "Message sent!", "Epic", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            }

            case 4: {
                messageSend = new Message(MsgType.LOGOUT, MsgStatus.UNDEFINED, "", null);
                objectOutputStream.writeObject(messageSend);
                loggedOut = true;
                break;
            }
            default: {
                // Handle unexpected choices here
                break;
            }
        }


        } while (!loggedOut);

        System.out.println("Closing socket");
        socket.close();
    }
}


        boolean loggedOut = false;
        int menuSize = 5;
        String[] chats = {"View All Logs (Admin)", "Anya-Derion Direct Messages", "Anya-Derion-Admin Group Chat", "Anya-Admin Direct Message", "Logout"};
        chats[menuSize - 1] = "Logout";
        int choice;
        do {
            choice = JOptionPane.showOptionDialog(null,
                    "Select a Chatroom!",
                    "Communication Program!",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.INFORMATION_MESSAGE,
                    null,
                    chats,
                    chats[menuSize - 1]);

            switch (choice) {
            case 0: {
                if (Integer.valueOf(perms[0]) == 0) {
                    JOptionPane.showMessageDialog(null, "You do not have access to this chatroom!");
                    break;
                }
                ArrayList<String> logPrint = new ArrayList<>(rooms.get(0).printLogs());
                for (String log : logPrint) {
                    System.out.println(log);
                }
                break;
            }
            case 1: {
                if (Integer.valueOf(perms[1]) == 0) {
                    JOptionPane.showMessageDialog(null, "You do not have access to this chatroom!");
                    break;
                }
                ArrayList<String> logPrint = new ArrayList<>(rooms.get(1).printLogs());
                for (String log : logPrint) {
                    System.out.println(log);
                }
                String newMsg = JOptionPane.showInputDialog("Send a message:");
                String toParse = rooms.get(1).getID() + "-" + userID + "-" + newMsg;
                Date date = new Date();
                String finalText = rooms.get(1).getID() + "-" + date.getCurrentDate() + "-" + userID + "-" + newMsg;
                System.out.println(finalText);
                messageSend = new Message(MsgType.DIRECTMESSAGE, MsgStatus.UNDEFINED, toParse, null);
                objectOutputStream.writeObject(messageSend);
                messageGet = (Message) objectInputStream.readObject();
                JOptionPane.showMessageDialog(null, "Message sent!", "Epic", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case 2: {
                if (Integer.valueOf(perms[2]) == 0) {
                    JOptionPane.showMessageDialog(null, "You do not have access to this chatroom!");
                    break;
                }
                ArrayList<String> logPrint = new ArrayList<>(rooms.get(2).printLogs());
                for (String log : logPrint) {
                    System.out.println(log);
                }
                String newMsg = JOptionPane.showInputDialog("Send a message:");
                String toParse = rooms.get(2).getID() + "-" + userID + "-" + newMsg;
                Date date = new Date();
                String finalText = rooms.get(2).getID() + "-" + date.getCurrentDate() + "-" + userID + "-" + newMsg;;
                System.out.println(finalText);
                messageSend = new Message(MsgType.DIRECTMESSAGE, MsgStatus.UNDEFINED, toParse, null);
                objectOutputStream.writeObject(messageSend);
                messageGet = (Message) objectInputStream.readObject();
                JOptionPane.showMessageDialog(null, "Message sent!", "Epic", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case 3: {
                if (Integer.valueOf(perms[3]) == 0) {
                    JOptionPane.showMessageDialog(null, "You do not have access to this chatroom!");
                    break;
                }
                ArrayList<String> logPrint = new ArrayList<>(rooms.get(3).printLogs());
                for (String log : logPrint) {
                    System.out.println(log);
                }
                String newMsg = JOptionPane.showInputDialog("Send a message:");
                String toParse = rooms.get(3).getID() + "-" + userID + "-" + newMsg;
                Date date = new Date();
                String finalText = rooms.get(3).getID() + "-" + date.getCurrentDate() + "-" + userID + "-" + newMsg;;
                System.out.println(finalText);
                messageSend = new Message(MsgType.DIRECTMESSAGE, MsgStatus.UNDEFINED, toParse, null);
                objectOutputStream.writeObject(messageSend);
                messageGet = (Message) objectInputStream.readObject();
                JOptionPane.showMessageDialog(null, "Message sent!", "Epic", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
            case 4: {
                messageSend = new Message(MsgType.LOGOUT, MsgStatus.UNDEFINED, "", null);
                objectOutputStream.writeObject(messageSend);
                loggedOut = true;
                break;
            }
            default: {
                // Handle unexpected choices here
                break;
            }
        }


        } while (!loggedOut);

        System.out.println("Closing socket");
        socket.close();
    }
}

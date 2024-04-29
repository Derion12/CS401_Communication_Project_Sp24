import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Create a ServerSock on localhost:7777
        ServerSocket ss = new ServerSocket(7777);
        System.out.println("ServerSocket awaiting connections...");

        // .accept blocks until an inbound connection on this port is attempted
        Socket socket = ss.accept();
        System.out.println("Connection from " + socket + "!");

        // Output stream socket.
        OutputStream outputStream = socket.getOutputStream();

        // Create object output stream from the output stream to send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        
        // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();

        // create a ObjectInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

        // read the list of messages from the socket
        List<Message> listOfMessages = (List<Message>) objectInputStream.readObject();
        System.out.println("Received [" + listOfMessages.size() + "] messages from: " + socket);

        // for every message, call printMessage(message);
        System.out.println("All messages:");
        listOfMessages.forEach(msg -> printMessage(msg));

        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
    }
    
    private static void printMessage(Message msg){
        System.out.println("ID: " + msg.getID());
        System.out.println("Type: " + msg.getType());
        System.out.println("Text: " + msg.getText());
    }

}

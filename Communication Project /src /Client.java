import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Scanner sc= new Scanner(System.in); //System.in is a standard input stream.
        System.out.print("Enter the port number to connect to: <7777>");
        int port = sc.nextInt();
        System.out.print("Enter the host address to connect to: <localhost> ");
        String host = sc.next();

        // Connect to the ServerSocket at host:port
        Socket socket = new Socket(host, port);
        System.out.println("Connected to " + host + ":" + port);

        // Output stream socket.
        OutputStream outputStream = socket.getOutputStream();

        // Create object output stream from the output stream to send an object through it
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);

     // get the input stream from the connected socket
        InputStream inputStream = socket.getInputStream();

        // create a ObjectInputStream so we can read data from it.
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        
        // List of Message objects
        Message messageToSend;
        MsgType type = MsgType.DIRECTMESSAGE;
        MsgStatus stat = MsgStatus.UNDEFINED;
        String msg = "";
        messageToSend = new Message(type,stat,msg);

        messageToSend = new Message(type,stat,msg);

        System.out.println("Sending Message Objects");
        objectOutputStream.writeObject(messageToSend);

        System.out.println("Closing socket");
        socket.close();
    }
}



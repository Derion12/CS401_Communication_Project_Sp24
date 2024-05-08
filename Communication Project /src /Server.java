import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Create a ServerSock on localhost:7777
    	ServerSocket ss = new ServerSocket(7777);
    	Socket socket = null;
    	try {
	        
	        ss.setReuseAddress(true);
	        System.out.println("ServerSocket awaiting connections...");
	        
	    	// running infinite loop for getting
			// client request
			while (true) {

			// socket object to receive incoming client
			// requests
			Socket client = ss.accept();
			// .accept blocks until an inbound connection on this port is attempted
			System.out.println("Connection from " + socket + "!");
			// Displaying that new client is connected
			// to server
			System.out.println("New client connected"
							+ client.getInetAddress()
									.getHostAddress());

			// create a new thread object
			ClientHandler clientSock
				= new ClientHandler(client);

			// This thread will handle the client
			// separately
			new Thread(clientSock).start();
			}
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (ss != null) {
				try {
					ss.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
        
        System.out.println("Closing sockets.");
        ss.close();
        socket.close();
    }
    
    private static class ClientHandler implements Runnable {
		private final Socket clientSocket;

		// Constructor
		public ClientHandler(Socket socket)
		{
			this.clientSocket = socket;
		}

		public void run()
		{
			try {
					
				// Output stream socket.
		        OutputStream outputStream = clientSocket.getOutputStream();

		        // Create object output stream from the output stream to send an object through it
		        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
		        
		        // get the input stream from the connected socket
		        InputStream inputStream = clientSocket.getInputStream();

		        // create a ObjectInputStream so we can read data from it.
		        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);

		        //hard-coding the accounts
		        ArrayList<User> users = new ArrayList<User>();
		        User newUser = new User("A","Anya","coolpass",false,false);
		        users.add(newUser);
		        User newUser2 = new User("B","Derion","coolerpass",false,false);
		        users.add(newUser2);
		        User newUser3 = new User("C","Admin","coolestpass",false,true);
		        users.add(newUser3);
		        
		        //retrieving logs
		        LogList logs = new LogList();
		        LogList newLogs = new LogList();
		        fetchLogs(logs);
		        ArrayList<LogEntry> adminRoomLogs = new ArrayList<LogEntry>(logs.getAllLogs());
		        ArrayList<LogEntry> roomOneLogs = new ArrayList<LogEntry>(logs.getRoomLogs(1));
		        ArrayList<LogEntry> roomTwoLogs = new ArrayList<LogEntry>(logs.getRoomLogs(2));
		        ArrayList<LogEntry> roomThreeLogs = new ArrayList<LogEntry>(logs.getRoomLogs(3));
		        
//		        roomOneLogs = logs.getRoomLogs(1);
//		        roomTwoLogs = logs.getRoomLogs(2);
//		        roomThreeLogs = logs.getRoomLogs(3);
		        
		        //hard-coding the chatrooms
		        ArrayList<Chatroom> chatrooms = new ArrayList<Chatroom>();
		        Chatroom adminRoom = new Chatroom(adminRoomLogs, "View All Logs", 0);
		        chatrooms.add(adminRoom);
		        Chatroom roomOne = new Chatroom(roomOneLogs, "Anya-Derion Direct Message", 1);
		        chatrooms.add(roomOne);
		        Chatroom roomTwo = new Chatroom(roomTwoLogs, "Anya-Derion-Admin Group Chat", 2);
		        chatrooms.add(roomTwo);
		        Chatroom roomThree = new Chatroom(roomThreeLogs, "Anya-Admin Direct Message", 3);
		        chatrooms.add(roomThree);
		        
		        //hard-coding the chatrooms per account
		        users.get(0).getChatrooms().add(roomOne);	//DM between Anya and Derion
		        users.get(1).getChatrooms().add(roomOne);
		        
		        users.get(0).getChatrooms().add(roomTwo);	//Group chat between all three users
		        users.get(1).getChatrooms().add(roomTwo);
		        users.get(2).getChatrooms().add(roomTwo);
		        
		        users.get(0).getChatrooms().add(roomThree);	//DM between Anya and MrAdmin
		        users.get(2).getChatrooms().add(roomThree);
		        
		        users.get(2).getChatrooms().add(adminRoom); //Admin Logs (all messages)
		         
		        // read the list of messages from the socket
		        Message messageSend;
		        Message messageGet;
		        boolean loggedIn = false;
		        boolean loggedOut = false;
		        String[] splitData = new String[2];
		        User theUser = new User("","","",false,false);
		        
		        
		        while(loggedOut == false) {
		        	messageGet = (Message) objectInputStream.readObject();
		        	System.out.println("Received message from: " + clientSocket);
		        	if(messageGet.getType() == MsgType.LOGIN) {
		        		if(messageGet.getText().length()<2) {	//ignore invalid login
		        			continue;
		        		}
		        		splitData = messageGet.getText().split("-");
		        		String userAttempt = splitData[0];
		        		String passAttempt = splitData[1];
		        		boolean validLogin = false;
		        		
		        		//login validation
		        		for(int i=0;i<users.size();i++) {
		        			if((users.get(i).getUsername().equals(userAttempt)) && (users.get(i).getPassword().equals(passAttempt))) {
		        				validLogin = true;
		        				loggedIn = true;
		        				theUser = users.get(i);
		        			}
		        		}
		        		//send chatrooms 
		        		if(validLogin) {
		        			String[] chatPerms = new String[] {"0","0","0","0"};
		        			String chatPermString = "";
		        			for(int i=0; i<theUser.getChatrooms().size();i++) {
		        					chatPerms[theUser.getChatrooms().get(i).getID()] = "1";
		        			}
		        			
		        			for(int i=0;i<4;i++) {								//form string for perms
		        				chatPermString += chatPerms[i];
		        			}
		        			
		        			chatPermString += theUser.getUserId();
		        			Message returnMsg = new Message(messageGet.getType(), MsgStatus.SUCCESS, chatPermString, chatrooms);
		        			objectOutputStream.writeObject(returnMsg);
		        		}
		        		else {
		        			Message returnMsg = new Message(messageGet.getType(), MsgStatus.FAIL,"Login fail", null);
		        			objectOutputStream.writeObject(returnMsg);
		        		}
		        		
		        	}
		        	else {
		        		if(loggedIn == false) {
		        			System.out.println("Not logged in, doing nothing.");
		        		}
			        	switch(messageGet.getType()) {
			        	case FETCHLOGS:{
			        		int roomID = 0;
			        		roomID = Integer.valueOf(messageGet.getText());
			        		ArrayList<LogEntry> logsToPrint = new ArrayList<LogEntry>();
			        		String printMsg = "";
			        		logsToPrint = newLogs.getRoomLogs(roomID);
			        		for(int i = 0; i<logsToPrint.size();i++) {
			        			printMsg += (logsToPrint.get(i).toString() + "\n");
			        		}
			        		Message returnMsg = new Message(messageGet.getType(), MsgStatus.SUCCESS, printMsg, null);
		        			objectOutputStream.writeObject(returnMsg);
			        		break;
			        	}
			        	case DIRECTMESSAGE:{
			        		Date newDate = new Date();			//create log
			        		String data = messageGet.getText();
			        		String[] parsed = new String[3];
			        		parsed = data.split("-");
			    			String text = parsed[2];
			    			String room = parsed[0];
			    			String user = parsed[1];
			    			
			    			LogEntry newLog = new LogEntry(newDate, text, Integer.valueOf(room), user);		    			
			    			newLogs.addLog(newDate, text, Integer.valueOf(room), user);
			    			String sourceName = "logs.txt";
			    			File file = new File(System.getProperty("user.dir") + "\\" + sourceName);
			    			
			    			try {
			    				
			    				FileWriter myWriter = new FileWriter(sourceName, true);		//append to the log for future
			    				myWriter.write(newLog.toString() + "\n");
			    				myWriter.close();
			    				
			    			} catch (IOException e1) {
			    				e1.printStackTrace();
			    				return;
			    			}
			    			
			    			Message returnMsg = new Message(messageGet.getType(), MsgStatus.SUCCESS, "", null);
		        			objectOutputStream.writeObject(returnMsg);
//			    			String returnText = newLog.toString();															//send the log to everyone in the chat
//			    			for(int i=0;i<users.size();i++) {
//			    				for(int j=0;i<users.size();j++) {
//				    				if(users.get(i).getChatrooms().get(j).getID() == Integer.valueOf(room)) {
//				    					Message returnMsg = new Message(messageGet.getType(), MsgStatus.SUCCESS, returnText, null);
//				    	        		objectOutputStream.writeObject(returnMsg);
//				    				}
//			    				}
//			    			}
			    			
			        		break;
			        	}
			        	case LOGOUT:{
			        		loggedOut = true;
			        		Message returnMsg = new Message(messageGet.getType(), MsgStatus.SUCCESS,"Logout success", null);
			        		objectOutputStream.writeObject(returnMsg);
			        		
			        		break;
			        	}
			        	default:{
			        		System.out.println("Nothing to see here.");
			        	}
			        	}
		        	}
		        	
		        }
				
			}
			catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally {
				
			}
		}
	}
		
    
    private static void fetchLogs(LogList logs) {
    	String sourceName = "logs.txt";
    	try {
    		File file = new File(System.getProperty("user.dir") + "\\" + sourceName);
    		Scanner scanner = new Scanner(file);
    		String[] splitData = new String[4];
    		String date = "";
    		String text = "";
    		String room = "";
    		String user = "";
    		while(scanner.hasNextLine()) {
    			String data = scanner.nextLine();
    			splitData = data.split("-");
    			date = splitData[1];
    			text = splitData[3];
    			room = splitData[0];
    			user = splitData[2];
    			Date newTime = new Date(date);
    			
    			logs.addLog(newTime, text, Integer.valueOf(room), user);
    		}
    		
    	} catch(FileNotFoundException e) {
    		System.out.println("Can't find logs.");
    		return;
    	}
    	
    }
    
    private static void printMessage(Message msg){
        System.out.println("ID: " + msg.getID());
        System.out.println("Type: " + msg.getType());
        System.out.println("Text: " + msg.getText());
    }

}



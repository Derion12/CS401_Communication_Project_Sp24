import java.util.ArrayList;
import java.util.Arrays;

public class User {
    private String userId;
    private String username;
    private String password;
    private ArrayList<Chatroom> chatrooms;
    private boolean online;
    private boolean admin;

    public User(String userId, String username, String password, boolean online, boolean admin) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.online = online;
        this.admin = admin;
        chatrooms = new ArrayList<Chatroom>();
    }

    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getOnline() {
        return online;
    }
    
    public boolean getAdmin() {
        return admin;
    }
    
    public ArrayList<Chatroom> getChatrooms() {
        return chatrooms;
    }
   
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
    public void setAdmin(boolean admin) {
        this.online = admin;
    }
   
}

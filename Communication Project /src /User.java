
public class User {
    private String userId;
    private String username;
    private String password;
    private UserStatus online;

    public User(String userId, String username, String password, boolean online) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.online = online;
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

    public UserStatus getOnline() {
        return online;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
    public void setOnline(boolean online){
        this.online = online;
    }

}

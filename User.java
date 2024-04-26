
public class User {
    private String userId;
    private String username;
    private String password;
    private UserStatus userStatus;

    public User(String userId, String username, String password, UserStatus userStatus) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.userStatus = userStatus;
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

    public UserStatus getUserStatus() {
        return userStatus;
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

   
}

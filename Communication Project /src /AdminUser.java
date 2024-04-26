public class AdminUser extends User {
	public AdminUser(String userId, String username, String password) {
        super(userId, username, password, UserStatus.Online);

	}
	
}

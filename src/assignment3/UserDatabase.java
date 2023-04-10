package assignment3;

import java.util.ArrayList;
import java.util.List;

// The class is similar to ReservationDatabase but the functionality is responsible for user data

public class UserDatabase {
	private List<User> users;

	public UserDatabase() {
		this.users = new ArrayList<>();
	}

	// method responsible for adding a new user
	public void addUser(String username, String password) {
		users.add(new User(username, password));
	}

	// method for user authentication
	public boolean authenticateUser(String username, String password) {
		User user = getUser(username);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	public User getUser(String username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public List<User> getUsers() {
		return users;
	}

	// method responsbile for user removal
	public void removeUser(String username) {
		User userToRemove = null;
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				userToRemove = user;
				break;
			}
		}
		if (userToRemove != null) {
			users.remove(userToRemove);
		}
	}
}

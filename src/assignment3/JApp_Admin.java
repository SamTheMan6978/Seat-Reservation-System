package assignment3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JApp_Admin {
	private UserDatabase userDatabase;
	private String adminUsername;
	private String adminPassword;

	// establishing root access user
	public JApp_Admin() {
		this.userDatabase = new UserDatabase();
		this.adminUsername = "root";
		this.adminPassword = "root";
		loadUsers();
	}

	// Method for adding a new user and writing them into a user_database.txt file
	
	public void addUser(User user) {
	    userDatabase.addUser(user.getUsername(), user.getPassword());
	    try {
	        FileOutputStream fout = new FileOutputStream("user_database.txt", true);
	        String userCredentials = user.getUsername() + "," + user.getPassword() + "\n";
	        fout.write(userCredentials.getBytes());
	        fout.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    System.out.println("User added successfully!");
	}

	// method for admin authentication
	
	public boolean authenticateAdmin(String username, String password) {
		return username.equals(adminUsername) && password.equals(adminPassword);
	}

	// method for user authentication
	public boolean authenticateUser(String username, String password) {
		return userDatabase.authenticateUser(username, password);
	}

	// method for displaying users stored in database
	
	public void displayUser() {
		for (User user : userDatabase.getUsers()) {
			System.out.println("User ID: " + user.getUsername() + ", Name: " + user.getPassword());
		}
	}

	// method for loading users from the database
	
	private void loadUsers() {
		try {
			File file = new File("user_database.txt");
			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file);
				Scanner scanner = new Scanner(fis);
				while (scanner.hasNextLine()) {
					String[] data = scanner.nextLine().split(",");
					userDatabase.addUser(data[0], data[1]);
				}
				scanner.close();
				fis.close();
			} else {
				file.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// method for user removal
	
	public void removeUser(String username) {
	    if (userDatabase.getUser(username) != null) {
	        userDatabase.removeUser(username);
	        try {
	            FileInputStream fis = new FileInputStream("user_database.txt");
	            Scanner scanner = new Scanner(fis);
	            List<String> userLines = new ArrayList<>();
	            List<String> reservationLines = new ArrayList<>();
	            while (scanner.hasNextLine()) {
	                String line = scanner.nextLine();
	                String[] data = line.split(",");
	                if (!data[0].equals(username)) {
	                    userLines.add(line);
	                }
	            }
	            scanner.close();
	            fis.close();

	            // read and remove reservation data for the user
	            File reservationFile = new File("reservation_database.txt");
	            if (reservationFile.exists()) {
	                fis = new FileInputStream(reservationFile);
	                scanner = new Scanner(fis);
	                while (scanner.hasNextLine()) {
	                    String line = scanner.nextLine();
	                    String[] data = line.split(",");
	                    if (!data[0].equals(username)) {
	                        reservationLines.add(line);
	                    }
	                }
	                scanner.close();
	                fis.close();
	                // write the updated reservation data back to the file
	                FileOutputStream reservationFos = new FileOutputStream(reservationFile);
	                for (String line : reservationLines) {
	                    reservationFos.write((line + "\n").getBytes());
	                }
	                reservationFos.close();
	            }

	            // write the updated user data back to the file
	            FileOutputStream fos = new FileOutputStream("user_database.txt");
	            for (String line : userLines) {
	                fos.write((line + "\n").getBytes());
	            }
	            fos.close();

	            System.out.println("User removed successfully!");
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    } else {
	        System.out.println("User not found!");
	    }
	}
}

package assignment3;

import java.io.IOException;
import java.util.Scanner;

public class JApp01 {
	public static void main(String[] args) {
		SeatReservationSystem reservationSystem = new SeatReservationSystem();
		JApp_Admin admin = new JApp_Admin();
		ReservationDatabase reserve = new ReservationDatabase("User_Database.txt");

		// Add some sample users to the user database for testing
		/*
		 * user1,pass1
		 * 
		 * user2,pass2
		 *
		 * administrator access is : root,root
		 */

		System.out.println("Welcome to Seat Reservation System!");

		try (Scanner input = new Scanner(System.in)) {
		    int userType = 0;
		    int choice;
		    String userID = null;

		    // user type classification 
		    
		    while (true) {
		        System.out.println("Are you a 1) user or 2) admin?");
		        if (input.hasNextInt()) {
		            userType = input.nextInt();
		            if (userType == 1 || userType == 2) {
		                break;
		            } else {
		                System.out.println("Invalid input, please enter 1 or 2.");
		            }
		        } else {
		            System.out.println("Invalid input, please enter an integer.");
		            input.next();
		        }
		    }

		    // Authentication
		    while (true) {
		        System.out.print("Enter your username: ");
		        String username = input.next();
		        System.out.print("Enter your password: ");
		        String password = input.next();

		        if (userType == 1) { // Regular user authentication
		            if (admin.authenticateUser(username, password)) {
		                userID = username;
		                break;
		            } else {
		                System.out.println("Invalid username or password, please try again");
		            }
		        } else if (userType == 2) { // Admin authentication
		            if (admin.authenticateAdmin(username, password)) {
		                break;
		            } else {
		                System.out.println("Invalid username or password, please try again");
		            }
		        }
		    }

			if (userType == 2) { // Admin menu
				while (true) {
					System.out.println("1) Display User");
					System.out.println("2) Add User");
					System.out.println("3) Remove User");
					System.out.println("4) Exit");
					choice = input.nextInt();
					if (choice == 1) {
						admin.displayUser();
					} else if (choice == 2) {
						System.out.print("Enter user ID: ");
						String id = input.next();
						System.out.print("Enter user password: ");
						String password = input.next();
						User user = new User(id, password);
						admin.addUser(user);

					} else if (choice == 3) {
						System.out.print("Enter user ID: ");
						String id = input.next();
						admin.removeUser(id);

					} else if (choice == 4) {
						System.exit(0);
					}
				}
			} else if (userType == 1) { // Reservation menu

				reservationSystem.displaySeatMatrix();

				while (true) {
					System.out.print("Enter 1 to select a seat, 2 to deselect a seat, 3 to confirm or 0 to exit: ");

					choice = input.nextInt();
					input.nextLine();

					if (choice == 0) {
						break;
					}

					if (choice == 1) {
						System.out.print("Enter the row and column (A1): ");
						String userInput = input.nextLine().trim().toUpperCase();

						// splitting the user input to generate row and column
						
						if (userInput.length() == 2 && userInput.charAt(0) >= 'A' && userInput.charAt(0) <= 'D'
								&& userInput.charAt(1) >= '1' && userInput.charAt(1) <= '9') {

							int row = Integer.parseInt(userInput.substring(1)) - 1;
							int col = userInput.charAt(0) - 'A';
							reservationSystem.selectSeat(userID, row, col);

						} else {
							System.out.println("Invalid input, please try again");
						}
						
						// splitting the user input to generate row and column to then de-select
					} else if (choice == 2) {
						System.out.print("Enter the row and column (A1): ");
						String userInput = input.nextLine().trim().toUpperCase();

						if (userInput.length() == 2 && userInput.charAt(0) >= 'A' && userInput.charAt(0) <= 'D'
								&& userInput.charAt(1) >= '1' && userInput.charAt(1) <= '9') {

							int row = Integer.parseInt(userInput.substring(1)) - 1;
							int col = userInput.charAt(0) - 'A';
							reservationSystem.deselectSeat(userID, row, col);

						} else {
							System.out.println("Invalid input, please try again");
						}
						
						// confirmation of user reservations
					} else if (choice == 3) {
						reservationSystem.seatConfirmation(userID);
						try {
							reserve.saveReservations(reservationSystem.getReservations());
							break;
						} catch (IOException e) {
							e.printStackTrace();
							break;
						}
					} else {
						System.out.println("Invalid input, please try again");
					}

					// displaying the final matrix
					reservationSystem.displaySeatMatrix();
				}
			}
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
	}
}

package assignment3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Primary class that is responsible for majority of functions with respect to seat reservations

public class SeatReservationSystem extends ReservationDatabase {
	private Seat[][] seats;
	private int numSeatsSelected;
	private boolean seatConfirmation;
	private Map<String, List<Seat>> reservations;

	// instantiating the array for seat generation
	public SeatReservationSystem() {
		super();
		this.seats = new Seat[9][4];
		int seatNumber = 1;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 4; j++) {
				seats[i][j] = new Seat(seatNumber++, i, j, true);
			}
		}
		this.reservations = new HashMap<>();
	}


	// function for deselecting seats for the user (currently not working as intended)
	
	public void deselectSeat(String userId, int row, int col) throws IOException {
	    Seat seat = seats[row][col];
	    Map<String, List<Seat>> reservations = loadReservations(seats);
	    List<Seat> userReservations = reservations.get(userId);
	    if (userReservations == null || !userReservations.contains(seat)) {
	        System.out.println("You have not selected this seat.");
	    } else {
	        seat.setAvailable(true);
	        userReservations.remove(seat);
	        numSeatsSelected--;
	        System.out.println("Seat " + getSeatName(row, col) + " deselected successfully.");
	    }
	}

	
	// displaying the records in a matrix (while also loading in reserved seats taken from ReservationDatabase.txt
	
	public void displaySeatMatrix() throws IOException {
		Map<String, List<Seat>> reservations = loadReservations(seats);

		System.out.println("   A  B  C  D ");
		for (int i = 0; i < 9; i++) {
			System.out.print((i + 1) + " ");
			for (int j = 0; j < 4; j++) {
				Seat seat = seats[i][j];
				boolean isReserved = false;
				@SuppressWarnings("unused")
				boolean isSelected = false;
				for (List<Seat> userReservations : reservations.values()) {
					for (Seat reservation : userReservations) {
						if (reservation.equals(seat)) {
							isReserved = true;
							break;
						}
					}
					if (isReserved) {
						break;
					}
				}
				if (isReserved) {
					System.out.print("[X]");
				} else if (seat.isAvailable()) {
					System.out.print("[ ]");
				} else {
					isSelected = true;
					System.out.print("[X]");
				}
			}
			System.out.println();
		}
	}

	public int getNumSeatsSelected() {
		return numSeatsSelected;
	}

	public Map<String, List<Seat>> getReservations() {
		Map<String, List<Seat>> reservationsMap = new HashMap<>();

		for (String userId : reservations.keySet()) {
			List<Seat> seats = reservations.get(userId);
			reservationsMap.put(userId, new ArrayList<>(seats));
		}

		return reservationsMap;
	}

	private String getSeatName(int row, int col) {
		char colName = (char) ('A' + col);
		return "" + colName + (row + 1);
	}

	public Seat[][] getSeats() {
		return seats;
	}

	public boolean isSeatConfirmation() {
		return seatConfirmation;
	}
	
	// seat confirmation method for debugging and user information
	public void seatConfirmation(String userId) {
		if (numSeatsSelected == 0) {
			System.out.println("You have not selected any seats to reserve.");
			return;
		}
		if (seatConfirmation) {
			System.out.println("You have already confirmed your reservation.");
			return;
		}
		List<Seat> userReservations = reservations.get(userId);
		if (userReservations == null || userReservations.isEmpty()) {
			System.out.println("You have not selected any seats to reserve.");
			return;
		}
		seatConfirmation = true;
		System.out.print("You have reserved the following seats:");

		for (Seat seat : userReservations) {
			System.out.print(" " + getSeatName(seat.getRow(), seat.getColumn()));
		}
		System.out.println();
	}

	
	// method for allocating user selection to reservations
	
	public void selectSeat(String userId, int row, int col) throws IOException {
	    Seat seat = seats[row][col];
	    loadReservations(seats);
	    if (!seat.isAvailable()) {
	        if (reservations.containsKey(userId) && reservations.get(userId).contains(seat)) {
	            System.out.println("You have already selected this seat.");
	        } else {
	            System.out.println("This seat is not available.");
	        }
	    } else {
	        for (List<Seat> userReservations : reservations.values()) {
	            if (userReservations.contains(seat)) {
	                System.out.println("This seat is not available (reserved by others).");
	                return;
	            }
	        }
	        seat.setAvailable(false);
	        numSeatsSelected++;
	        List<Seat> userReservations = reservations.get(userId);
	        if (userReservations == null) {
	            userReservations = new ArrayList<>();
	            reservations.put(userId, userReservations);
	        }
	        userReservations.add(seat);
	        System.out.println("Seat " + getSeatName(row, col) + " selected successfully.");
	    }
	}

	public void setNumSeatsSelected(int numSeatsSelected) {
		this.numSeatsSelected = numSeatsSelected;
	}

	public void setReservations(ReservationDatabase reservationDatabase) {
		try {
			this.reservations = reservationDatabase.loadReservations(seats);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setSeatConfirmation(boolean seatConfirmation) {
		this.seatConfirmation = seatConfirmation;
	}

	public void setSeats(Seat[][] seats) {
		this.seats = seats;
	}
}
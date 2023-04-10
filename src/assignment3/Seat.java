package assignment3;

// class is responsible for holding Seat attributes
public class Seat {
	private int seatNumber;
	private int row;
	private int column;
	private boolean isAvailable;

	public int getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(int seatNumber) {
		this.seatNumber = seatNumber;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean available) {
		isAvailable = available;
	}

	public Seat(int seatNumber, int row, int column, boolean isAvailable) {
		this.seatNumber = seatNumber;
		this.row = row;
		this.column = column;
		this.isAvailable = isAvailable;
	}

	public void reserveSeat() {
		this.isAvailable = false;
	}

	public void cancelReservation() {
		this.isAvailable = true;
	}
	
	
}

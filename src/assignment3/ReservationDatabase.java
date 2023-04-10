package assignment3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ReservationDatabase {
	private File file;

	public ReservationDatabase() {
		this.file = null;
	}

	public ReservationDatabase(String fileName) {
		this.file = new File(fileName);
	}

	public File getFile() {
		return file;
	}

	// loading reservations data from Reservation_Database.txt
	
	
	public Map<String, List<Seat>> loadReservations(Seat[][] seats) throws IOException {
	    Map<String, List<Seat>> reservations = new HashMap<>();
	    Path path = Paths.get("Reservation_Database.txt");
	    if (Files.exists(path)) {
	        List<String> lines = Files.readAllLines(path);
	        for (String line : lines) {
	            String[] fields = line.split(",");
	            if (fields.length == 3) {
	                String userId = fields[0];
	                int row = Integer.parseInt(fields[1]);
	                int col = Integer.parseInt(fields[2]);

	                Seat seat = seats[row][col];

	                List<Seat> userReservations = reservations.get(userId);
	                if (userReservations == null) {
	                    userReservations = new ArrayList<>();
	                    reservations.put(userId, userReservations);
	                }
	                userReservations.add(seat);
	            }
	        }
	    }
	    return reservations;
	}

	// Saving reservations data to Reservation_Database.txt
	
	public void saveReservations(Map<String, List<Seat>> reservations) throws IOException {
		FileOutputStream fos = null;
		Set<String> savedReservations = new HashSet<>();
		try {
			fos = new FileOutputStream("Reservation_Database.txt", true);
			for (Map.Entry<String, List<Seat>> entry : reservations.entrySet()) {
				String userId = entry.getKey();
				List<Seat> seats = entry.getValue();

				for (Seat seat : seats) {
					String reservationKey = userId + "-" + seat.getRow() + "-" + seat.getColumn();
					if (!savedReservations.contains(reservationKey)) {
						String line = String.format("%s,%d,%d\n", userId, seat.getRow(), seat.getColumn());
						fos.write(line.getBytes());
						savedReservations.add(reservationKey);
					}
				}
			}
			fos.flush();
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
	}


	
	public void setFile(File file) {
		this.file = file;
	}
}

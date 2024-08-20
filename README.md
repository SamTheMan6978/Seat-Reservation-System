
# Seat Reservation System

This repository contains the implementation of a seat reservation system. The system allows users to reserve seats, view reservations, and manage user data through a simple user interface.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Dependencies](#dependencies)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## Features
- **User Management:** Allows the creation, deletion, and management of user accounts.
- **Seat Reservation:** Users can reserve seats and view available options.
- **Admin Functions:** Admins have additional privileges, including managing reservations and users.
- **Database Management:** Persistent storage of user data and reservation details.

## Work in Progress

🚧 **This project is currently under development and is not yet complete.** 🚧

Some features may be incomplete or in-progress, and the code may be subject to change. Contributions and feedback are welcome as we continue to develop and improve this project.


## Project Structure

```
├── bin/                         # Compiled Java classes
│   ├── module-info.class
│   └── assignment3/
│       ├── JApp01.class
│       ├── JApp_Admin.class
│       ├── ReservationDatabase.class
│       ├── Seat.class
│       ├── SeatReservationSystem.class
│       ├── User.class
│       └── UserDatabase.class
├── src/                         # Source Java files
│   ├── module-info.java
│   └── assignment3/
│       ├── JApp01.java
│       ├── JApp_Admin.java
│       ├── ReservationDatabase.java
│       ├── Seat.java
│       ├── SeatReservationSystem.java
│       ├── User.java
│       └── UserDatabase.java
├── Reservation_Database.txt     # Text file storing reservation data
├── user_database.txt            # Text file storing user data
```

## Dependencies
- **Java JDK 8 or above:** Ensure that Java is installed and configured on your system.

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/yourusername/Seat-Reservation-System.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Seat-Reservation-System
   ```

## Usage
1. Compile the Java source files:
   ```bash
   javac -d bin src/assignment3/*.java
   ```
2. Run the application:
   ```bash
   java -cp bin assignment3.SeatReservationSystem
   ```

## Contributing
If you'd like to contribute to this project, please fork the repository and use a feature branch. Pull requests are warmly welcome.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact
For any questions or contributions, please open an issue on this repository or contact the repository maintainer.

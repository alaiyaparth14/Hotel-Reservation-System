# 🏨 Hotel Reservation System (JDBC Project)

A console-based hotel reservation system built using **Java JDBC**, allowing users to perform CRUD operations on room bookings with MySQL as the backend.

## 🚀 Features

- Reserve a new room
- View all reservations
- Fetch room number by reservation ID and guest name
- Update reservation details
- Delete a reservation
- Interactive CLI menu for smooth user experience

## 💻 Technologies Used

- Java 17+
- JDBC API
- MySQL Database
- Console I/O (Scanner)
- SQL (Basic Queries)

## 📂 Project Structure


## 🛠️ Setup Instructions

1. **Clone this repo**
   ```bash
   git clone https://github.com/your-username/hotel-reservation-system.git
   cd hotel-reservation-system

2.SQL

CREATE TABLE reservations (
    reservation_id INT AUTO_INCREMENT PRIMARY KEY,
    guest_name VARCHAR(100),
    room_number INT,
    contact_number VARCHAR(20),
    reservation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

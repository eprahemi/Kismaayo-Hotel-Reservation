# Kismaayo Hotel — Reservation System

Java HTTP server that serves a hotel booking website. All data stored in `data/*.txt` files. No database needed.

---

## Requirements

- **Java 8+** (JRE or JDK)

Check if Java is installed:

```
java -version
```

If not installed:

- **Fedora:** `sudo dnf install java-latest-openjdk`
- **Windows:** Download from [adoptium.net](https://adoptium.net/) or [oracle.com](https://www.oracle.com/java/technologies/downloads/)

---

## How to Run

### Linux (Fedora)

```bash
cd HotelReservation/java
javac HotelServer.java
java HotelServer
```

### Windows (CMD / PowerShell)

```cmd
cd HotelReservation\java
javac HotelServer.java
java HotelServer
```

You should see:

```
Kismaayo Hotel Server running on http://localhost:8080
```

---

## How to Use

Open browser: `http://localhost:8080`

### Default Admin
- **Username:** `admin`
- **Password:** `admin123`

### Pages
| Page | What it does |
|------|-------------|
| `/` or `index.html` | Home page |
| `pages/login.html` | Login gateway |
| `pages/signup.html` | Create account |
| `pages/rooms.html` | View rooms with amenities |
| `pages/reservation.html` | Book a room (slider: 1–30 nights, price doubles each night) |
| `pages/dashboard.html` | Your bookings, cancel booking, edit profile |
| `pages/admin.html` | Admin: manage rooms, users, all bookings |
| `pages/receipt.html?id=X` | Printable receipt for booking ID X |
| `pages/hotel-info.html` | About + contact form |

---

## Project Structure

```
HotelReservation/
├── Akhri.md
├── index.html
├── css/style.css
├── java/
│   ├── HotelServer.java        ← HTTP server (run this)
│   ├── Room.java, StandardRoom.java, DeluxeRoom.java, SuiteRoom.java
│   ├── Customer.java, User.java, Reservation.java
│   └── HotelReservationSystem.java
├── pages/
│   ├── login.html, signup.html, dashboard.html
│   ├── admin.html, rooms.html, reservation.html
│   ├── receipt.html, hotel-info.html
└── data/
    ├── users.txt               ← username|password|name|phone|email|isAdmin
    ├── rooms.txt               ← name|price|amenities
    ├── reservations.txt        ← id|username|name|roomType|checkin|checkout|guests|phone|email
    └── contacts.txt            ← name|email|message|timestamp
```

---

## Troubleshooting

| Problem | Fix |
|---------|-----|
| `javac: command not found` | Install JDK, not just JRE |
| `java: command not found` | Install Java |
| Port 8080 in use | Linux: `fuser -k 8080/tcp` — Windows: `netstat -ano \| findstr :8080` then `taskkill /PID X` |
| "Server error!" in browser | Use `http://localhost:8080` not `file://` |
| Data files corrupted | Delete the file in `data/` and restart server (it recreates defaults) |

---

## Features

- Login/signup gateway
- Room booking with 1–30 night slider (price doubles each night)
- Check availability before booking
- Printable receipt page
- Dashboard: cancel bookings, edit profile
- Admin panel: manage rooms, users, all bookings
- Contact form
- Clean, minimal design

---

*University of Kismaayo — Semester 6 Assignment*

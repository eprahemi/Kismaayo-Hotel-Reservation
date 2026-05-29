
<!-- markdownlint-disable MD033 MD041 -->

<p align="center">
  <br>
  <img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java">
  <img src="https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white" alt="HTML5">
  <img src="https://img.shields.io/badge/CSS3-1572B6?style=for-the-badge&logo=css3&logoColor=white" alt="CSS3">
  <img src="https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black" alt="JavaScript">
  <img src="https://img.shields.io/badge/License-MIT-1a1a2e?style=for-the-badge" alt="License">
  <br><br>
</p>

<div align="center">

# 🏨 Kismaayo Hotel Reservation System

### *Pure Java HTTP Server · Zero External Dependencies · File-Based Storage*

[![GitHub last commit](https://img.shields.io/github/last-commit/eprahemi/Kismaayo-Hotel-Reservation?style=flat&color=C9A96E)](https://github.com/eprahemi/Kismaayo-Hotel-Reservation/commits/main)
[![GitHub repo size](https://img.shields.io/github/repo-size/eprahemi/Kismaayo-Hotel-Reservation?style=flat&color=C9A96E)](https://github.com/eprahemi/Kismaayo-Hotel-Reservation)
[![GitHub stars](https://img.shields.io/github/stars/eprahemi/Kismaayo-Hotel-Reservation?style=flat&color=C9A96E)](https://github.com/eprahemi/Kismaayo-Hotel-Reservation/stargazers)

<br>

</div>

---

```text
╔══════════════════════════════════════════════════════════════════════════════╗
║                                                                              ║
║               Kismaayo Hotel — Where Code Meets Comfort 🏨                  ║
║                                                                              ║
║  A fully functional hotel reservation web application built with pure Java   ║
║  on the backend and vanilla HTML/CSS/JavaScript on the frontend. No Spring,  ║
║  no Tomcat, no MySQL — just the JDK's built-in HttpServer and plain .txt     ║
║  files for storage. Designed for the University of Kismaayo, Semester 6.     ║
║                                                                              ║
╚══════════════════════════════════════════════════════════════════════════════╝
```

<br>

## 📋 Table of Contents

- [✨ Features](#-features)
- [🏗️ Architecture](#️-architecture)
- [📁 Project Structure](#-project-structure)
- [🚀 Quick Start](#-quick-start)
- [🔌 API Endpoints](#-api-endpoints)
- [📊 Data Storage](#-data-storage)
- [🧬 OOP Concepts Demonstrated](#-oop-concepts-demonstrated)
- [🖥️ Screenshots](#️-screenshots)
- [👥 Team](#-team)
- [📄 License](#-license)

<br>

---

## ✨ Features

<div align="center">

| 🎯 Feature | 📝 Description |
|:----------|:---------------|
| 🔐 **Login by Email or Username** | Single field accepts either email or username — server searches both |
| 📝 **Real-Time Duplicate Validation** | Username, email, and phone checked live with 400ms debounce |
| 🎚️ **Interactive Pricing Slider** | Drag 1–30 nights — live cost breakdown table updates instantly |
| 🧾 **Printable Receipt** | Auto-generated after booking — print-friendly CSS hides all navigation |
| 📊 **User Dashboard** | View bookings, cancel, edit profile, delete account in one place |
| ⚙️ **Admin Panel** | Manage rooms, users, bookings — with stats dashboard |
| 🔍 **Date Overlap Detection** | Prevents double-booking using string comparison (YYYY-MM-DD) |
| 🛡️ **Admin Self-Protection** | Cannot delete admin accounts — buttons hidden + server-side guard |
| 🌍 **Somali Language** | All error messages in Somali — built for the local community |
| 📁 **File-Based Storage** | No database needed — all data in inspectable `.txt` files |

</div>

<br>

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────────────────────┐
│                        SYSTEM ARCHITECTURE                              │
├─────────────────────────────────────────────────────────────────────────┤
│                                                                         │
│     ┌──────────┐      ┌──────────────┐     ┌──────────────────────┐    │
│     │ BROWSER  │      │   HTML/CSS   │     │  JAVASCRIPT (AJAX)   │    │
│     │ (Chrome) │─────▶│  (Frontend)  │────▶│ XMLHttpRequest /     │    │
│     └──────────┘      └──────────────┘     │ Fetch API            │    │
│                                             └──────────┬───────────┘    │
│                                                        │                │
│                                  HTTP POST/GET JSON     │                │
│                                                        ▼                │
│     ┌──────────────────────────────────────────────────────────────┐    │
│     │              JAVA HTTP SERVER (HotelServer.java)              │    │
│     │              ═══════════════════════════════════              │    │
│     │  Port 8080 · 19 Handler Classes · 1,040 Lines                │    │
│     │                                                              │    │
│     │  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────────┐    │    │
│     │  │ Login    │ │ Signup   │ │ Book     │ │ Availability │    │    │
│     │  │ Handler  │ │ Handler  │ │ Handler  │ │ Handler      │    │    │
│     │  ├──────────┤ ├──────────┤ ├──────────┤ ├──────────────┤    │    │
│     │  │ Rooms    │ │ Users    │ │ Stats    │ │ Contact      │    │    │
│     │  │ Handler  │ │ Handler  │ │ Handler  │ │ Handler      │    │    │
│     │  ├──────────┤ ├──────────┤ ├──────────┤ ├──────────────┤    │    │
│     │  │ Check    │ │ Profile  │ │ Booking  │ │ Static File  │    │    │
│     │  │ Field    │ │ Update   │ │ Delete   │ │ Handler      │    │    │
│     │  └──────────┘ └──────────┘ └──────────┘ └──────────────┘    │    │
│     └───────────────────────────┬──────────────────────────────────┘    │
│                                 │                                       │
│                    Reads/Writes .txt Files                               │
│                                 ▼                                       │
│     ┌──────────────────────────────────────────────────────────────┐    │
│     │  📁 data/                                                    │    │
│     │  ├── users.txt           (username|password|name|phone|...)  │    │
│     │  ├── rooms.txt           (Standard|50|AC,WiFi,TV,...)       │    │
│     │  ├── reservations.txt    (id|username|name|roomType|...)     │    │
│     │  ├── room_assignments.txt (roomNum|username)                 │    │
│     │  └── contacts.txt        (name|email|message|date)           │    │
│     └──────────────────────────────────────────────────────────────┘    │
│                                                                         │
└─────────────────────────────────────────────────────────────────────────┘
```

<br>

---

## 📁 Project Structure

```
Kismaayo-Hotel-Reservation/
│
├── 📄 index.html                    # Home page (auth guard, welcome message)
│
├── 🎨 css/
│   └── style.css                    # Arch Linux inspired theme (515 lines)
│
├── 📂 data/
│   ├── users.txt                    # |username|password|name|phone|email|isAdmin|
│   ├── rooms.txt                    # |name|price|amenities|
│   ├── reservations.txt             # 10-field or 9-field format (backward compat)
│   ├── room_assignments.txt         # |roomNum|username| (global 30-room pool)
│   └── contacts.txt                 # |name|email|message|date|
│
├── 📄 pages/
│   ├── login.html                   # Email/Username + Password login
│   ├── signup.html                  # Registration with real-time validation
│   ├── rooms.html                   # Dynamic room listing from server
│   ├── reservation.html             # ★ Interactive booking with pricing slider
│   ├── dashboard.html               # User dashboard + profile editor
│   ├── admin.html                   # Full admin panel
│   ├── receipt.html                 # Printable booking receipt
│   ├── hotel-info.html              # About page + contact form
│   └── logout.html                  # Dedicated logout confirmation
│
├── ☕ java/
│   ├── HotelServer.java             # ★ MAIN: HTTP server (1,040 lines, 19 handlers)
│   ├── HotelReservationSystem.java  # Console-based version (496 lines)
│   ├── Room.java                    # Base class — encapsulation
│   ├── StandardRoom.java            # Inheritance — Room → Standard
│   ├── DeluxeRoom.java              # Inheritance — Room → Deluxe
│   ├── SuiteRoom.java               # Inheritance — Room → Suite
│   ├── Customer.java                # Constructor overloading (3 constructors)
│   ├── Reservation.java             # Static counter, File I/O
│   └── User.java                    # User model with File I/O
│
├── 📂 presentation/
│   └── full-presentation-map.md     # Solo presentation guide (1,426 lines)
│
└── 📄 README.md                     # ⬅️ You are here
```

<br>

---

## 🚀 Quick Start

### Prerequisites

- ✅ **Java JDK 8+** (tested with OpenJDK 11 and 17)
- ✅ A modern web browser (Chrome, Firefox, Edge)

### Run the Server

```bash
# Clone the repository
git clone https://github.com/eprahemi/Kismaayo-Hotel-Reservation.git
cd Kismaayo-Hotel-Reservation/java

# Compile the server
javac HotelServer.java

# Start the server
java HotelServer

# Output:
# ========================================
#   Kismaayo Hotel Server running on
#   http://localhost:8080
# ========================================
```

### Open in Browser

```bash
# Linux / macOS
firefox http://localhost:8080
# or
xdg-open http://localhost:8080

# Windows
start http://localhost:8080
```

### Default Admin Credentials

| Username | Password | Role |
|:---------|:---------|:-----|
| `admin` | `1234` | 👑 Administrator |

<br>

---

## 🔌 API Endpoints

| Method | Route | Handler | Description |
|:-------|:------|:--------|:------------|
| `POST` | `/api/login` | `LoginHandler` | Authenticate by username OR email |
| `POST` | `/api/signup` | `SignupHandler` | Register new user with validation |
| `POST` | `/api/check` | `CheckFieldHandler` | Real-time duplicate field check |
| `GET` | `/api/rooms/available` | `RoomsAvailableHandler` | List available room numbers (1–30) |
| `POST` | `/api/book` | `BookHandler` | Create a new booking |
| `GET` | `/api/bookings` | `BookingsHandler` | List all bookings (filterable) |
| `GET` | `/api/booking?id=X` | `BookingByIdHandler` | Get single booking details |
| `GET` | `/api/users` | `UsersHandler` | List all users (admin only) |
| `POST` | `/api/users/update` | `UserUpdateHandler` | Edit user (admin) |
| `POST` | `/api/users/delete` | `UserDeleteHandler` | Delete user + cleanup all data |
| `POST` | `/api/user/update` | `ProfileUpdateHandler` | Edit own profile with duplicate check |
| `GET` | `/api/rooms` | `RoomsHandler` | List all room types with prices |
| `POST` | `/api/rooms/add` | `RoomAddHandler` | Add new room type (admin) |
| `POST` | `/api/rooms/delete` | `RoomDeleteHandler` | Delete room type (admin) |
| `POST` | `/api/bookings/delete` | `BookingDeleteHandler` | Cancel booking, free room |
| `GET` | `/api/stats` | `StatsHandler` | Dashboard statistics |
| `POST` | `/api/contact` | `ContactHandler` | Submit contact form |
| `POST` | `/api/availability` | `AvailabilityHandler` | Check date overlap availability |
| `GET` | `/` | `StaticFileHandler` | Serve HTML, CSS, JS files |

### 📊 Reservation Data Format

```
# New 10-field format:
  [id]|[username]|[name]|[roomType]|[roomNumber]|[checkin]|[checkout]|[guests]|[phone]|[email]

# Example:
  42|john|John Doe|Deluxe|15|2026-06-01|2026-06-05|2|612345678|john@gmail.com

# Backward compatible with old 9-field format (no room number)
```

<br>

---

## 📊 Data Storage

All data is stored in **pipe-delimited** ( `|` ) plain text files in the `data/` directory.

| File | Fields | Description |
|:-----|:-------|:------------|
| `users.txt` | `username\|password\|name\|phone\|email\|isAdmin` | User accounts |
| `rooms.txt` | `name\|price\|amenities` | Room types & pricing |
| `reservations.txt` | `id\|username\|name\|roomType\|roomNumber\|checkin\|checkout\|guests\|phone\|email` | Booking records |
| `room_assignments.txt` | `roomNum\|username` | Global 30-room pool tracking |
| `contacts.txt` | `name\|email\|message\|date` | Contact form submissions |

> 💡 **Why .txt files?** Simple, portable, no database setup needed, and every file can be opened in any text editor for inspection. Perfect for a learning environment.

<br>

---

## 🧬 OOP Concepts Demonstrated

| Concept | File | Example |
|:--------|:-----|:--------|
| 🔒 **Encapsulation** | `Room.java` | `private` fields with `public` getters |
| 🧬 **Inheritance** | `StandardRoom extends Room` | Child classes reuse parent code |
| 🔄 **Polymorphism** | `@Override displayInfo()` | Same method, different behavior |
| 📐 **Constructor Overloading** | `Customer.java` | 3 constructors, different parameters |
| 📊 **Static Members** | `Reservation.counter` | Shared auto-increment ID |
| 📂 **File I/O** | `User.saveUsers()`, `readUsers()` | Read/write `.txt` files |
| 🎯 **Exception Handling** | `HotelReservationSystem.java` | try-catch-finally, multiple catch blocks |

<br>

---

## 🖥️ Screenshots

<div align="center">

| Page | Preview |
|:-----|:--------|
| 🔐 **Login Page** | *Email / Username + password login with Somali error messages* |
| 📝 **Registration** | *Real-time duplicate validation on username, phone, email* |
| 🏠 **Home Page** | *Welcome greeting, quick links, room highlights* |
| 🛏️ **Rooms Page** | *Dynamic room cards with prices and amenities* |
| 🎚️ **Booking Form** | *Interactive slider, live cost breakdown, room number picker* |
| 📊 **Dashboard** | *My bookings table, profile editor, delete account* |
| ⚙️ **Admin Panel** | *Stats, manage rooms, all users, all bookings* |
| 🧾 **Receipt** | *Clean print-friendly booking confirmation* |

</div>

<br>

---

## 👥 Team

| # | Role | Responsibility |
|:--|:-----|:--------------|
| 1 | 🎬 **Team Leader** | Opens presentation, explains project, shows live demo |
| 2 | 🌐 **HTML Pages** | All 9 web pages + AJAX communication with server |
| 3 | ☕ **Java Server** | `HotelServer.java` — all 19 API handler classes |
| 4 | 📦 **OOP Classes** | Room hierarchy, Customer, Reservation, User models |
| 5 | 📁 **Data Storage** | `data/*.txt` file formats and data flow |
| 6 | ⚙️ **Admin Features** | Admin panel, user management, room management |
| 7 | 🎚️ **Slider & Receipt** | Interactive pricing slider, printable receipt |

<br>

---

## 📄 License

```text
MIT License

Copyright (c) 2026 — University of Kismaayo · Group 3

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
```

<br>

---

<div align="center">

**🏨 Kismaayo Hotel — Pure Java · Pure Code · Pure Excellence**

*University of Kismaayo · Semester 6 · 30th May 2026*

<a href="https://github.com/eprahemi/Kismaayo-Hotel-Reservation">
  <img src="https://img.shields.io/badge/View_on-GitHub-181717?style=for-the-badge&logo=github&logoColor=white" alt="View on GitHub">
</a>

</div>

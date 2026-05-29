# 🏨 Kismaayo Hotel Reservation System
## 🎤 Complete Solo Presentation Map · 30–40 Minutes
### 👤 Presenter: You · 👥 Audience: Teachers & Students · 📅 30th May 2026 · Semester 6

---

```text
╔══════════════════════════════════════════════════════════════════╗
║                     PRESENTATION FLOW                           ║
╠══════════════════════════════════════════════════════════════════╣
║  1.  Opening & Introduction       (3 min)   ═══  ████████░░   ║
║  2.  The Problem & Our Solution   (3 min)   ═══  ████████░░   ║
║  3.  System Architecture          (4 min)   ═══  ██████████   ║
║  4.  Frontend – All HTML Pages    (6 min)   ═══  ██████████   ║
║  5.  Backend – Java Server        (7 min)   ═══  ██████████   ║
║  6.  OOP Concepts in Code         (5 min)   ═══  ██████████   ║
║  7.  Data Storage & Flow          (3 min)   ═══  ██████░░░░   ║
║  8.  Key Features Demo            (4 min)   ═══  ██████████   ║
║  9.  Live Website Demo            (5 min)   ═══  ██████████   ║
║  10. Q&A Session                  (5 min)   ═══  ██████░░░░   ║
╚══════════════════════════════════════════════════════════════════╝
```

---

# 🎬 SECTION 1: OPENING & INTRODUCTION (~3 min)

---

### 🎯 What to Say (Opening Words)

> *"Assalaamu Calaykum. My name is [Your Name], and today I will be presenting our project: the **Kismaayo Hotel Reservation System**. This is a fully functional hotel booking website built with pure Java on the backend and HTML/CSS/JavaScript on the frontend. I will walk you through every part — the code, the architecture, the features, and a live demo."*

---

### 📋 Project Overview Table

| Item | Detail |
|------|--------|
| **Project Name** | Kismaayo Hotel Reservation System |
| **Course** | Java Programming II — Semester 6 |
| **Language** | Java (Backend) + HTML/CSS/JS (Frontend) |
| **Server** | Pure Java HTTP Server (no frameworks!) |
| **Storage** | Plain `.txt` files (no database!) |
| **Port** | `8080` |
| **Group Members** | 7 |
| **Lines of Code** | ~2,600+ total (1,040 Java server + 496 console app + ~1,100 HTML/JS) |
| **GitHub** | `eprahemi/Kismaayo-Hotel-Reservation` |

---

### 📸 Show: Project Structure Tree

```
Kismaayo-Hotel-Reservation/
├── index.html                    # Home page (redirects if not logged in)
├── css/
│   └── style.css                 # All styling (515 lines)
├── data/
│   ├── users.txt                 # User accounts (pipe-delimited)
│   ├── rooms.txt                 # Room types & prices
│   ├── reservations.txt          # Booking records
│   ├── room_assignments.txt      # Room 1-30 assignment tracking
│   └── contacts.txt              # Contact form messages
├── pages/
│   ├── login.html                # Login page (email/username + password)
│   ├── signup.html               # Registration with real-time validation
│   ├── index.html → rooms.html   # Room listing with prices & amenities
│   ├── reservation.html          # Booking form with pricing slider
│   ├── dashboard.html            # User dashboard (bookings + profile)
│   ├── admin.html                # Admin panel (manage everything)
│   ├── receipt.html              # Printable booking receipt
│   ├── hotel-info.html           # About page + contact form
│   └── logout.html               # Dedicated logout confirmation page
├── java/
│   ├── HotelServer.java          # ★ MAIN HTTP SERVER (1,040 lines)
│   ├── HotelReservationSystem.java  # Console-based app (496 lines)
│   ├── Room.java                 # Base class (encapsulation)
│   ├── StandardRoom.java         # Inheritance from Room
│   ├── DeluxeRoom.java           # Inheritance from Room
│   ├── SuiteRoom.java            # Inheritance from Room
│   ├── Customer.java             # Constructor overloading
│   ├── Reservation.java          # Static counter + File I/O
│   └── User.java                 # User model + File I/O
└── presentation/
    └── [this file]               # You are here!
```

---

# 💡 SECTION 2: THE PROBLEM & OUR SOLUTION (~3 min)

---

### 🎯 Speaker Script

> *"Before we dive into the code, let me explain why we built this. Hotels in Kismaayo were using paper records. Customers had to call or visit in person. There was no way to check room availability remotely. Managing reservations was a nightmare.
>
> Our solution? A **complete online hotel reservation system** — a website where customers can register, browse rooms, book with a live pricing slider, and get a printable receipt. All built with **pure Java** on the backend and **simple .txt file storage** — no database, no frameworks, no external libraries."*

---

### 📊 Side-by-Side Comparison

| ❌ Before (Problem) | ✅ After (Our Solution) |
|---|---|
| 📝 Paper records manually kept | 💻 Digital `users.txt`, `reservations.txt` |
| 📞 Call or visit to book | 🌐 Book online 24/7 from browser |
| ❓ No availability check | ✅ Real-time overlap detection |
| 💰 Manual price calculation | 🎚️ Live slider with cost breakdown |
| 🧾 Handwritten receipts | 🖨️ Printable receipt with one click |
| 👑 No admin oversight | ⚙️ Full admin panel with stats |

---

# 🏗️ SECTION 3: SYSTEM ARCHITECTURE (~4 min)

---

### 🎯 Speaker Script

> *"Let me show you the big picture — how everything connects. The system follows a simple client-server architecture."*

---

### 🖼️ Architecture Diagram (ASCII Art)

```text
┌─────────────────────────────────────────────────────────────────┐
│                    SYSTEM ARCHITECTURE                          │
├─────────────────────────────────────────────────────────────────┤
│                                                                 │
│  ┌──────────┐   ┌──────────────┐   ┌───────────────────────┐   │
│  │ BROWSER  │   │   HTML/CSS   │   │   JAVASCRIPT (AJAX)  │   │
│  │ (Chrome) │──▶│  (Frontend)  │──▶│  Fetch / XMLHttpReq  │   │
│  └──────────┘   └──────────────┘   └───────────┬───────────┘   │
│                                                │               │
│                   HTTP POST/GET JSON            │               │
│                                                ▼               │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │              JAVA HTTP SERVER (HotelServer.java)         │   │
│  │              ================================            │   │
│  │  Port 8080 · 19 Handler Classes · 1,040 lines           │   │
│  │                                                         │   │
│  │  ┌──────────┐ ┌──────────┐ ┌──────────┐ ┌──────────┐   │   │
│  │  │ Login    │ │ Signup   │ │ Book     │ │ Rooms    │   │   │
│  │  │ Handler  │ │ Handler  │ │ Handler  │ │ Handler  │   │   │
│  │  ├──────────┤ ├──────────┤ ├──────────┤ ├──────────┤   │   │
│  │  │ Avail.   │ │ Users    │ │ Stats    │ │ Contact  │   │   │
│  │  │ Handler  │ │ Handler  │ │ Handler  │ │ Handler  │   │   │
│  │  ├──────────┤ ├──────────┤ ├──────────┤ ├──────────┤   │   │
│  │  │ Check    │ │ Profile  │ │ Booking  │ │ Static   │   │   │
│  │  │ Field    │ │ Update   │ │ Delete   │ │ File     │   │   │
│  │  └──────────┘ └──────────┘ └──────────┘ └──────────┘   │   │
│  └──────────────────────────┬──────────────────────────────┘   │
│                             │                                   │
│                    Reads/Writes .txt Files                      │
│                             ▼                                   │
│  ┌─────────────────────────────────────────────────────────┐   │
│  │  📁 data/                                               │   │
│  │  ├── users.txt          (username|password|name|...)    │   │
│  │  ├── rooms.txt          (Standard|50|AC,WiFi,...)      │   │
│  │  ├── reservations.txt   (id|username|name|roomType|...) │   │
│  │  ├── room_assignments.txt (roomNum|username)            │   │
│  │  └── contacts.txt       (name|email|message)           │   │
│  └─────────────────────────────────────────────────────────┘   │
│                                                                 │
└─────────────────────────────────────────────────────────────────┘
```

---

### 🔄 Data Flow: What Happens When a User Books

```text
USER ACTION                SERVER RESPONSE           FILE UPDATED
────────────               ───────────────           ────────────
1. Visit website  ─────▶   StaticFileHandler         ─
                          serves index.html

2. Click "Login"  ─────▶   Serves login.html          ─

3. Submit credentials ─▶  LoginHandler verifies       reads users.txt
                          username/email + password

4. Browse rooms   ─────▶  RoomsHandler returns JSON   reads rooms.txt

5. Fill booking form ─▶   BookHandler validates       writes:
                          room number 1-30,           reservations.txt
                          not taken, saves            room_assignments.txt

6. View receipt   ─────▶  BookingByIdHandler          reads reservations.txt
                          returns booking details
```

---

### 🎯 Key Point to Make

> *"Notice: everything is **file-based**. No MySQL, no PostgreSQL, no MongoDB. Just pure Java reading and writing `.txt` files. This makes the project simple, portable, and easy to inspect."*

---

# 🌐 SECTION 4: FRONTEND — ALL HTML PAGES (~6 min)

---

### 🎯 Speaker Script

> *"Let me walk you through all 9 web pages. The design follows a clean, minimal style inspired by Arch Linux — table-based layout, simple colors, no CSS frameworks."*

---

### 📄 PAGE 1: `index.html` — Home Page (81 lines)

| Feature | Detail |
|---------|--------|
| **Layout** | Table layout with header, nav, sidebar, content |
| **Auth Guard** | Redirects to `login.html` if `localStorage` has no user |
| **Welcome** | Shows user's name from localStorage |
| **Nav** | Home, Rooms, Book Now, Dashboard, Admin, About, Logout |
| **Admin Link** | Hidden via JS if user is not admin |

```javascript
// Auth guard — runs immediately
if (!localStorage.getItem("hotelUser")) {
    window.location.href = "pages/login.html";
}

// Admin link visibility
if (!u.isAdmin) { document.getElementById('adminLink').style.display = 'none'; }
```

---

### 📄 PAGE 2: `login.html` — Login Page (108 lines)

| Feature | Detail |
|---------|--------|
| **Fields** | Email/Username + Password |
| **Validation** | Client-side: all fields required, password must contain digits |
| **Password Input** | `oninput` strips non-digits (`[^0-9]`), 200ms reveal on type |
| **API Call** | `POST /api/login` sends `{email, password}` |
| **Error Messages** | Somali: "Fadlan buuxi dhammaan goobaha!", "Password-ka waa khalad!" |
| **Success** | Saves user to `localStorage`, redirects to `index.html` |

> 🎤 *"Login accepts **email OR username**. The server checks both fields."*

```javascript
// Password filter — digits only
oninput="this.value=this.value.replace(/[^0-9]/g,'')"

// Password reveal on type (200ms then hides again)
pw.addEventListener('input', function() {
    clearTimeout(timer);
    this.type = 'text';
    timer = setTimeout(function() { pw.type = 'password'; }, 200);
});
```

---

### 📄 PAGE 3: `signup.html` — Registration Page (184 lines)

| Feature | Detail |
|---------|--------|
| **Fields** | Username, Password, Full Name, Phone, Email |
| **Validations** | Username starts with letter, password has digit, phone ≥7 digits, email contains `@gmail.com` |
| **Real-time Check** | 400ms debounce on username/phone/email → `POST /api/check` |
| **Visual Feedback** | Red border `.input-taken` + red "Waa la qaatay" message |
| **Success** | Alert: "Waa La Sameeye Account. Hadda Login Samee" → redirects to login |

> 🎤 *"The real-time duplicate check is really cool — as you type, it calls the server to check if the username, phone, or email is already taken. No page reload needed."*

```javascript
// Real-time duplicate check with 400ms debounce
fields.forEach(function(f) {
    var input = document.getElementById(f);
    input.addEventListener('input', function() {
        clearTimeout(timers[f]);
        timers[f] = setTimeout(function() { checkField(f); }, 400);
    });
});

function checkField(field) {
    // POST /api/check → if res.taken → red border + "Waa la qaatay"
}
```

---

### 📄 PAGE 4: `rooms.html` — Room Listing (94 lines)

| Feature | Detail |
|---------|--------|
| **Dynamic** | Loads rooms from `GET /api/rooms` as JSON |
| **Cards** | Each room: name, price, facilities, "Book Now" button |
| **Sidebar** | Quick links to book specific room type |
| **URL Param** | `reservation.html?room=standard` auto-selects room |

```javascript
// Room card HTML generated from JSON
html += '<table class="room-table">';
html += '<tr><td class="room-img">' + r.name + '</td>';
html += '<td><h3>' + r.name + '</h3>';
html += '<p>Price: <strong>$' + r.price + '</strong> / night</p>';
html += '<p><strong>Facilities:</strong> ' + amenities + '</p>';
html += '<a href="reservation.html?room=' + r.name.toLowerCase() + '" class="btn">Book Now</a>';
html += '</td></tr></table>';
```

---

### 📄 PAGE 5: `reservation.html` — Booking Form (452 lines) ⭐

| Feature | Detail |
|---------|--------|
| **Room Type** | Select dropdown loaded from `GET /api/rooms` |
| **Room Number** | Dropdown of rooms 1–30, taken ones grayed+disabled |
| **Availability Count** | Shows "X of 30 rooms available" (red if ≤5 or full) |
| **Nights Slider** | Range 1–30, live total price display |
| **Check-Out** | Auto-calculated from check-in + nights |
| **Cost Breakdown** | Table showing each night's cost + running total |
| **Check Availability** | `POST /api/availability` with date overlap detection |
| **Re-check Before Submit** | Sync `GET /api/rooms/available` to catch last-second booking |
| **URL Param** | `?room=standard` auto-selects room type |

> 🎤 *"This is the most interactive page. The pricing slider shows a live cost breakdown table — every single night's cost and a running total. And before submitting, we do a synchronous check to make sure the room wasn't taken between page load and clicking confirm."*

```javascript
// Pricing slider - live calculation
function updatePricing() {
    var nights = parseInt(document.getElementById('nightsSlider').value);
    var price = parseInt(selectedOpt.getAttribute('data-price'));
    var runningTotal = price * nights;
    
    // Build cost breakdown table row by row
    for (var i = 1; i <= nights; i++) {
        displayTotal += price;
        html += '<tr><td>Night ' + i + '</td>';
        html += '<td>$' + price + '</td>';
        html += '<td>$' + displayTotal + '</td></tr>';
    }
}

// Sync re-check before submit
var recheck = new XMLHttpRequest();
recheck.open('GET', '/api/rooms/available', false); // synchronous!
recheck.send();
```

---

### 📄 PAGE 6: `dashboard.html` — User Dashboard (208 lines)

| Feature | Detail |
|---------|--------|
| **My Bookings** | Table: ID, Room Type, Room No., Check-In, Check-Out, Guests, Cancel button |
| **Cancel Booking** | Double confirm → `POST /api/bookings/delete` → refreshes list |
| **Edit Profile** | Name, Phone, Email → `POST /api/user/update` → updates localStorage |
| **Delete Account** | Red-bordered section (hidden for admins) → double confirm → clears everything |
| **Admin Self-Protection** | "Delete My Account" hidden if `user.isAdmin === true` |

---

### 📄 PAGE 7: `admin.html` — Admin Panel (340 lines)

| Feature | Detail |
|---------|--------|
| **Stats** | Total Users, Room Types, Total Bookings (from `GET /api/stats`) |
| **Manage Rooms** | Add (name, price, amenities) + Delete room types |
| **All Users** | Table with Edit/Delete. Own Edit/Delete hidden, shows "(you)" |
| **All Bookings** | Full table with Delete action for any booking |
| **Admin Protection** | Server blocks deleting `admin` or any `isAdmin=true` user |

```javascript
// Admin self-protection in user list
if (!isSelf) {
    html += '<button onclick="editUser(...)">Edit</button>';
}
if (!isSelf && u.isAdmin !== "true") {
    html += '<button onclick="deleteUser(...)">Delete</button>';
}
if (isSelf) {
    html += '<span style="color:#888;">(you)</span>';
}
```

---

### 📄 PAGE 8: `receipt.html` — Booking Receipt (143 lines)

| Feature | Detail |
|---------|--------|
| **URL Param** | `?id=123` loads specific booking |
| **Print** | CSS `@media print` hides nav/sidebar/buttons |
| **Receipt Box** | Clean print-friendly layout |
| **Details** | Reservation ID, Guest Name, Room Type, Room Number, Dates, Guests, Phone, Email |

```css
@media print {
    .no-print { display: none; }
    .receipt-box { border: 2px solid #000; }
}
```

---

### 📄 PAGE 9: `hotel-info.html` — About + Contact (144 lines)

| Feature | Detail |
|---------|--------|
| **Info** | Hotel description, location, amenities, room types |
| **Contact Form** | `POST /api/contact` → saves to `contacts.txt` |
| **Auto-fill** | Name + email from logged-in user's localStorage |
| **Success** | "Message sent! Thanks for contacting us." |

---

### 📄 PAGE 10: `logout.html` — Logout Confirmation (48 lines)

| Feature | Detail |
|---------|--------|
| **Design** | Centered log-layout, clean confirmation box |
| **Prompt** | "Ma hubtaa inaad logout dhahaysid?" (Are you sure you want to logout?) |
| **Logout Button** | Red styled → clears `localStorage` → redirects to `login.html` |
| **Cancel Button** | `window.history.back()` |

---

### 🎨 CSS: `style.css` (515 lines)

| Feature | Detail |
|---------|--------|
| **Theme** | Arch Linux inspired — clean, minimal, table-based |
| **Layout** | Table layout (2007-era constraint for the course) |
| **Colors** | Blue `#08c` accents, gray `#333` header, white content |
| **Components** | `.btn`, `.data-table`, `.info-box`, `.confirm-box`, `.room-table`, `.cost-table`, `.form-table`, `.log-layout` |
| **Hover Effects** | Rows highlight yellow `#ffd`, buttons invert colors |

---

# ☕ SECTION 5: BACKEND — JAVA SERVER (~7 min)

---

### 🎯 Speaker Script

> *"Now let's look at the heart of the system — HotelServer.java. This is 1,040 lines of pure Java, running on `com.sun.net.httpserver.HttpServer`. No Spring, no Tomcat, no frameworks. Just the JDK's built-in HTTP server."*

---

### 🏗️ Server Startup (`main` method)

```java
public static void main(String[] args) throws Exception {
    // Create data files if they don't exist
    // Default admin: admin / 1234
    // Default rooms: Standard $50, Deluxe $80, Suite $120
    
    HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
    
    // Register all API endpoints (19 contexts)
    server.createContext("/api/login", new LoginHandler());
    server.createContext("/api/signup", new SignupHandler());
    server.createContext("/api/book", new BookHandler());
    server.createContext("/api/bookings", new BookingsHandler());
    server.createContext("/api/booking", new BookingByIdHandler());
    server.createContext("/api/users", new UsersHandler());
    server.createContext("/api/users/update", new UserUpdateHandler());
    server.createContext("/api/users/delete", new UserDeleteHandler());
    server.createContext("/api/user/update", new ProfileUpdateHandler());
    server.createContext("/api/rooms", new RoomsHandler());
    server.createContext("/api/rooms/add", new RoomAddHandler());
    server.createContext("/api/rooms/delete", new RoomDeleteHandler());
    server.createContext("/api/bookings/delete", new BookingDeleteHandler());
    server.createContext("/api/stats", new StatsHandler());
    server.createContext("/api/contact", new ContactHandler());
    server.createContext("/api/availability", new AvailabilityHandler());
    server.createContext("/api/check", new CheckFieldHandler());
    server.createContext("/api/rooms/available", new RoomsAvailableHandler());
    server.createContext("/", new StaticFileHandler());
    
    server.start();
    System.out.println("Server running on http://localhost:8080");
}
```

---

### 🗺️ All 19 API Handlers — Complete Map

| # | Handler | Route | Method | What It Does |
|---|---------|-------|--------|-------------|
| 1 | **LoginHandler** | `/api/login` | POST | Authenticates by username OR email, returns user JSON |
| 2 | **SignupHandler** | `/api/signup` | POST | Validates all fields, checks duplicates, saves user |
| 3 | **CheckFieldHandler** | `/api/check` | POST | Real-time duplicate check (username/phone/email) |
| 4 | **RoomsAvailableHandler** | `/api/rooms/available` | GET | Returns available room numbers 1-30 |
| 5 | **BookHandler** | `/api/book` | POST | Validates room 1-30, checks not taken, saves reservation |
| 6 | **BookingsHandler** | `/api/bookings` | GET | Lists bookings, optional `?username=` or `?roomType=` filter |
| 7 | **BookingByIdHandler** | `/api/booking` | GET | Single booking by `?id=` |
| 8 | **UsersHandler** | `/api/users` | GET | Lists all users (admin only) |
| 9 | **UserUpdateHandler** | `/api/users/update` | POST | Admin edits user details |
| 10 | **UserDeleteHandler** | `/api/users/delete` | POST | Deletes user + their bookings + room assignments |
| 11 | **ProfileUpdateHandler** | `/api/user/update` | POST | User edits own profile with duplicate check |
| 12 | **RoomsHandler** | `/api/rooms` | GET | Lists all room types with prices & amenities |
| 13 | **RoomAddHandler** | `/api/rooms/add` | POST | Admin adds new room type |
| 14 | **RoomDeleteHandler** | `/api/rooms/delete` | POST | Admin deletes room type |
| 15 | **BookingDeleteHandler** | `/api/bookings/delete` | POST | Cancels booking, frees room assignment |
| 16 | **StatsHandler** | `/api/stats` | GET | Returns total users, rooms, bookings |
| 17 | **ContactHandler** | `/api/contact` | POST | Saves contact form message to file |
| 18 | **AvailabilityHandler** | `/api/availability` | POST | Date overlap check for specific room type |
| 19 | **StaticFileHandler** | `/` | GET | Serves HTML, CSS, JS files from disk |

---

### 🔐 LoginHandler — Deep Dive

```java
static class LoginHandler implements HttpHandler {
    public void handle(HttpExchange ex) throws IOException {
        String body = readBody(ex);
        String login = getJsonValue(body, "email");  // Try "email" field
        if (login.isEmpty()) {
            login = getJsonValue(body, "username");  // Fallback to "username"
        }
        String password = getJsonValue(body, "password");
        
        // Check credentials against users.txt
        for (String[] u : readUsers()) {
            boolean match = u[0].equals(login) || u[4].equals(login); // username OR email
            if (match && u[1].equals(password)) {
                // Return user JSON: username, name, phone, email, isAdmin
                sendJson(ex, 200, successResponse);
                return;
            }
        }
        
        // Smart error: does the login exist?
        if (loginNotFound) {
            sendJson(ex, "Email-ka ama Username-ka lama diiwaan gelin wali!");
        } else {
            sendJson(ex, "Password-ka waa khalad!");
        }
    }
}
```

> 🎤 *"Notice: the login accepts **either username or email** in one field. The server tries the `email` field first, then falls back to `username`. It searches both `u[0]` (username) and `u[4]` (email). And the error messages are in Somali — a nice touch for our local users."*

---

### 📅 AvailabilityHandler — Date Overlap Detection

```java
static class AvailabilityHandler implements HttpHandler {
    // Check if a room type is available for given dates
    // Uses string comparison (YYYY-MM-DD format works for date comparison!)
    
    for (String line : readLines("reservations.txt")) {
        String[] parts = line.split("\\|");
        if (parts[3].equalsIgnoreCase(roomType)) {
            // Handle both 9-field (old) and 10-field (new) formats
            String existCheckin = parts.length >= 10 ? parts[5] : parts[4];
            String existCheckout = parts.length >= 10 ? parts[6] : parts[5];
            
            // Overlap: (checkin < existCheckout) && (checkout > existCheckin)
            if (checkin.compareTo(existCheckout) < 0 && 
                checkout.compareTo(existCheckin) > 0) {
                available = false;
                break;
            }
        }
    }
}
```

> 🎤 *"The date overlap logic is simple but effective. Since dates are in YYYY-MM-DD format, we can use string comparison. Two date ranges overlap if `checkin < existCheckout AND checkout > existCheckin`. Plus we handle both old 9-field and new 10-field reservation formats for backward compatibility."*

---

### 🧾 BookHandler — Creating a Reservation

```java
static class BookHandler implements HttpHandler {
    public void handle(HttpExchange ex) throws IOException {
        // 1. Validate all fields
        // 2. Validate room number 1-30
        // 3. Check room not already taken (from room_assignments.txt)
        // 4. Check total bookings < 30
        // 5. Generate next ID (max existing + 1)
        // 6. Write to reservations.txt (10 fields)
        // 7. Write to room_assignments.txt
        
        int nextId = 1;
        // Find max existing ID
        for (String line : Files.readAllLines(f.toPath())) {
            nextId = Integer.parseInt(line.split("\\|")[0]) + 1;
        }
        
        // Save reservation (10-field format)
        fw.write(nextId + "|" + username + "|" + name + "|" + roomType + "|" 
               + roomNum + "|" + checkin + "|" + checkout + "|" + guests + "|" 
               + phone + "|" + email + "\n");
        
        // Save room assignment
        fw.write(roomNum + "|" + username + "\n");
    }
}
```

---

### 🗑️ UserDeleteHandler — Full Account Cleanup

```java
static class UserDeleteHandler implements HttpHandler {
    public void handle(HttpExchange ex) throws IOException {
        // 1. Prevent deleting "admin" or any isAdmin=true user
        // 2. Remove user from users.txt
        // 3. Remove ALL their bookings from reservations.txt
        // 4. Remove ALL their room assignments from room_assignments.txt
        //    (freeing the rooms for other users)
    }
}
```

> 🎤 *"When a user deletes their account, we clean up everything — the user record, all their bookings, and all their room assignments. This frees up rooms for other guests. But we protect admin accounts — you cannot delete the admin user or any user with admin privileges."*

---

### 🛠️ Helper Methods

```java
// Read users.txt → List<String[]>
static List<String[]> readUsers() { ... }

// Read rooms.txt → List<String[]>
static List<String[]> readRooms() { ... }

// Read room_assignments.txt → List<String[]>
static List<String[]> readRoomAssignments() { ... }

// Read HTTP request body
static String readBody(HttpExchange ex) { ... }

// Parse JSON value (simple, no library!)
static String getJsonValue(String json, String key) { ... }

// Send JSON response
static void sendJson(HttpExchange ex, int status, String json) { ... }
```

> 🎤 *"Notice: we don't use any JSON library. The `getJsonValue` method manually parses JSON strings. This keeps the project dependency-free — just pure Java."*

---

# 📦 SECTION 6: OOP CONCEPTS IN CODE (~5 min)

---

### 🎯 Speaker Script

> *"This project demonstrates all major Object-Oriented Programming concepts. Let me show you each one with actual code from our project."*

---

### 🔒 1. Encapsulation — `Room.java`

```java
public class Room {
    private String roomNumber;      // 🔒 Private field
    private String roomType;        // 🔒 Private field
    private double pricePerNight;   // 🔒 Private field
    private boolean isAvailable;    // 🔒 Private field
    private int capacity;           // 🔒 Private field
    
    // Public getters — controlled access
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    
    // Public setter — with control
    public void setAvailable(boolean available) { isAvailable = available; }
}
```

> 🎤 *"Data hiding — fields are private, accessed only through public getters/setters. The internal state is protected from direct modification."*

---

### 🧬 2. Inheritance — `Room` → `StandardRoom` / `DeluxeRoom` / `SuiteRoom`

```java
// Base class
public class Room { ... }

// Child classes — inherit everything from Room
public class StandardRoom extends Room {
    public StandardRoom(String roomNumber) {
        super(roomNumber, "Standard", 50.0, 2);  // super() calls parent constructor
    }
}

public class DeluxeRoom extends Room {
    public DeluxeRoom(String roomNumber) {
        super(roomNumber, "Deluxe", 80.0, 3);
    }
}

public class SuiteRoom extends Room {
    public SuiteRoom(String roomNumber) {
        super(roomNumber, "Suite", 120.0, 5);
    }
}
```

```text
INHERITANCE HIERARCHY:

        ┌──────────┐
        │   Room   │  ← Base class (parent)
        └────┬─────┘
             │
     ┌───────┼───────┐
     │       │       │
┌────┴──┐ ┌──┴────┐ ┌┴──────┐
│Standard│ │Deluxe │ │ Suite │
│ Room   │ │ Room  │ │ Room  │
└───────┘ └───────┘ └───────┘
Price: $50   $80      $120
Bed:  Double King    2 Kings
```

> 🎤 *"The child classes inherit all fields and methods from Room. They only need to add what makes them unique — like bed type for StandardRoom, sea view for DeluxeRoom, kitchen for SuiteRoom."*

---

### 🔄 3. Polymorphism — `@Override`

```java
// Base class
public class Room {
    public void displayInfo() {
        System.out.println("Room " + roomNumber + " | " + roomType + 
                           " | $" + pricePerNight + "/night");
    }
}

// StandardRoom overrides displayInfo
public class StandardRoom extends Room {
    @Override
    public void displayInfo() {
        System.out.print("[STANDARD] ");
        super.displayInfo();  // Calls parent's version
        System.out.println("  Bed: " + bedType);
    }
}

// Same method name, different behavior!
```

> 🎤 *"Same method name (`displayInfo`), different implementation in each class. The `@Override` annotation tells Java we're replacing the parent's method."*

---

### 📐 4. Constructor Overloading — `Customer.java`

```java
public class Customer {
    private String name;
    private String phone;
    private String email;
    
    // Constructor 1 — all fields
    public Customer(String name, String phone, String email) { ... }
    
    // Constructor 2 — name only (overloading!)
    public Customer(String name) {
        this.name = name;
        this.phone = "Unknown";
        this.email = "Unknown";
    }
    
    // Constructor 3 — name + phone (overloading!)
    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.email = "Unknown";
    }
}
```

> 🎤 *"Three constructors with the same name but different parameters. This is constructor overloading — one of Java's polymorphism features."*

---

### 📊 5. Static Members — `Reservation.java`

```java
public class Reservation {
    private static int counter = 100;  // 🔷 Shared across ALL instances
    
    private int reservationId;
    
    public Reservation(...) {
        this.reservationId = counter++;  // Auto-incrementing ID
    }
}
```

> 🎤 *"Static means the variable belongs to the class, not to any single object. Every new reservation gets a unique ID by incrementing the shared counter."*

---

### 📂 6. File I/O — Reading & Writing

```java
// WRITING to users.txt
public static void saveUsers(ArrayList<User> users) {
    FileWriter writer = new FileWriter("../data/users.txt");
    for (User u : users) {
        writer.write(u.username + "|" + u.password + "|" + ... + "\n");
    }
    writer.close();
}

// READING from users.txt (in HotelServer)
static List<String[]> readUsers() {
    List<String[]> users = new ArrayList<>();
    for (String line : Files.readAllLines(f.toPath())) {
        String[] parts = line.split("\\|");
        users.add(parts);
    }
    return users;
}
```

---

### 🎯 7. Exception Handling — Try-Catch-Finally

```java
// Multiple catch blocks + finally
try {
    int ch = Integer.parseInt(sc.nextLine());
    if (ch < 1) throw new IndexOutOfBoundsException("Khalad!");
    // ... booking logic
} catch (NumberFormatException e) {
    System.out.println("Number khalad!");
} catch (IllegalArgumentException e) {
    System.out.println("Error: " + e.getMessage());
} catch (IndexOutOfBoundsException e) {
    System.out.println("Error: " + e.getMessage());
} finally {
    System.out.println("(finally block always runs)");
}
```

---

### 📚 All OOP Concepts Summary Table

| # | Concept | Where? | Code Example |
|---|---------|--------|-------------|
| 🔒 | **Encapsulation** | `Room.java` | `private` fields + `public` getters |
| 🧬 | **Inheritance** | `StandardRoom extends Room` | Reuses parent fields/methods |
| 🔄 | **Polymorphism** | `@Override displayInfo()` | Same method, different behavior |
| 📐 | **Constructor Overloading** | `Customer.java` | 3 constructors with different params |
| 📊 | **Static Members** | `Reservation.counter` | Shared auto-increment ID |
| 📂 | **File I/O** | `User.saveUsers()`, `readUsers()` | Read/write `.txt` files |
| 🎯 | **Exception Handling** | `bookRoom()` | try-catch-finally, multiple catches |

---

# 📁 SECTION 7: DATA STORAGE & FLOW (~3 min)

---

### 🎯 Speaker Script

> *"All data is stored in simple pipe-delimited `.txt` files. Let me show you each file's format."*

---

### 📄 `data/users.txt`

```text
admin|1234|Admin|000|admin@hotel.com|true
```

| Field | Index | Meaning |
|-------|-------|---------|
| `admin` | `u[0]` | **Username** — unique, starts with letter |
| `1234` | `u[1]` | **Password** — digits only, contains ≥1 number |
| `Admin` | `u[2]` | **Full Name** |
| `000` | `u[3]` | **Phone** — ≥7 digits, unique |
| `admin@hotel.com` | `u[4]` | **Email** — must contain `@gmail.com`, unique |
| `true` | `u[5]` | **isAdmin** — `true` or `false` |

---

### 📄 `data/rooms.txt`

```text
Standard|50|AC,WiFi,TV,Free Parking
Deluxe|80|AC,WiFi,TV,Mini Bar,Breakfast
Suite|120|AC,WiFi,TV,Jacuzzi,Breakfast,Room Service 24/7
```

| Field | Meaning |
|-------|---------|
| `Standard` | Room type name |
| `50` | Price per night (USD) |
| `AC,WiFi,TV,...` | Comma-separated amenities |

---

### 📄 `data/reservations.txt` (10-field format)

```text
1|john|John Doe|Standard|5|2026-06-01|2026-06-03|2|1234567|john@gmail.com
```

| # | Field | Meaning |
|---|-------|---------|
| 1 | `1` | Reservation ID (auto-increment) |
| 2 | `john` | Username who booked |
| 3 | `John Doe` | Guest full name |
| 4 | `Standard` | Room type |
| 5 | `5` | **Room number** (1-30) |
| 6 | `2026-06-01` | Check-in date |
| 7 | `2026-06-03` | Check-out date |
| 8 | `2` | Number of guests |
| 9 | `1234567` | Phone |
| 10 | `john@gmail.com` | Email |

> ⚠️ **Backward Compatible**: Old 9-field format (no room number) is also supported.

---

### 📄 `data/room_assignments.txt`

```text
5|john
```

| Field | Meaning |
|-------|---------|
| `5` | Room number (1-30, global pool) |
| `john` | Username assigned to this room |

> 🎤 *"This is the global room pool. Room numbers 1-30 are shared among all users. When someone books, their room goes here. When they cancel, it's removed. One user = one room at a time."*

---

### 🔄 Complete Data Flow Diagram

```text
REGISTRATION:
  signup.html ──POST──▶ SignupHandler ──write──▶ users.txt

LOGIN:
  login.html ──POST──▶ LoginHandler ──read──▶ users.txt

BOOKING:
  reservation.html ──POST──▶ BookHandler ──write──▶ reservations.txt
                                                    room_assignments.txt

CANCEL:
  dashboard.html ──POST──▶ BookingDeleteHandler ──remove from──▶ reservations.txt
                                                                room_assignments.txt

DELETE ACCOUNT:
  dashboard.html ──POST──▶ UserDeleteHandler ──remove from──▶ users.txt
                                                              reservations.txt
                                                              room_assignments.txt

CONTACT FORM:
  hotel-info.html ──POST──▶ ContactHandler ──write──▶ contacts.txt
```

---

# ✨ SECTION 8: KEY FEATURES (~4 min)

---

### 🎯 Speaker Script

> *"Let me highlight the coolest features of our system."*

---

### 🎚️ 1. Interactive Pricing Slider

```text
┌──────────────────────────────────────────────────────────────┐
│  Room Type:  [Standard ▼]  Price: $50/night                  │
│  Nights:     ════════●═══════════════ 1 ═══════════════ 30   │
│              └─────────── 15 ──────────┘                     │
│                                                              │
│  ┌── Total: $750 ──────────────────────────────────────────┐ │
│                                                              │
│  COST BREAKDOWN:                                             │
│  ┌──────────┬──────────┬────────────────┐                   │ │
│  │ Night 1  │  $50     │  $50           │                   │ │
│  │ Night 2  │  $50     │  $100          │                   │ │
│  │ ...      │  ...     │  ...           │                   │ │
│  │ Night 15 │  $50     │  $750          │                   │ │
│  ├──────────┼──────────┼────────────────┤                   │ │
│  │ TOTAL    │          │  $750          │                   │ │
│  └──────────┴──────────┴────────────────┘                   │ │
└──────────────────────────────────────────────────────────────┘
```

> Live price = `pricePerNight × nights`. Cost breakdown table updates in real-time.

---

### 🧾 2. Printable Receipt

```text
┌──────────────────────────────────────┐
│        RESERVATION RECEIPT           │
│                                      │
│  Booking Confirmed! ✅               │
│                                      │
│  Reservation ID:  42                 │
│  Guest Name:      John Doe           │
│  Room Type:       Deluxe             │
│  Room Number:     15                 │
│  Check-In:        2026-06-01         │
│  Check-Out:       2026-06-05         │
│  Guests:          2                  │
│  Phone:           612345678          │
│  Email:           john@gmail.com     │
│                                      │
│  Kismaayo Hotel — Kismaayo, Somalia  │
│  Phone: 617939022                    │
└──────────────────────────────────────┘
```

> Click "Print Receipt" → browser print dialog → clean, no-nav layout.

---

### 🔍 3. Real-Time Duplicate Checking

```text
Username: [ali                     ] ⚠️ Waa la qaatay  ← Red border
Phone:    [612345678               ] ✅ Available       ← Normal border
Email:    [ali@gmail.com           ] ⚠️ Waa la qaatay  ← Red border
```

> 400ms debounce → `POST /api/check` → red `.input-taken` border + error message.

---

### ⚙️ 4. Admin Panel

```text
╔══════════════════════════════════════════════════════════════╗
║                     ADMIN PANEL                             ║
╠══════════════════════════════════════════════════════════════╣
║  📊 STATS:                                                  ║
║  ┌──────────┬──────────┬──────────────┐                     ║
║  │  5 Users │ 4 Rooms  │  12 Bookings │                     ║
║  └──────────┴──────────┴──────────────┘                     ║
║                                                             ║
║  🏠 MANAGE ROOMS:                                           ║
║  [Room Name] [Price] [Amenities] [Add Room]                 ║
║  ┌──────────┬───────┬──────────────────────┬────────┐       ║
║  │ Standard │ $50   │ AC,WiFi,TV           │ Delete │       ║
║  │ Deluxe   │ $80   │ AC,WiFi,TV,Mini Bar  │ Delete │       ║
║  │ Suite    │ $120  │ AC,WiFi,TV,Jacuzzi   │ Delete │       ║
║  └──────────┴───────┴──────────────────────┴────────┘       ║
║                                                             ║
║  👥 ALL USERS:                                              ║
║  ┌──────────┬──────┬──────────┬────────────────┬──────┬───┐ ║
║  │ admin    │Admin │ admin@.. │ 111            │Admin │you│ ║
║  │ john     │ John │ john@..  │ 612345678      │User  │ED │ ║
║  └──────────┴──────┴──────────┴────────────────┴──────┴───┘ ║
║                                                             ║
║  📋 ALL BOOKINGS: (with Delete buttons)                     ║
╚══════════════════════════════════════════════════════════════╝
```

---

### 🔐 5. Security Features

| Feature | Where | How |
|---------|-------|-----|
| **Password digits-only** | Client + Server | `oninput` filter + regex `.*\\d.*` |
| **Username starts with letter** | Client + Server | `^[a-zA-Z].*` |
| **Phone ≥ 7 digits** | Client + Server | `length() < 7` check |
| **Email must be @gmail.com** | Client + Server | `contains("@gmail.com")` |
| **No duplicate username/email/phone** | Client + Server | Real-time check + server validation |
| **Admin self-protection** | Client + Server | Hide Edit/Delete buttons + block on server |
| **Login by email OR username** | Server | Searches both `u[0]` and `u[4]` |
| **Somali error messages** | Server | All responses in Somali language |

---

# 🌐 SECTION 9: LIVE WEBSITE DEMO (~5 min)

---

### 🎯 Demo Script — Step by Step

> *"Now let me show you the live website in action. I'll run the server and demonstrate the complete user journey."*

---

### ⚙️ How to Start the Server

```bash
# Terminal 1: Start the server
cd ~/HotelReservation/java
javac HotelServer.java   # Compile (already compiled)
java HotelServer         # Run

# Output:
# ========================================
#   Kismaayo Hotel Server running on
#   http://localhost:8080
# ========================================
```

---

### 🎬 Demo Flow (Slide Through These Steps)

```text
STEP 1: OPEN WEBSITE
  └─▶ Open browser → http://localhost:8080
  └─▶ Redirected to login.html (not logged in)
  └─▶ SHOW: Login page with Email/Username + Password fields

STEP 2: REGISTER NEW USER
  └─▶ Click "Register" link
  └─▶ Fill form: username, password, name, phone, email
  └─▶ SHOW: Real-time duplicate check (type taken username → red)
  └─▶ Submit → "Waa La Sameeye Account. Hadda Login Samee"
  └─▶ SHOW: Redirected to login page

STEP 3: LOGIN
  └─▶ Login with email OR username
  └─▶ SHOW: Home page with "Welcome, [Name]!"
  └─▶ SHOW: Nav bar with all links

STEP 4: BROWSE ROOMS
  └─▶ Click "Rooms" in nav
  └─▶ SHOW: Room cards with prices and amenities
  └─▶ SHOW: Sidebar quick links

STEP 5: BOOK A ROOM
  └─▶ Click "Book Now" → reservation.html
  └─▶ SHOW: Room Type dropdown (loaded from server)
  └─▶ SHOW: Room Number dropdown (1-30, taken grayed)
  └─▶ SHOW: "X of 30 rooms available" count
  └─▶ Select dates and move nights slider
  └─▶ SHOW: Live cost breakdown table updating
  └─▶ SHOW: Check Availability button
  └─▶ Submit → redirected to receipt page

STEP 6: VIEW RECEIPT
  └─▶ SHOW: Clean receipt with all booking details
  └─▶ Click "Print" → print preview (clean, no nav)

STEP 7: USER DASHBOARD
  └─▶ Click "Dashboard"
  └─▶ SHOW: My Bookings table with Cancel button
  └─▶ SHOW: Edit Profile form (pre-filled)
  └─▶ SHOW: Delete Account section (red border)

STEP 8: ADMIN PANEL
  └─▶ Logout → Login as admin / 1234
  └─▶ Click "Admin"
  └─▶ SHOW: Stats (Total Users, Room Types, Total Bookings)
  └─▶ SHOW: Manage Rooms (Add/Delete)
  └─▶ SHOW: All Users table (Edit/Delete, own hidden)
  └─▶ SHOW: All Bookings table

STEP 9: LOGOUT
  └─▶ Click "Logout" in nav
  └─▶ SHOW: "Ma hubtaa inaad logout dhahaysid?" page
  └─▶ Click Logout → redirected to login page
```

---

### 📝 Key Talking Points During Demo

| Moment | Say This |
|--------|----------|
| When loading rooms | *"See how the rooms load dynamically from the server? No page refresh — just AJAX."* |
| When slider moves | *"Watch the cost breakdown table update with every slide — real-time pricing."* |
| When room number shows | *"Rooms 1-30 are a global pool. Taken rooms are grayed out so you can't book them."* |
| When checking availability | *"The server checks for date overlap. Same room type, different dates — no conflict."* |
| When receipt prints | *"The print CSS hides all navigation elements — just the receipt."* |
| When admin deletes user | *"The server also frees all their rooms. And admin accounts are protected."* |

---

# ❓ SECTION 10: Q&A PREPARATION (~5 min)

---

### 🎯 Speaker Script

> *"Thank you for your attention. I'm happy to answer any questions."*

---

### 🎤 Expected Teacher Questions + Model Answers

---

#### Q1: "Why did you use Java for the backend instead of PHP?"

**Answer:**
> *"The assignment required pure Java. We used `com.sun.net.httpserver.HttpServer` — a built-in JDK class — so there's zero external dependencies. No Tomcat, no Spring, no frameworks. Just `javac` and `java`. PHP wasn't an option because this is a Java course."*

---

#### Q2: "Why .txt files instead of a database like MySQL?"

**Answer:**
> *"Three reasons:*
> 1. *Simplicity — no setup, no configuration, no server installation.*
> 2. *Transparency — the teacher can open any `.txt` file and see the data immediately.*
> 3. *Focus — the assignment tests Java File I/O concepts (FileReader, FileWriter, BufferedReader), not database skills. Using a database would bypass the learning objectives."*

---

#### Q3: "Explain the date overlap detection logic."

**Answer:**
> *"Two date ranges [A, B] and [C, D] overlap if `A < D AND C < B`. Since our dates are in YYYY-MM-DD format, we can use string comparison (`compareTo`). In the code: `checkin.compareTo(existCheckout) < 0 && checkout.compareTo(existCheckin) > 0`. This catches any overlap regardless of which date comes first."*

```java
// Overlap visualization:
// Existing:    ████████░░░░░░
// New A:       ░░░░████████░░  ✅ Available
// New B:       ░░░░░░████████  ❌ Overlap!
// New C:       ████████░░░░░░  ❌ Overlap!
```

---

#### Q4: "What OOP concepts did you use?"

**Answer:**
> *"We used **six** major OOP concepts:*
> - **Encapsulation** — private fields with public getters in `Room.java`
> - **Inheritance** — `StandardRoom`, `DeluxeRoom`, `SuiteRoom` all `extends Room`
> - **Polymorphism** — `@Override displayInfo()` in each child class
> - **Constructor Overloading** — `Customer` has 3 constructors with different parameters
> - **Static Members** — `Reservation.counter` for auto-incrementing IDs
> - **Exception Handling** — try-catch-finally with multiple catch blocks"

---

#### Q5: "How does the login work? Can users log in with email or username?"

**Answer:**
> *"Yes! The login form has a single field labeled 'Email / Username'. The JavaScript sends it as `{email: value}`. The server first tries to get the 'email' field, then falls back to 'username'. It searches the users list where `u[0] == login OR u[4] == login` — matching either username or email. If the account doesn't exist, the error says 'Email-ka ama Username-ka lama diiwaan gelin wali'. If it exists but password is wrong, it says 'Password-ka waa khalad!'"*

---

#### Q6: "How do you prevent two people booking the same room?"

**Answer:**
> *"We have a **global room pool** in `room_assignments.txt`. When someone books room 5, we write `5|username` to that file. Before saving any new booking, `BookHandler` checks if the room number is already in `room_assignments.txt`. Additionally, on the frontend, we do a synchronous `GET /api/rooms/available` right before submitting — just in case someone booked it between page load and clicking confirm."*

---

#### Q7: "What happens when a user deletes their account?"

**Answer:**
> *"Everything is cleaned up in one operation:*
> 1. *User record removed from `users.txt`*
> 2. *All their bookings removed from `reservations.txt`*
> 3. *All their room assignments freed from `room_assignments.txt`*
> *But we protect admin accounts — the server refuses to delete any user where `isAdmin == true`, and the admin page hides the delete button for the logged-in admin's own row."*

---

#### Q8: "How did you handle the 2007-era table layout constraint?"

**Answer:**
> *"The assignment required no CSS Grid or Flexbox. So we used HTML `<table>` for the main page layout, with CSS for styling. It actually works well — tables are universally supported, print-friendly, and naturally responsive in their own way. The design is inspired by Arch Linux documentation pages — clean, minimal, and functional."*

---

#### Q9: "What would you improve if you had more time?"

**Answer:**
> *"Three things:*
> 1. *Add a **real database** (SQLite would be perfect for a lightweight upgrade).*
> 2. *Implement **password hashing** (currently passwords are stored in plain text).*
> 3. *Build a **REST API** with proper JSON response formatting and status codes."*

---

#### Q10: "How many hours did this project take?"

**Answer:**
> *"The complete project — planning, coding, testing, and documentation — took approximately [X] hours over [Y] weeks. The server alone is 1,040 lines of Java with 19 handler classes. The frontend has 10 HTML pages totaling over 1,000 lines of HTML and JavaScript."*

---

# 🧠 BONUS: QUICK REFERENCE CARDS

---

### 📇 Data File Field Positions (Index Table)

```text
users.txt (pipe | delimited):
  u[0] = username        u[1] = password
  u[2] = full name       u[3] = phone
  u[4] = email           u[5] = isAdmin (true/false)

reservations.txt (NEW 10-field format):
  [0] id    [1] username  [2] name
  [3] roomType  [4] roomNumber  [5] checkin
  [6] checkout  [7] guests     [8] phone  [9] email

reservations.txt (OLD 9-field format — still supported):
  [0] id    [1] username  [2] name
  [3] roomType  [4] checkin  [5] checkout
  [6] guests  [7] phone  [8] email

rooms.txt:
  [0] name   [1] price   [2] amenities

room_assignments.txt:
  [0] roomNumber   [1] username
```

---

### 🚀 Quick Commands

```bash
# Compile everything
cd ~/HotelReservation/java
javac *.java

# Run the HTTP server
java HotelServer

# Run the console version
java HotelReservationSystem

# Open in browser
firefox http://localhost:8080

# Default admin login
# Username: admin
# Password: 1234

# Push to GitHub
git add -A && git commit -m "message" && git push
```

---

### 🔗 All API Endpoints Quick Reference

| Method | Route | Purpose |
|--------|-------|---------|
| POST | `/api/login` | Authenticate user |
| POST | `/api/signup` | Register new user |
| POST | `/api/check` | Real-time duplicate check |
| GET | `/api/rooms/available` | Available room numbers |
| POST | `/api/book` | Create booking |
| GET | `/api/bookings` | List bookings |
| GET | `/api/booking?id=X` | Single booking |
| GET | `/api/users` | All users (admin) |
| POST | `/api/users/update` | Edit user (admin) |
| POST | `/api/users/delete` | Delete user |
| POST | `/api/user/update` | Edit own profile |
| GET | `/api/rooms` | List room types |
| POST | `/api/rooms/add` | Add room type |
| POST | `/api/rooms/delete` | Delete room type |
| POST | `/api/bookings/delete` | Cancel booking |
| GET | `/api/stats` | Dashboard stats |
| POST | `/api/contact` | Contact form |
| POST | `/api/availability` | Date availability check |
| GET | `/` | Static files |

---

# 🏁 CLOSING STATEMENT

---

### 🎯 Final Words

> *"In conclusion, the Kismaayo Hotel Reservation System demonstrates:*
>
> ✅ *A fully functional hotel booking website — from registration to printable receipt*
> ☕ *Pure Java backend — 1,040 lines, 19 API handlers, zero external dependencies*
> 📁 *File-based storage — simple, inspectable, portable*
> 🧬 *All OOP concepts — encapsulation, inheritance, polymorphism, and more*
> 🌍 *Somali language interface — built for our local community*
>
> *Thank you for listening. I'm happy to take any questions."*

---

```text
╔══════════════════════════════════════════════════════════════════╗
║              END OF PRESENTATION MAP                            ║
║                                                                 ║
║  "Kismaayo Hotel — Where Code Meets Comfort 🏨"                ║
║                                                                 ║
║  University of Kismaayo · 30th May 2026 · Semester 6           ║
║  Java Programming II · Group 3                                  ║
╚══════════════════════════════════════════════════════════════════╝
```

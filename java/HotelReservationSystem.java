import java.util.*;
import java.io.*;

/**
 * HotelReservationSystem.java - FULL VERSION
 * ============================================
 * Kani waa barnaamijka hotel reservation system oo buuxa.
 *
 * Waxaa ku jira:
 * - Login/Signup system (users.txt)
 * - Admin panel (add rooms, view all)
 * - User dashboard (my bookings only)
 * - Dhammaan concepts-kii aan ku baranay:
 *   Variables, if/else, switch, for/while/do-while,
 *   break, continue, arrays, ArrayList, Stack, LinkedList,
 *   String methods, Scanner, inheritance, polymorphism,
 *   constructor overloading, static, encapsulation,
 *   try-catch (multiple + nested), throw, finally,
 *   File I/O (write, read, delete).
 *
 * SIDE U ISTICMAALO:
 *   javac *.java
 *   java HotelReservationSystem
 */

public class HotelReservationSystem {

    /* Collections */
    private static ArrayList<Room> rooms = new ArrayList<>();
    private static ArrayList<Reservation> reservations = new ArrayList<>();
    private static ArrayList<User> users = new ArrayList<>();
    private static Stack<String> recentBookings = new Stack<>();
    private static LinkedList<String> waitingList = new LinkedList<>();
    private static Scanner sc = new Scanner(System.in);

    /* User-ka hadda soo galay (logged in) */
    private static User currentUser = null;

    public static void main(String[] args) {

        /* Setup bilowga */
        setupRooms();
        users = User.loadUsers();
        Reservation.loadFromFile();

        System.out.println("\n==============================");
        System.out.println("  KISMAAYO HOTEL RESERVATION");
        System.out.println("==============================\n");

        /* Login/Signup marka hore */
        if (!loginMenu()) {
            System.out.println("Bye!\n");
            return;
        }

        /* Menu-ga ku xiran hadba cidda soo gashay */
        if (currentUser.isAdmin()) {
            adminMenu();
        } else {
            userMenu();
        }

        sc.close();
    }

    /* ==========================================================
       LOGIN / SIGNUP
       ========================================================== */
    private static boolean loginMenu() {
        while (true) {
            System.out.println("--- WELCOME ---");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit");
            System.out.print("Dooro: ");

            String c = sc.nextLine();
            switch (c) {
                case "1":
                    if (doLogin()) return true;
                    break;
                case "2":
                    doSignup();
                    break;
                case "3":
                    return false;
                default:
                    System.out.println("Khalad!\n");
            }
        }
    }

    /* Login */
    private static boolean doLogin() {
        System.out.print("Username: ");
        String u = sc.nextLine();
        System.out.print("Password: ");
        String p = sc.nextLine();

        User found = User.findUser(users, u);
        if (found != null && found.checkPassword(p)) {
            currentUser = found;
            System.out.println("\nWelcome " + currentUser.getFullName() + "!\n");
            return true;
        }
        System.out.println("Username ama password khalad!\n");
        return false;
    }

    /* Signup */
    private static void doSignup() {
        System.out.println("\n--- SIGN UP ---");
        System.out.print("Username: ");
        String u = sc.nextLine();

        /* Hubi inuu jiro */
        if (User.findUser(users, u) != null) {
            System.out.println("Username-ka waa la isticmaalay!\n");
            return;
        }

        System.out.print("Password: ");
        String p = sc.nextLine();
        System.out.print("Full Name: ");
        String n = sc.nextLine();
        System.out.print("Phone: ");
        String ph = sc.nextLine();
        System.out.print("Email: ");
        String e = sc.nextLine();

        User newUser = new User(u, p, n, ph, e, false);
        users.add(newUser);
        User.saveUsers(users);
        System.out.println("Account created! Now login.\n");
    }

    /* ==========================================================
       USER MENU (regular customer)
       ========================================================== */
    private static void userMenu() {
        while (true) {
            try {
                System.out.println("--- MENU (User: " + currentUser.getUsername() + ") ---");
                System.out.println("1. View Rooms");
                System.out.println("2. Search Room");
                System.out.println("3. Book a Room");
                System.out.println("4. My Bookings");
                System.out.println("5. Cancel Booking");
                System.out.println("6. Hotel Info");
                System.out.println("7. Logout");
                System.out.print("Dooro: ");

                int ch = Integer.parseInt(sc.nextLine());
                switch (ch) {
                    case 1: viewRooms(); break;
                    case 2: searchRoom(); break;
                    case 3: bookRoom(); break;
                    case 4: myBookings(); break;
                    case 5: cancelBooking(); break;
                    case 6: hotelInfo(); break;
                    case 7:
                        System.out.println("Logging out...\n");
                        return;
                    default:
                        System.out.println("1-7 kaliya!\n");
                        continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Number gali!\n");
            }
        }
    }

    /* ==========================================================
       ADMIN MENU
       ========================================================== */
    private static void adminMenu() {
        while (true) {
            try {
                System.out.println("--- ADMIN PANEL ---");
                System.out.println("1. View All Rooms");
                System.out.println("2. Add New Room");
                System.out.println("3. Remove a Room");
                System.out.println("4. View All Bookings");
                System.out.println("5. View All Users");
                System.out.println("6. Hotel Info");
                System.out.println("7. Logout");
                System.out.print("Dooro: ");

                int ch = Integer.parseInt(sc.nextLine());
                switch (ch) {
                    case 1: viewRooms(); break;
                    case 2: addRoom(); break;
                    case 3: removeRoom(); break;
                    case 4: allBookings(); break;
                    case 5: allUsers(); break;
                    case 6: hotelInfo(); break;
                    case 7:
                        System.out.println("Logging out...\n");
                        return;
                    default:
                        System.out.println("1-7 kaliya!\n");
                        continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Number gali!\n");
            }
        }
    }

    /* ==========================================================
       ADMIN: Add Room
       ========================================================== */
    private static void addRoom() {
        System.out.println("\n--- ADD ROOM ---");
        System.out.print("Room Number: ");
        String num = sc.nextLine();
        System.out.print("Type (Standard/Deluxe/Suite): ");
        String type = sc.nextLine();
        System.out.print("Price per night: ");
        double price = Double.parseDouble(sc.nextLine());
        System.out.print("Capacity: ");
        int cap = Integer.parseInt(sc.nextLine());

        Room newRoom = null;
        if (type.equalsIgnoreCase("Standard")) {
            newRoom = new StandardRoom(num);
        } else if (type.equalsIgnoreCase("Deluxe")) {
            newRoom = new DeluxeRoom(num);
        } else if (type.equalsIgnoreCase("Suite")) {
            newRoom = new SuiteRoom(num);
        } else {
            System.out.println("Type khalad! Standard/Deluxe/Suite.\n");
            return;
        }

        rooms.add(newRoom);
        System.out.println("Room " + num + " added!\n");
    }

    /* ==========================================================
       ADMIN: Remove Room
       ========================================================== */
    private static void removeRoom() {
        System.out.println("\n--- REMOVE ROOM ---");
        System.out.print("Room Number: ");
        String num = sc.nextLine();

        /* Array loop + break */
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getRoomNumber().equals(num)) {
                if (!rooms.get(i).isAvailable()) {
                    System.out.println("Qolkan waa la isticmaalay, ma saari karo!\n");
                    return;
                }
                rooms.remove(i);
                System.out.println("Room " + num + " removed!\n");
                return;
            }
        }
        System.out.println("Room " + num + " lama helin.\n");
    }

    /* ==========================================================
       ADMIN: All Bookings
       ========================================================== */
    private static void allBookings() {
        if (reservations.isEmpty()) {
            System.out.println("\nWax booking ah ma jiraan.\n");
            return;
        }
        System.out.println("\n--- ALL BOOKINGS (" + reservations.size() + ") ---");
        for (Reservation r : reservations) {
            r.displayInfo();
        }
        System.out.println();
    }

    /* ==========================================================
       ADMIN: All Users
       ========================================================== */
    private static void allUsers() {
        System.out.println("\n--- ALL USERS (" + users.size() + ") ---");
        for (User u : users) {
            System.out.println("  " + u.getUsername() + " | " + u.getFullName()
                + " | Admin: " + u.isAdmin());
        }
        System.out.println();
    }

    /* ==========================================================
       VIEW ROOMS
       ========================================================== */
    private static void viewRooms() {
        System.out.println("\n--- QOLALKA ---");
        for (Room r : rooms) {
            r.displayInfo();
        }
        System.out.println();
    }

    /* ==========================================================
       SEARCH ROOM (do-while)
       ========================================================== */
    private static void searchRoom() {
        String type;
        do {
            System.out.print("Search (Standard/Deluxe/Suite or exit): ");
            type = sc.nextLine().trim();
            if (type.equalsIgnoreCase("exit")) return;

            if (type.equalsIgnoreCase("Standard") ||
                type.equalsIgnoreCase("Deluxe") ||
                type.equalsIgnoreCase("Suite")) {

                System.out.println("\n--- " + type + " ---");
                for (Room r : rooms) {
                    if (r.getRoomType().equalsIgnoreCase(type)) {
                        r.displayInfo();
                    }
                }
                break;
            } else {
                System.out.println("Khalad! Try again.");
            }
        } while (true);
        System.out.println();
    }

    /* ==========================================================
       BOOK ROOM (try-catch + multiple catch + throw + finally)
       ========================================================== */
    private static void bookRoom() {
        System.out.println("\n--- BOOKING ---");

        ArrayList<Room> available = new ArrayList<>();
        for (Room r : rooms) {
            if (r.isAvailable()) available.add(r);
        }

        if (available.isEmpty()) {
            System.out.println("Qolal diyaar ah ma jiraan!");
            waitingList.add(currentUser.getUsername());
            System.out.println("Waitlist size: " + waitingList.size() + "\n");
            return;
        }

        for (int i = 0; i < available.size(); i++) {
            Room r = available.get(i);
            System.out.println("[" + (i+1) + "] Room " + r.getRoomNumber()
                + " - " + r.getRoomType() + " ($" + r.getPricePerNight() + ")");
        }

        try {
            System.out.print("Dooro [1-" + available.size() + "]: ");
            String in = sc.nextLine();
            if (in.trim().length() == 0) throw new IllegalArgumentException("Bannaan!");

            int ch = Integer.parseInt(in);
            if (ch < 1 || ch > available.size()) throw new IndexOutOfBoundsException("Khalad!");

            Room sel = available.get(ch - 1);

            System.out.print("Check-in (YYYY-MM-DD): ");
            String cin = sc.nextLine();
            System.out.print("Check-out (YYYY-MM-DD): ");
            String cout = sc.nextLine();
            System.out.print("Guests: ");
            int g = Integer.parseInt(sc.nextLine());

            /* Constructor overloading - Customer */
            Customer cust = new Customer(currentUser.getFullName(),
                                         currentUser.getPhone(),
                                         currentUser.getEmail());

            Reservation res = new Reservation(currentUser.getUsername(), cust, sel, cin, cout, g);
            reservations.add(res);
            res.saveToFile();

            recentBookings.push("RES" + res.getReservationId());
            System.out.println("\nBOOKED! ID: #" + res.getReservationId());
            res.displayInfo();

        } catch (NumberFormatException e) {
            System.out.println("Number khalad!\n");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        } finally {
            System.out.println("(finally done)\n");
        }
    }

    /* ==========================================================
       MY BOOKINGS (user-specific)
       ========================================================== */
    private static void myBookings() {
        boolean found = false;
        System.out.println("\n--- MY BOOKINGS ---");
        for (Reservation r : reservations) {
            /* Kaliya kuwa user-kan */
            if (r.getUserId().equals(currentUser.getUsername()) && r.getStatus() == 'A') {
                r.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("Wax booking ah ma lihid.");
        System.out.println();
    }

    /* ==========================================================
       CANCEL BOOKING (nested try-catch)
       ========================================================== */
    private static void cancelBooking() {
        System.out.println("\n--- CANCEL ---");
        try {
            System.out.print("Reservation ID: ");
            String input = sc.nextLine();

            try {
                int id = Integer.parseInt(input);
                Reservation found = null;
                for (Reservation r : reservations) {
                    if (r.getReservationId() == id) { found = r; break; }
                }
                if (found == null) {
                    System.out.println("Lama helin.\n");
                    return;
                }
                /* Hubi inuu yahay kii user-ka */
                if (!found.getUserId().equals(currentUser.getUsername()) && !currentUser.isAdmin()) {
                    System.out.println("Kama saari karto booking-ka dadka kale!\n");
                    return;
                }

                if (found.getStatus() == 'C') {
                    System.out.println("Horeyba waa la joojiyay.\n");
                } else {
                    found.setStatus('C');
                    found.getRoom().setAvailable(true);

                    File f = new File("../data/reservations.txt");
                    f.delete();

                    System.out.println("Cancelled!\n");
                }
            } catch (NumberFormatException e) {
                System.out.println("Number gali!\n");
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage() + "\n");
        } finally {
            System.out.println("(finally)\n");
        }
    }

    /* ==========================================================
       HOTEL INFO
       ========================================================== */
    private static void hotelInfo() {
        System.out.println("\n--- KISMAAYO HOTEL ---");
        System.out.println("Location: Kismaayo, Somalia");
        System.out.println("Phone: +252 61 234 5678");
        System.out.println("Email: info@kismaayohotel.com");
        System.out.println("Rooms: " + rooms.size());
        System.out.println("Staff: 24/7 Service");
        System.out.println();

        int available = 0;
        for (Room r : rooms) {
            if (r.isAvailable()) available++;
        }
        System.out.println("Available Rooms: " + available + "/" + rooms.size());

        /* Stack + LinkedList */
        System.out.println("Recent Bookings: " + recentBookings.size());
        System.out.println("Waitlist: " + waitingList.size() + "\n");
    }

    /* ==========================================================
       SETUP ROOMS (inheritance)
       ========================================================== */
    private static void setupRooms() {
        rooms.add(new StandardRoom("101"));
        rooms.add(new StandardRoom("102"));
        rooms.add(new StandardRoom("103"));
        rooms.add(new DeluxeRoom("201"));
        rooms.add(new DeluxeRoom("202"));
        rooms.add(new DeluxeRoom("203"));
        rooms.add(new SuiteRoom("301"));
        rooms.add(new SuiteRoom("302"));
        rooms.add(new SuiteRoom("303"));
    }
}

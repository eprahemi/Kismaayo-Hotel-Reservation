import java.io.*;

/**
 * Reservation.java
 * Waxay isku xidhaystaa User iyo Room.
 * Static counter, File I/O, try-catch.
 */

public class Reservation {

    private static int counter = 100;

    private int reservationId;
    private String userId;       // cidda booking gareysay
    private Customer customer;
    private Room room;
    private String checkInDate;
    private String checkOutDate;
    private int guests;
    private char status; // 'A' = Active, 'C' = Cancelled

    /* Constructor */
    public Reservation(String userId, Customer customer, Room room,
                       String checkIn, String checkOut, int guests) {
        this.reservationId = counter++;
        this.userId = userId;
        this.customer = customer;
        this.room = room;
        this.checkInDate = checkIn;
        this.checkOutDate = checkOut;
        this.guests = guests;
        this.status = 'A';
        room.setAvailable(false);
    }

    /* Getters */
    public int getReservationId() { return reservationId; }
    public String getUserId() { return userId; }
    public Customer getCustomer() { return customer; }
    public Room getRoom() { return room; }
    public String getCheckInDate() { return checkInDate; }
    public String getCheckOutDate() { return checkOutDate; }
    public int getGuests() { return guests; }
    public char getStatus() { return status; }
    public void setStatus(char status) { this.status = status; }

    public double getTotalPrice() {
        return room.getPricePerNight() * guests;
    }

    public void displayInfo() {
        System.out.println("\n=== RESERVATION #" + reservationId + " ===");
        System.out.println("User: " + userId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Room: " + room.getRoomNumber() + " (" + room.getRoomType() + ")");
        System.out.println("Dates: " + checkInDate + " to " + checkOutDate);
        System.out.println("Guests: " + guests);
        System.out.println("Total: $" + getTotalPrice());
        System.out.println("Status: " + (status == 'A' ? "Active" : "Cancelled"));
    }

    /* FILE I/O: Kaydi data/ */
    public void saveToFile() {
        try {
            File dir = new File("../data");
            if (!dir.exists()) dir.mkdir();
            FileWriter writer = new FileWriter("../data/reservations.txt", true);
            writer.write(reservationId + "," + userId + "," + customer.getName() + ","
                        + room.getRoomNumber() + "," + room.getRoomType() + ","
                        + checkInDate + "," + checkOutDate + "," + guests + "," + status + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving: " + e.getMessage());
        }
    }

    /* FILE I/O: Akhriso data/ */
    public static void loadFromFile() {
        try {
            File file = new File("../data/reservations.txt");
            if (!file.exists()) return;
            BufferedReader reader = new BufferedReader(new FileReader(file));
            int count = 0;
            while (reader.readLine() != null) count++;
            reader.close();
            if (count > 0) System.out.println("Loaded " + count + " past reservations.");
        } catch (IOException e) {
            System.out.println("Error reading: " + e.getMessage());
        }
    }
}

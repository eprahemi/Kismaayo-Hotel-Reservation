/**
 * Room.java
 * Kani waa fasal asaasi ah (base class).
 * Qolalka hotel-ka waxay ka imanayaan kan.
 *
 * Waxaa ku baranaysaa:
 * - Private variables (encapsulation)
 * - Constructor (dhisaha)
 * - Getters iyo Setters
 */

public class Room {

    /* Halkan waxaan ku haynaa macluumaadka qolka */
    private String roomNumber;
    private String roomType;
    private double pricePerNight;
    private boolean isAvailable;
    private int capacity;

    /* Constructor - waxan abuurnaa qol cusub */
    public Room(String roomNumber, String roomType, double pricePerNight, int capacity) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
        this.capacity = capacity;
        this.isAvailable = true; // marka hore qolku waa diyaar
    }

    /* Getter-yada - waxay soo celiyaan qiimaha */
    public String getRoomNumber() { return roomNumber; }
    public String getRoomType() { return roomType; }
    public double getPricePerNight() { return pricePerNight; }
    public boolean isAvailable() { return isAvailable; }
    public int getCapacity() { return capacity; }

    /* Setter - waxay bedeshaa xaaladda qolka */
    public void setAvailable(boolean available) { isAvailable = available; }

    /* Tus macluumaadka qolka */
    public void displayInfo() {
        System.out.println("Room " + roomNumber + " | " + roomType +
                           " | $" + pricePerNight + "/night | " +
                           (isAvailable ? "Available" : "Booked"));
    }
}

/**
 * DeluxeRoom.java
 * Kan wuxuu ka qaalisan yahay Standard.
 * Wuxuu leeyahay sea view iyo mini bar.
 * Dhaxal (inheritance), override (polymorphism).
 */

public class DeluxeRoom extends Room {

    private String bedType = "King Bed";
    private boolean hasSeaView = true;

    public DeluxeRoom(String roomNumber) {
        super(roomNumber, "Deluxe", 80.0, 3);
    }

    @Override
    public void displayInfo() {
        System.out.print("[DELUXE] ");
        super.displayInfo();
        System.out.println("  Bed: " + bedType + " | Sea View: " +
                           (hasSeaView ? "Yes" : "No"));
    }
}

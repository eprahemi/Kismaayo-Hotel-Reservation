/**
 * SuiteRoom.java
 * Kani waa qolka ugu qaalisan.
 * Wuxuu leeyahay jiko, balcony, iyo qol fadhi.
 * Inheritance + polymorphism.
 */

public class SuiteRoom extends Room {

    private String bedType = "2 King Beds";

    public SuiteRoom(String roomNumber) {
        super(roomNumber, "Suite", 120.0, 5);
    }

    @Override
    public void displayInfo() {
        System.out.print("[SUITE] ");
        super.displayInfo();
        System.out.println("  Beds: " + bedType + " | Kitchen: Yes | Balcony: Yes");
    }
}

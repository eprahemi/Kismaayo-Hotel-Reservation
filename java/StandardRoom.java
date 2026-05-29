/**
 * StandardRoom.java
 * Qolkan waa kan ugu jaban hotelka.
 * Wuxuu dhaxlayaa (extends) Room - Inheritance.
 * Waxaan bedelaynay displayInfo() - polymorphism.
 */

public class StandardRoom extends Room {

    private String bedType = "Double Bed";

    /* super() waxay u dirtaa macluumaadka Room-ka */
    public StandardRoom(String roomNumber) {
        super(roomNumber, "Standard", 50.0, 2);
    }

    /* Halkan waxaan ku daraynaa macluumaad dheeri ah */
    @Override
    public void displayInfo() {
        System.out.print("[STANDARD] ");
        super.displayInfo();
        System.out.println("  Bed: " + bedType);
    }
}

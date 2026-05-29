/**
 * Customer.java
 * Macmiilka hotelka.
 * Waxaan ku baranaynaa constructor overloading
 * (dhisayaal badan oo magac wadaag ah).
 * Iyo string methods like toUpperCase(), contains().
 */

public class Customer {

    private String name;
    private String phone;
    private String email;

    /* Constructor 1 - dhammaan xogta */
    public Customer(String name, String phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    /* Constructor 2 - magac oo kaliya (overloading) */
    public Customer(String name) {
        this.name = name;
        this.phone = "Unknown";
        this.email = "Unknown";
    }

    /* Constructor 3 - magac + phone (overloading) */
    public Customer(String name, String phone) {
        this.name = name;
        this.phone = phone;
        this.email = "Unknown";
    }

    /* Getters */
    public String getName() { return name; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }

    /* String method: magaca weyn (uppercase) */
    public String getFormattedName() {
        return name.toUpperCase();
    }

    /* String method: hubi in email-ku sax yahay */
    public boolean isValidEmail() {
        return email.contains("@") && email.contains(".");
    }

    /* Soo saar xarfaha hore ee magaca (initials) */
    public String getInitials() {
        if (name.length() == 0) return "??";
        String result = "" + name.charAt(0);
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == ' ' && i + 1 < name.length()) {
                result += name.charAt(i + 1);
            }
        }
        return result.toUpperCase();
    }

    public void displayInfo() {
        System.out.println("Name: " + name + " | Phone: " + phone + " | Email: " + email);
    }
}

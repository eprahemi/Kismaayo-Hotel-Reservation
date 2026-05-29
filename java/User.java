import java.io.*;
import java.util.*;

/**
 * User.java
 * Isticmaalayaasha hotel-ka (login/signup).
 * Waxaa ku baranaysaa:
 * - File I/O (save/load users)
 * - try-catch
 * - String methods
 * - ArrayList
 */

public class User {
    private String username;
    private String password;
    private String fullName;
    private String phone;
    private String email;
    private boolean isAdmin;

    /* Constructor */
    public User(String username, String password, String fullName, String phone, String email, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    /* Getters */
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getFullName() { return fullName; }
    public String getPhone() { return phone; }
    public String getEmail() { return email; }
    public boolean isAdmin() { return isAdmin; }

    /* Hubi in password-ka sax yahay */
    public boolean checkPassword(String pwd) {
        return this.password.equals(pwd);
    }

    /* Tus macluumaadka user-ka */
    public void displayInfo() {
        System.out.println("User: " + username + " | Name: " + fullName + " | Admin: " + isAdmin);
    }

    /* ===== FILE I/O: Kaydi users-ka faylka data/ ===== */
    public static void saveUsers(ArrayList<User> users) {
        try {
            File dir = new File("../data");
            if (!dir.exists()) dir.mkdir();
            FileWriter writer = new FileWriter("../data/users.txt");
            for (User u : users) {
                writer.write(u.username + "," + u.password + "," + u.fullName + ","
                            + u.phone + "," + u.email + "," + u.isAdmin + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }

    /* ===== FILE I/O: Akhriso users-ka faylka data/ ===== */
    public static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        try {
            File file = new File("../data/users.txt");
            if (!file.exists()) {
                /* Haddii uusan jirin fayl, abuur admin default ah */
                users.add(new User("admin", "1234", "Admin", "000", "admin@hotel.com", true));
                saveUsers(users);
                return users;
            }
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    users.add(new User(parts[0], parts[1], parts[2], parts[3], parts[4],
                              Boolean.parseBoolean(parts[5])));
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    /* Hel user username-kiisa */
    public static User findUser(ArrayList<User> users, String username) {
        for (User u : users) {
            if (u.getUsername().equals(username)) return u;
        }
        return null;
    }
}

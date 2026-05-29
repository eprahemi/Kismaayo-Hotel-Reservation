/*
 ==============================================
  Kismaayo Hotel - Java HTTP Server
  Wuxuu u adeegaa HTML pages oo wuxuu
  xafidaa xogta data/*.txt
  ==============================================
 */

import com.sun.net.httpserver.*;
import java.io.*;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.*;

public class HotelServer {

    static final int PORT = 8080;
    static final String DATA_DIR = "../data";
    static final String WWW_DIR = "..";

    public static void main(String[] args) throws Exception {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) dataDir.mkdirs();

        // users.txt
        File usersFile = new File(DATA_DIR + "/users.txt");
        if (!usersFile.exists()) {
            try (FileWriter fw = new FileWriter(usersFile)) {
                fw.write("admin|admin123|Admin|000|admin@hotel.com|true\n");
            }
        }

        // reservations.txt
        File resFile = new File(DATA_DIR + "/reservations.txt");
        if (!resFile.exists()) resFile.createNewFile();

        // rooms.txt with amenities
        File roomsFile = new File(DATA_DIR + "/rooms.txt");
        if (!roomsFile.exists()) {
            try (FileWriter fw = new FileWriter(roomsFile)) {
                fw.write("Standard|50|AC,WiFi,TV,Free Parking\n");
                fw.write("Deluxe|80|AC,WiFi,TV,Mini Bar,Breakfast\n");
                fw.write("Suite|120|AC,WiFi,TV,Jacuzzi,Breakfast,Room Service 24/7\n");
            }
        }

        // contacts.txt
        File contactFile = new File(DATA_DIR + "/contacts.txt");
        if (!contactFile.exists()) contactFile.createNewFile();

        HttpServer server = HttpServer.create(new java.net.InetSocketAddress(PORT), 0);

        // API endpoints
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

        server.createContext("/", new StaticFileHandler());

        server.setExecutor(null);
        System.out.println("========================================");
        System.out.println("  Kismaayo Hotel Server running on");
        System.out.println("  http://localhost:" + PORT);
        System.out.println("========================================");
        server.start();
    }

    // ============================================
    //  LOGIN
    // ============================================
    static class LoginHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String username = getJsonValue(body, "username");
            String password = getJsonValue(body, "password");

            List<String[]> users = readUsers();
            for (String[] u : users) {
                if (u[0].equals(username) && u[1].equals(password)) {
                    String jsonUser = String.format(
                        "{\"username\":\"%s\",\"name\":\"%s\",\"phone\":\"%s\",\"email\":\"%s\",\"isAdmin\":%s}",
                        u[0], u[2], u[3], u[4], u[5]
                    );
                    sendJson(ex, 200, "{\"success\":true,\"user\":" + jsonUser + "}");
                    return;
                }
            }
            sendJson(ex, 200, "{\"success\":false,\"error\":\"Username ama password khalad!\"}");
        }
    }

    // ============================================
    //  SIGNUP
    // ============================================
    static class SignupHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String username = getJsonValue(body, "username");
            String password = getJsonValue(body, "password");
            String name = getJsonValue(body, "name");
            String phone = getJsonValue(body, "phone");
            String email = getJsonValue(body, "email");

            if (username.isEmpty() || password.isEmpty()) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"Username iyo password waa inay buuxsameyn!\"}"); return;
            }

            for (String[] u : readUsers()) {
                if (u[0].equals(username)) {
                    sendJson(ex, 200, "{\"success\":false,\"error\":\"Username-ka waa la isticmaalay!\"}"); return;
                }
            }

            try (FileWriter fw = new FileWriter(DATA_DIR + "/users.txt", true)) {
                fw.write(username + "|" + password + "|" + name + "|" + phone + "|" + email + "|false\n");
            }
            sendJson(ex, 200, "{\"success\":true}");
        }
    }

    // ============================================
    //  BOOK
    // ============================================
    static class BookHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String username = getJsonValue(body, "username");
            String name = getJsonValue(body, "name");
            String roomType = getJsonValue(body, "roomType");
            String checkin = getJsonValue(body, "checkin");
            String checkout = getJsonValue(body, "checkout");
            String guests = getJsonValue(body, "guests");
            if (guests.isEmpty()) guests = "1";
            String phone = getJsonValue(body, "phone");
            String email = getJsonValue(body, "email");

            if (username.isEmpty() || roomType.isEmpty() || checkin.isEmpty() || checkout.isEmpty()) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"Fadlan buuxi dhammaan goobaha!\"}"); return;
            }

            // Hel ID-ga xiga
            int nextId = 1;
            File f = new File(DATA_DIR + "/reservations.txt");
            if (f.exists()) {
                for (String line : Files.readAllLines(f.toPath())) {
                    if (!line.trim().isEmpty()) {
                        try { nextId = Integer.parseInt(line.split("\\|")[0]) + 1; } catch (Exception e) {}
                    }
                }
            }

            try (FileWriter fw = new FileWriter(DATA_DIR + "/reservations.txt", true)) {
                fw.write(nextId + "|" + username + "|" + name + "|" + roomType + "|"
                         + checkin + "|" + checkout + "|" + guests + "|" + phone + "|" + email + "\n");
            }

            String bookingJson = String.format(
                "{\"id\":\"%d\",\"username\":\"%s\",\"name\":\"%s\",\"roomType\":\"%s\",\"checkin\":\"%s\",\"checkout\":\"%s\",\"guests\":\"%s\",\"phone\":\"%s\",\"email\":\"%s\"}",
                nextId, username, name, roomType, checkin, checkout, guests, phone, email
            );
            sendJson(ex, 200, "{\"success\":true,\"booking\":" + bookingJson + "}");
        }
    }

    // ============================================
    //  BOOKINGS (list)
    // ============================================
    static class BookingsHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"GET".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String query = ex.getRequestURI().getQuery();
            String filterUser = null;
            String filterRoom = null;
            if (query != null) {
                for (String param : query.split("&")) {
                    String[] kv = param.split("=", 2);
                    if (kv.length == 2) {
                        if (kv[0].equals("username")) filterUser = URLDecoder.decode(kv[1], "UTF-8");
                        if (kv[0].equals("roomType")) filterRoom = URLDecoder.decode(kv[1], "UTF-8");
                    }
                }
            }

            List<String[]> bookings = new ArrayList<>();
            File f = new File(DATA_DIR + "/reservations.txt");
            if (f.exists()) {
                for (String line : Files.readAllLines(f.toPath())) {
                    if (!line.trim().isEmpty()) {
                        String[] parts = line.split("\\|");
                        if (parts.length >= 9) {
                            boolean match = true;
                            if (filterUser != null && !parts[1].equals(filterUser)) match = false;
                            if (filterRoom != null && !parts[3].equalsIgnoreCase(filterRoom)) match = false;
                            if (match) bookings.add(parts);
                        }
                    }
                }
            }

            StringBuilder json = new StringBuilder("{\"success\":true,\"bookings\":[");
            for (int i = 0; i < bookings.size(); i++) {
                String[] b = bookings.get(i);
                if (i > 0) json.append(",");
                json.append(String.format(
                    "{\"id\":\"%s\",\"username\":\"%s\",\"name\":\"%s\",\"roomType\":\"%s\",\"checkin\":\"%s\",\"checkout\":\"%s\",\"guests\":\"%s\",\"phone\":\"%s\",\"email\":\"%s\"}",
                    b[0], b[1], b[2], b[3], b[4], b[5], b[6], b[7], b[8]
                ));
            }
            json.append("]}");
            sendJson(ex, 200, json.toString());
        }
    }

    // ============================================
    //  BOOKING BY ID (single)
    // ============================================
    static class BookingByIdHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"GET".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String query = ex.getRequestURI().getQuery();
            String id = "";
            if (query != null && query.startsWith("id=")) {
                id = URLDecoder.decode(query.substring(3), "UTF-8");
            }

            if (id.isEmpty()) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"ID-ga booking-ka waa inuu buuxsamay!\"}"); return;
            }

            File f = new File(DATA_DIR + "/reservations.txt");
            if (f.exists()) {
                for (String line : Files.readAllLines(f.toPath())) {
                    if (!line.trim().isEmpty()) {
                        String[] parts = line.split("\\|");
                        if (parts.length >= 9 && parts[0].equals(id)) {
                            String json = String.format(
                                "{\"success\":true,\"booking\":{\"id\":\"%s\",\"username\":\"%s\",\"name\":\"%s\",\"roomType\":\"%s\",\"checkin\":\"%s\",\"checkout\":\"%s\",\"guests\":\"%s\",\"phone\":\"%s\",\"email\":\"%s\"}}",
                                parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8]
                            );
                            sendJson(ex, 200, json);
                            return;
                        }
                    }
                }
            }
            sendJson(ex, 200, "{\"success\":false,\"error\":\"Booking-ka lama helin!\"}");
        }
    }

    // ============================================
    //  USERS (list all)
    // ============================================
    static class UsersHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"GET".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            List<String[]> users = readUsers();
            StringBuilder json = new StringBuilder("{\"success\":true,\"users\":[");
            for (int i = 0; i < users.size(); i++) {
                String[] u = users.get(i);
                if (i > 0) json.append(",");
                json.append(String.format(
                    "{\"username\":\"%s\",\"password\":\"%s\",\"name\":\"%s\",\"phone\":\"%s\",\"email\":\"%s\",\"isAdmin\":%s}",
                    u[0], u[1], u[2], u[3], u[4], u[5]
                ));
            }
            json.append("]}");
            sendJson(ex, 200, json.toString());
        }
    }

    // ============================================
    //  USER UPDATE (admin)
    // ============================================
    static class UserUpdateHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String username = getJsonValue(body, "username");
            String password = getJsonValue(body, "password");
            String name = getJsonValue(body, "name");
            String phone = getJsonValue(body, "phone");
            String email = getJsonValue(body, "email");

            if (username.isEmpty()) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"Username waa inuu buuxsamay!\"}"); return;
            }

            List<String[]> users = readUsers();
            boolean found = false;
            StringBuilder out = new StringBuilder();
            for (String[] u : users) {
                if (u[0].equals(username)) {
                    found = true;
                    out.append(username).append("|");
                    out.append(password.isEmpty() ? u[1] : password).append("|");
                    out.append(name.isEmpty() ? u[2] : name).append("|");
                    out.append(phone.isEmpty() ? u[3] : phone).append("|");
                    out.append(email.isEmpty() ? u[4] : email).append("|");
                    out.append(u[5]).append("\n");
                } else {
                    out.append(String.join("|", u)).append("\n");
                }
            }

            if (!found) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"User-ka lama helin!\"}"); return;
            }

            try (FileWriter fw = new FileWriter(DATA_DIR + "/users.txt")) {
                fw.write(out.toString());
            }
            sendJson(ex, 200, "{\"success\":true}");
        }
    }

    // ============================================
    //  USER DELETE (admin)
    // ============================================
    static class UserDeleteHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String username = getJsonValue(body, "username");

            List<String[]> users = readUsers();
            boolean found = false;
            StringBuilder out = new StringBuilder();
            for (String[] u : users) {
                if (u[0].equals(username)) {
                    found = true;
                } else {
                    out.append(String.join("|", u)).append("\n");
                }
            }

            if (!found) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"User-ka lama helin!\"}"); return;
            }

            try (FileWriter fw = new FileWriter(DATA_DIR + "/users.txt")) {
                fw.write(out.toString());
            }
            sendJson(ex, 200, "{\"success\":true}");
        }
    }

    // ============================================
    //  PROFILE UPDATE (user edits own profile)
    // ============================================
    static class ProfileUpdateHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String username = getJsonValue(body, "username");
            String name = getJsonValue(body, "name");
            String phone = getJsonValue(body, "phone");
            String email = getJsonValue(body, "email");

            if (username.isEmpty()) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"Username waa inuu buuxsamay!\"}"); return;
            }

            List<String[]> users = readUsers();
            boolean found = false;
            StringBuilder out = new StringBuilder();
            for (String[] u : users) {
                if (u[0].equals(username)) {
                    found = true;
                    out.append(username).append("|").append(u[1]).append("|");
                    out.append(name.isEmpty() ? u[2] : name).append("|");
                    out.append(phone.isEmpty() ? u[3] : phone).append("|");
                    out.append(email.isEmpty() ? u[4] : email).append("|");
                    out.append(u[5]).append("\n");
                } else {
                    out.append(String.join("|", u)).append("\n");
                }
            }

            if (!found) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"User-ka lama helin!\"}"); return;
            }

            try (FileWriter fw = new FileWriter(DATA_DIR + "/users.txt")) {
                fw.write(out.toString());
            }
            sendJson(ex, 200, "{\"success\":true}");
        }
    }

    // ============================================
    //  ROOMS (list)
    // ============================================
    static class RoomsHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"GET".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            List<String[]> rooms = readRooms();
            StringBuilder json = new StringBuilder("{\"success\":true,\"rooms\":[");
            for (int i = 0; i < rooms.size(); i++) {
                String[] r = rooms.get(i);
                if (i > 0) json.append(",");
                String amenities = r.length >= 3 ? r[2] : "";
                json.append(String.format(
                    "{\"name\":\"%s\",\"price\":\"%s\",\"amenities\":\"%s\"}",
                    r[0], r[1], amenities
                ));
            }
            json.append("]}");
            sendJson(ex, 200, json.toString());
        }
    }

    // ============================================
    //  ROOM ADD
    // ============================================
    static class RoomAddHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String name = getJsonValue(body, "name");
            String price = getJsonValue(body, "price");
            String amenities = getJsonValue(body, "amenities");

            if (name.isEmpty() || price.isEmpty()) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"Fadlan buuxi Room name iyo price!\"}"); return;
            }

            for (String[] r : readRooms()) {
                if (r[0].equalsIgnoreCase(name)) {
                    sendJson(ex, 200, "{\"success\":false,\"error\":\"Room-ka waa la isticmaalay!\"}"); return;
                }
            }

            try (FileWriter fw = new FileWriter(DATA_DIR + "/rooms.txt", true)) {
                fw.write(name + "|" + price + "|" + amenities + "\n");
            }
            sendJson(ex, 200, "{\"success\":true}");
        }
    }

    // ============================================
    //  ROOM DELETE
    // ============================================
    static class RoomDeleteHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String name = getJsonValue(body, "name");

            List<String[]> rooms = readRooms();
            boolean found = false;
            StringBuilder out = new StringBuilder();
            for (String[] r : rooms) {
                if (r[0].equalsIgnoreCase(name)) {
                    found = true;
                } else {
                    out.append(r[0]).append("|").append(r[1]);
                    if (r.length >= 3) out.append("|").append(r[2]);
                    out.append("\n");
                }
            }

            if (!found) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"Room-ka lama helin!\"}"); return;
            }

            try (FileWriter fw = new FileWriter(DATA_DIR + "/rooms.txt")) {
                fw.write(out.toString());
            }
            sendJson(ex, 200, "{\"success\":true}");
        }
    }

    // ============================================
    //  BOOKING DELETE
    // ============================================
    static class BookingDeleteHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String id = getJsonValue(body, "id");

            if (id.isEmpty()) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"ID-ga booking-ka waa inuu buuxsamay!\"}"); return;
            }

            File f = new File(DATA_DIR + "/reservations.txt");
            List<String> lines = Files.readAllLines(f.toPath());
            boolean found = false;
            StringBuilder out = new StringBuilder();
            for (String line : lines) {
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split("\\|");
                if (parts.length > 0 && parts[0].equals(id)) {
                    found = true;
                } else {
                    out.append(line).append("\n");
                }
            }

            if (!found) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"Booking-ka lama helin!\"}"); return;
            }

            try (FileWriter fw = new FileWriter(DATA_DIR + "/reservations.txt")) {
                fw.write(out.toString());
            }
            sendJson(ex, 200, "{\"success\":true}");
        }
    }

    // ============================================
    //  STATS
    // ============================================
    static class StatsHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"GET".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            int totalUsers = readUsers().size();
            int totalRooms = readRooms().size();
            int totalBookings = 0;
            File f = new File(DATA_DIR + "/reservations.txt");
            if (f.exists()) {
                for (String line : Files.readAllLines(f.toPath())) {
                    if (!line.trim().isEmpty()) totalBookings++;
                }
            }
            String json = String.format(
                "{\"success\":true,\"stats\":{\"totalUsers\":%d,\"totalRooms\":%d,\"totalBookings\":%d}}",
                totalUsers, totalRooms, totalBookings
            );
            sendJson(ex, 200, json);
        }
    }

    // ============================================
    //  CONTACT FORM
    // ============================================
    static class ContactHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String name = getJsonValue(body, "name");
            String email = getJsonValue(body, "email");
            String message = getJsonValue(body, "message");

            if (name.isEmpty() || message.isEmpty()) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"Fadlan buuxi magacaaga iyo farriinta!\"}"); return;
            }

            try (FileWriter fw = new FileWriter(DATA_DIR + "/contacts.txt", true)) {
                fw.write(name + "|" + email + "|" + message + "|" + new java.util.Date().toString() + "\n");
            }
            sendJson(ex, 200, "{\"success\":true}");
        }
    }

    // ============================================
    //  AVAILABILITY CHECK
    // ============================================
    static class AvailabilityHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            if (!"POST".equals(ex.getRequestMethod())) {
                sendJson(ex, 405, "{\"success\":false,\"error\":\"Method not allowed\"}"); return;
            }
            String body = readBody(ex);
            String roomType = getJsonValue(body, "roomType");
            String checkin = getJsonValue(body, "checkin");
            String checkout = getJsonValue(body, "checkout");

            if (roomType.isEmpty() || checkin.isEmpty() || checkout.isEmpty()) {
                sendJson(ex, 200, "{\"success\":false,\"error\":\"Fadlan buuxi dhammaan!\"}"); return;
            }

            File f = new File(DATA_DIR + "/reservations.txt");
            boolean available = true;
            if (f.exists()) {
                for (String line : Files.readAllLines(f.toPath())) {
                    if (!line.trim().isEmpty()) {
                        String[] parts = line.split("\\|");
                        if (parts.length >= 6 && parts[3].equalsIgnoreCase(roomType)) {
                            // Hubi haddii uu jiro booking isku waqti ah
                            String existCheckin = parts[4];
                            String existCheckout = parts[5];
                            // Check overlap: (checkin < existCheckout) && (checkout > existCheckin)
                            if (checkin.compareTo(existCheckout) < 0 && checkout.compareTo(existCheckin) > 0) {
                                available = false;
                                break;
                            }
                        }
                    }
                }
            }

            sendJson(ex, 200, "{\"success\":true,\"available\":" + available + "}");
        }
    }

    // ============================================
    //  STATIC FILE HANDLER
    // ============================================
    static class StaticFileHandler implements HttpHandler {
        public void handle(HttpExchange ex) throws IOException {
            String path = ex.getRequestURI().getPath();
            if (path.equals("/")) path = "/index.html";

            String filePath = WWW_DIR + path;
            File file = new File(filePath);
            if (!file.exists() || file.isDirectory()) {
                file = new File(WWW_DIR + "/pages" + path);
                if (!file.exists()) {
                    sendJson(ex, 404, "{\"error\":\"404 Not Found: " + path + "\"}");
                    return;
                }
            }

            String mime = "text/html";
            if (path.endsWith(".css")) mime = "text/css";
            else if (path.endsWith(".js")) mime = "application/javascript";
            else if (path.endsWith(".png")) mime = "image/png";
            else if (path.endsWith(".jpg") || path.endsWith(".jpeg")) mime = "image/jpeg";
            else if (path.endsWith(".gif")) mime = "image/gif";
            else if (path.endsWith(".ico")) mime = "image/x-icon";
            else if (path.endsWith(".txt")) mime = "text/plain";

            ex.getResponseHeaders().set("Content-Type", mime);
            ex.sendResponseHeaders(200, file.length());
            try (OutputStream os = ex.getResponseBody();
                 FileInputStream fis = new FileInputStream(file)) {
                byte[] buf = new byte[8192];
                int n;
                while ((n = fis.read(buf)) != -1) os.write(buf, 0, n);
            }
        }
    }

    // ============================================
    //  HELPERS
    // ============================================

    static List<String[]> readUsers() {
        List<String[]> users = new ArrayList<>();
        File f = new File(DATA_DIR + "/users.txt");
        if (!f.exists()) return users;
        try {
            for (String line : Files.readAllLines(f.toPath())) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 5) users.add(parts);
                }
            }
        } catch (Exception e) {}
        return users;
    }

    static List<String[]> readRooms() {
        List<String[]> rooms = new ArrayList<>();
        File f = new File(DATA_DIR + "/rooms.txt");
        if (!f.exists()) return rooms;
        try {
            for (String line : Files.readAllLines(f.toPath())) {
                line = line.trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split("\\|");
                    if (parts.length >= 2) rooms.add(parts);
                }
            }
        } catch (Exception e) {}
        return rooms;
    }

    static String readBody(HttpExchange ex) throws IOException {
        try (InputStream is = ex.getRequestBody();
             Scanner s = new Scanner(is, "UTF-8")) {
            return s.useDelimiter("\\A").hasNext() ? s.next() : "";
        }
    }

    static String getJsonValue(String json, String key) {
        String search = "\"" + key + "\":\"";
        int start = json.indexOf(search);
        if (start == -1) return "";
        start += search.length();
        int end = json.indexOf("\"", start);
        if (end == -1) return "";
        return json.substring(start, end);
    }

    static void sendJson(HttpExchange ex, int status, String json) throws IOException {
        byte[] bytes = json.getBytes("UTF-8");
        ex.getResponseHeaders().set("Content-Type", "application/json");
        ex.sendResponseHeaders(status, bytes.length);
        try (OutputStream os = ex.getResponseBody()) {
            os.write(bytes);
        }
    }
}

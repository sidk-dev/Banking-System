package daos;

import org.apache.commons.csv.*;
import models.User;
import session.Session;
import utils.Display;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.UUID;

public final class UserDAO {
    private static final String[] headers = {"FullName", "Email", "Password", "UUID"};
    public static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader(headers)
            .get();

    private UserDAO() {}

    public static void saveUser(String fullName, String email, String password) {
        try (
                FileWriter writer = new FileWriter("users.csv", true);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSV_FORMAT)
        ){
            String uuid = UUID.randomUUID().toString();
            csvPrinter.printRecord(fullName, email, password, uuid);
            Display.success("User Saved, you can log in now!");
        } catch (IOException e) {
            Display.error("Something went wrong: " + e.getMessage());
        }
    }

    public static void loginUser(String email, String password) {
        try (Reader reader = new FileReader("users.csv")) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                    .setSkipHeaderRecord(true)
                    .setHeader(headers)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                if (email.equals(record.get("Email")) && password.equals(record.get("Password"))) {
                    User u = new User(record.get("Full Name"), email, record.get("UUID"));
                    Session.saveCurrentUser(u);

                    Display.success("User logged in successfully!");
                    return;
                }
            }

            Display.error("No such user found.");
        } catch (Exception e) {
            Display.error("Something went wrong: " + e.getMessage());
            // e.printStackTrace();
        }
    }
}

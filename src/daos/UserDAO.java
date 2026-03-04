package daos;

import models.Account;
import org.apache.commons.csv.*;
import models.User;
import session.Session;
import utils.Display;

import java.io.*;
import java.util.UUID;

public final class UserDAO {
    private static final String fileName = "users.csv";
    private static final String[] headers = {"UUID", "FullName", "Email", "Password"};
    public static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader(headers)
            .get();

    private UserDAO() {}

    public static void saveUser(String fullName, String email, String password) {
        try (
                Writer writer = new FileWriter(fileName, true);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSV_FORMAT)
        ){
            String uuid = UUID.randomUUID().toString();
            csvPrinter.printRecord(uuid, fullName, email, password);
            Display.success("User Saved, you can log in now!");
            AccountDAO.createAccount(uuid);
        } catch (IOException e) {
            Display.error("Something went wrong: " + e.getMessage());
        }
    }

    public static void loginUser(String email, String password) {
        try (Reader reader = new FileReader(fileName)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                    .setSkipHeaderRecord(true)
                    .setHeader(headers)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                if (email.equals(record.get("Email")) && password.equals(record.get("Password"))) {
                    User u = new User(record.get("FullName"), email, record.get("UUID"));
                    Session.saveCurrentUser(u);
                    Account.setBalance(AccountDAO.getBalance());
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

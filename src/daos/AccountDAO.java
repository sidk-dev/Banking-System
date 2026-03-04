package daos;

import models.User;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import session.Session;
import utils.Display;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public final class AccountDAO {
    private static final String fileName = "accounts.csv";
    private static final String[] headers = {"UUID", "Balance"};
    public static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader(headers)
            .get();

    private AccountDAO() {}

    public static void createAccount(String uuid) {
        try (
                FileWriter writer = new FileWriter(fileName, true);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSV_FORMAT)
        ){
            csvPrinter.printRecord(uuid, 0);
            Display.success("Account created!");
        } catch (IOException e) {
            Display.error("Something went wrong: " + e.getMessage());
        }
    }

    public static int getBalance() {
        try (Reader reader = new FileReader(fileName)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                    .setSkipHeaderRecord(true)
                    .setHeader(headers)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                if (Session.getCurrentUser().getUUID().equals(record.get("UUID"))) {
                    return Integer.parseInt(record.get("Balance"));
                }
            }
        } catch (Exception e) {
            Display.error("Something went wrong: " + e.getMessage());
            // e.printStackTrace();
        }
        return 0;
    }
}

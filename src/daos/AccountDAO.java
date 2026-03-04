package daos;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import session.Session;
import utils.Display;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    public static void setBalance(double balance) {
        String tempPath = "temp_account.csv";
        try (
                FileWriter writer = new FileWriter(tempPath, true);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSV_FORMAT);
                Reader reader = new FileReader(fileName)
        ){
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                    .setSkipHeaderRecord(true)
                    .setHeader(headers)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                if (Session.getCurrentUser().getUUID().equals(record.get("UUID"))) {
                    csvPrinter.printRecord(record.get("UUID"), balance);
                } else {
                    csvPrinter.printRecord(record.get("UUID"), record.get("Balance"));
                }
            }
        } catch (IOException e) {
            Display.error("Something went wrong: " + e.getMessage());
        }

        try {
            Files.move(Paths.get(tempPath), Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            Display.error("Something went wrong: " + e.getMessage());
        }
    }

    public static double getBalance() {
        try (Reader reader = new FileReader(fileName)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                    .setSkipHeaderRecord(true)
                    .setHeader(headers)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                if (Session.getCurrentUser().getUUID().equals(record.get("UUID"))) {
                    return Double.parseDouble(record.get("Balance"));
                }
            }
        } catch (Exception e) {
            Display.error("Something went wrong: " + e.getMessage());
            // e.printStackTrace();
        }
        return 0;
    }
}

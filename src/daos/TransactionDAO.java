package daos;

import models.Transaction;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import session.Session;
import utils.Display;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class TransactionDAO {
    private static final String fileName = "transactions.csv";
    private static final String[] headers = {"ID", "UserID", "Amount", "Type", "TimeStamp"};
    public static final CSVFormat CSV_FORMAT = CSVFormat.DEFAULT.builder()
            .setHeader(headers)
            .get();

    private TransactionDAO() {}

    public static void createTransaction(long amount, Transaction.TYPE type) {
        try (
                FileWriter writer = new FileWriter(fileName, true);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSV_FORMAT)
        ){
            String id = UUID.randomUUID().toString();
            csvPrinter.printRecord(
                    id,
                    Session.getCurrentUser().getUUID(),
                    amount,
                    type,
                    ZonedDateTime.now(ZoneId.of("Asia/Kolkata"))
            );
        } catch (IOException e) {
            Display.error("Something went wrong: " + e.getMessage());
        }
    }

    public static List<Transaction> getTransactions() {
        List<Transaction> transactionList = new ArrayList<>();

        File file = new File(fileName);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            Display.error("Something went wrong: " + e.getMessage());
        }

        try (Reader reader = new FileReader(fileName)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.builder()
                    .setSkipHeaderRecord(true)
                    .setHeader(headers)
                    .build()
                    .parse(reader);

            for (CSVRecord record : records) {
                if (Session.getCurrentUser().getUUID().equals(record.get("UserID"))) {
                    transactionList.add(new Transaction(
                            UUID.fromString(record.get("ID")),
                            UUID.fromString(record.get("UserID")),
                            Integer.parseInt(record.get("Amount")),
                            Transaction.TYPE.valueOf(record.get("Type")),
                            ZonedDateTime.parse(record.get("TimeStamp"))
                    ));
                }
            }
        } catch (Exception e) {
            Display.error("Something went wrong: " + e.getMessage());
        }

        return transactionList;
    }
}

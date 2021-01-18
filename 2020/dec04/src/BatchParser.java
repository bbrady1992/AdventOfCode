import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BatchParser {
    BatchParser(String filename) {
        this.filename = filename;
    }

    public List<Passport> Read() {
        List<String> batches = ParseBatches();
        List<Passport> passports = new ArrayList<>();
        for (var batch: batches) {
            passports.add(new Passport(batch.trim()));
        }
        return passports;
    }

    private List<String> ParseBatches() {
        FileReader f = null;
        try {
            f = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var in = new Scanner(f);
        var sb = new StringBuilder();
        List<String> batches = new ArrayList<>();
        while (in.hasNextLine()) {
            String nextLine = in.nextLine();
            if (nextLine.equals("")) {
                batches.add(sb.toString());
                sb.setLength(0);
            }
            sb.append(nextLine);
            sb.append(" ");
        }
        if (sb.length() != 0) {
            batches.add(sb.toString());
        }
        return batches;
    }

    private final String filename;
}

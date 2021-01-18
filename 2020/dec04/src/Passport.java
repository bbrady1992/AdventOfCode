import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Passport {
    Passport(String batch) {
        passportData = new HashMap<>();
        String[] passportFields = batch.split(" ");
        for (String field: passportFields) {
            String[] keyValuePair = field.split(":");
            passportData.put(keyValuePair[0], keyValuePair[1]);
        }

        //System.out.println("----- Passport Fields -----");
        //for (var kv: passportData.entrySet()) {
        //    System.out.printf("('%s', '%s')\n", kv.getKey() , kv.getValue());
        //}
        //System.out.println("---------------------------");
    }

    public boolean IsValid() {
        List<String> missingFields = new ArrayList<>();
        for (String requiredField: requiredFields) {
            if (!passportData.containsKey(requiredField)) {
                missingFields.add(requiredField);
            }
        }
        return missingFields.size() == 0;
    }

    private Map<String, String> passportData;
    private String[] requiredFields = {"byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid"};
}

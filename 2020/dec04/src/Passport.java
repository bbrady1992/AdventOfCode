import java.util.HashMap;
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
    }

    public boolean IsValid() {
        return byrValid() &&
                iyrValid() &&
                eyrValid() &&
                hgtValid() &&
                hclValid() &&
                eclValid() &&
                pidValid();
    }

    private boolean byrValid() {
        return fieldValid("byr", (v) -> {
            if (v.length() != 4) {
                return false;
            }
            Integer year = Integer.parseInt(v);
            return year >= 1920 && year <= 2002;
        });
    }

    private boolean iyrValid() {
        return fieldValid("iyr", (v)  -> {
            if (v.length() != 4) {
                return false;
            }
            Integer year = Integer.parseInt(v);
            return year >= 2010 && year <= 2020;
        });
    }

    private boolean eyrValid() {
        return fieldValid("eyr", (v) -> {
            if (v.length() != 4) {
                return false;
            }
            Integer year = Integer.parseInt(v);
            return year >= 2020 && year <= 2030;
        });
    }

    private boolean hgtValid() {
        return fieldValid("hgt", (v) -> {
            Pattern hgtPattern = Pattern.compile("([0-9]{2,3})([cmin]{2})");
            Matcher m = hgtPattern.matcher(v);
            if (!m.find()) {
                return false;
            }
            String units = m.group(2);
            Integer hgtValue = Integer.parseInt(m.group(1));
            switch (units) {
                case "in":
                    return hgtValue >= 59 && hgtValue <= 76;
                case "cm":
                    return hgtValue >= 150 && hgtValue <= 193;
                default:
                    return false;
            }
        });
    }

    private boolean hclValid() {
        return fieldValid("hcl", (v) -> {
            Pattern hclPattern = Pattern.compile("#[0-9a-f]{6}");
            Matcher m = hclPattern.matcher(v);
            return m.find();
        });
    }

    private boolean eclValid() {
        return fieldValid("ecl", (v) -> {
            return v.equals("amb") ||
                    v.equals("blu") ||
                    v.equals("brn") ||
                    v.equals("gry") ||
                    v.equals("grn") ||
                    v.equals("hzl") ||
                    v.equals("oth");
        });
    }

    private boolean pidValid() {
        return fieldValid("pid", (v) -> {
            Pattern pidPattern = Pattern.compile("^[0-9]{9}$");
            Matcher m = pidPattern.matcher(v);
            return m.find();
        });
    }


    private interface FieldValidationFunction {
        public boolean IsValid(String key);
    }

    private boolean fieldValid(String key, FieldValidationFunction validationFunction) {
        String value = passportData.get(key);
        if (value == null) {
            return false;
        }
        return validationFunction.IsValid(value);
    }

    private Map<String, String> passportData;
}

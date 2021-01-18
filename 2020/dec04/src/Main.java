import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = "input.txt";
        var parser = new BatchParser(input);
        List<Passport> passports = parser.Read();
        var wrapper = new Object(){int validPassports = 0;};
        passports.forEach((p) -> {
            wrapper.validPassports += (p.IsValid() ? 1 : 0);
        });
        System.out.println("Valid passports: " + wrapper.validPassports);
    }
}

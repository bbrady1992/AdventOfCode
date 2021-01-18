import java.util.List;

public class Main {
    public static void main(String[] args) {
        String input = "input.txt";
        var parser = new BatchParser(input);
        List<Passport> passports = parser.Read();
        long validPassports = passports.stream().filter((p) -> p.IsValid()).count();
        System.out.println("Valid passports: " + validPassports);
    }
}

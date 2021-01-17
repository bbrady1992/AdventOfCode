import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Part1 {
    public static void main(String[] args) {
        File inputFile = new File("input.txt");
        Scanner in = null;
        try{
            in = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int validPasswords = 0;
        while (in.hasNextLine()) {
            // Read each line. Split into password and rule it's being checked against
            String passwordLine = in.nextLine();
            String[] tokens = passwordLine.split(":");
            String ruleString = tokens[0];
            String password = tokens[1].trim();

            // Check if password is valid
            Rule r = new Rule(ruleString);
            if (r.PassesRule(password)) {
                validPasswords++;
            }
        }
        System.out.printf("Valid passwords: %d\n", validPasswords);
    }
}
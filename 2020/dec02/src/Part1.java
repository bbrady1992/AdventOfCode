import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;

public class Part1 {
    public static void main(String[] args) {

        int validPasswords = 0;
        var inputReader = new InputReader("input.txt");
        var passwordCandidates = inputReader.Read();
        for (PasswordCandidate candidate: passwordCandidates) {
            //RuleInterface r = new Part1Rule(candidate.ruleString());
            RuleInterface r = new Part2Rule(candidate.ruleString());
            if (r.Passes(candidate.password())) {
                ++validPasswords;
            }
        }
        System.out.printf("Valid passwords: %d\n", validPasswords);
    }
}
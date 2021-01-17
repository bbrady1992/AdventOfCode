import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Rule {
    int lowerBound;
    int upperBound;
    char character;

    public Rule(String ruleString) {
        SetRule(ruleString);
    }

    public void SetRule(String ruleString) {
        Pattern pattern = Pattern.compile("([0-9]+)-([0-9]+) ([A-Za-z])");
        Matcher m = pattern.matcher(ruleString);

        if (m.find()) {
            lowerBound = Integer.parseInt(m.group(1));
            upperBound = Integer.parseInt(m.group(2));
            character = m.group(3).charAt(0);
        } else {
            System.out.println("Failed to extract rule");
        }
    }

    public boolean PassesRule(String input) {
        long count = input.chars().filter(ch -> ch == character).count();
        return count >= lowerBound && count <= upperBound;
    }

    public void PrintRule() {
        System.out.println(lowerBound + "-" + upperBound + " " + character);
    }
}
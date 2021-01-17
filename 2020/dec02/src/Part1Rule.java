import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1Rule implements RuleInterface {

    public Part1Rule(String ruleString) {
        SetRule(ruleString);
    }

    @Override
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

    @Override
    public boolean Passes(String input) {
        long count = input.chars().filter(ch -> ch == character).count();
        return count >= lowerBound && count <= upperBound;
    }

    private int lowerBound;
    private int upperBound;
    private char character;
}
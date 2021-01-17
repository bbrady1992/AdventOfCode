import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2Rule implements RuleInterface{
    public Part2Rule(String ruleString) {
        SetRule(ruleString);
    }


    @Override
    public void SetRule(String ruleString) {
        Pattern pattern = Pattern.compile("([0-9]+)-([0-9]+) ([A-Za-z])");
        Matcher m = pattern.matcher(ruleString);

        if (m.find()) {
            lowerPosition = Integer.parseInt(m.group(1));
            upperPosition = Integer.parseInt(m.group(2));
            character = m.group(3).charAt(0);
        } else {
            System.out.println("Failed to extract rule");
        }
    }

    @Override
    public boolean Passes(String password) {
        int lowerTotal = password.charAt(lowerPosition - 1) == character ? 1 : 0;
        int upperTotal = password.charAt(upperPosition - 1) == character ? 1 : 0;
        return lowerTotal + upperTotal == 1;
    }

    private int lowerPosition;
    private int upperPosition;
    private char character;
}

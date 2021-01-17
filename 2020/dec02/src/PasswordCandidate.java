public class PasswordCandidate {
    PasswordCandidate(String passwordLine) {
        String[] tokens = passwordLine.split(":");
        ruleString = tokens[0];
        password = tokens[1].trim();
    }

    private String ruleString;
    private String password;

    public String ruleString() {
        return ruleString;
    }

    public String password() {
        return password;
    }
}

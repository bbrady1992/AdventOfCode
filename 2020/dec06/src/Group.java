import java.util.stream.IntStream;

public class Group {
    Group(String groupString) {
       this.groupString = groupString.trim();
    }

    public Integer GroupSum() {
        int[] groupAnswers = new int[26];
        for (int i = 0; i < groupString.length(); ++i) {
            groupAnswers[(int)(groupString.charAt(i)) - (int)('a')] = 1;
        }
        return IntStream.of(groupAnswers).sum();
    }

    String groupString;
}

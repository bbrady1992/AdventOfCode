import java.util.stream.IntStream;

public class Group {
    Group(String groupString) {
       this.groupString = groupString.trim();
    }

    void CollateAnswers() {
        int[] groupAnswers = new int[26];
        for (int i = 0; i < groupString.length(); ++i) {
            if (groupString.charAt(i) == '\n') {
                continue;
            }
            groupAnswers[(int)(groupString.charAt(i)) - (int)('a')] += 1;
        }

        totalQuestionsAnswered = IntStream.of(groupAnswers).filter((c) -> {
            return c > 0;
        }).count();

        var people = new Object(){ final int length = groupString.split("\n").length;};
        allYeses = IntStream.of(groupAnswers).filter((c) -> {
            return c == people.length;
        }).count();
    }

    public Long GroupYeses() {
        return allYeses;
    }

    public Long TotalQuestionsAnswered() {
        return totalQuestionsAnswered;
    }

    String groupString;
    Long totalQuestionsAnswered = 0L;
    Long allYeses = 0L;
}

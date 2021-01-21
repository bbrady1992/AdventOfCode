public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: 'java Main </path/to/input.txt>'");
            return;
        }

        var group = new Object(){ long totalAnswered = 0L; long allYeses = 0L; };
        new InputParser().ParseGroups(args[0]).forEach((g) -> {
            g.CollateAnswers();
            group.totalAnswered += g.TotalQuestionsAnswered();
            group.allYeses += g.GroupYeses();
        });
        System.out.println("Sum = " + group.totalAnswered);
        System.out.println("All yeses = " + group.allYeses);
    }
}

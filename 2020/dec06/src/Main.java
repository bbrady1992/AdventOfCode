import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: 'java Main </path/to/input.txt>'");
            return;
        }

        var group = new Object(){int sum = 0;};
        new InputParser().ParseGroups(args[0]).forEach((g) -> {
            group.sum += g.GroupSum();
        });
        System.out.println("Sum = " + group.sum);
    }
}

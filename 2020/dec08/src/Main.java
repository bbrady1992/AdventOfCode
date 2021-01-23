import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: 'java Main <input.txt>'");
            return;
        }

        FileReader f = null;
        try {
            f = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        var ops = new ArrayList<String>();
        new Scanner(f).useDelimiter("\n").forEachRemaining((s) -> ops.add(s));

        List<Long> executionTimes = new ArrayList<>();
        Integer correctedOpCodeAccumulator = null;
        for (int i = 0; i < 200; ++i) {
            final long startTime = System.currentTimeMillis();
            OpCodeFixer fixer = new OpCodeFixer(ops, 4);
            correctedOpCodeAccumulator = fixer.Run();
            final long endTime = System.currentTimeMillis();
            executionTimes.add(endTime - startTime);
        }
        System.out.println("Found corrected OpCode accumulator - " + correctedOpCodeAccumulator);
        System.out.println("Average execution time w/ ThreadPool = " + executionTimes.stream().mapToDouble(a -> a).average().getAsDouble());
    }
}

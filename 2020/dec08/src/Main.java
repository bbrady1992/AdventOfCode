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

        var opCodeRunner = new OpCodeRunner(ops);
        opCodeRunner.Run();

        System.out.println("Accumulator = " + opCodeRunner.Accumulator());
    }
}

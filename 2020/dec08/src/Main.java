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

        List<String> ops = new ArrayList<>();
        new Scanner(f).useDelimiter("\n").forEachRemaining((s) -> ops.add(s));

        Integer accumulator = 0;
        int currentOpIndex = 0;

        String currentOp = ops.get(currentOpIndex);
        while (currentOp != null) {
            String[] opInstruction = currentOp.trim().split(" ");
            String op = opInstruction[0];
            Integer opValue = Integer.parseInt(opInstruction[1]);

            ops.set(currentOpIndex, null);

            switch (op) {
                case "acc":
                    accumulator += opValue;
                    currentOpIndex += 1;
                    break;
                case "jmp":
                    currentOpIndex += opValue;
                    break;
                case "nop":
                    currentOpIndex += 1;
                    break;
            }

            currentOp = ops.get(currentOpIndex);
        }


        System.out.println("Accumulator = " + accumulator);
    }
}

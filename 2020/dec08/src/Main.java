import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
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

        var opCodeRunner = new OpCodeRunner((ArrayList)ops.clone());
        opCodeRunner.Run();
        System.out.println("Accumulator = " + opCodeRunner.Accumulator());

        var failedHistory = opCodeRunner.History();
        for (var i: failedHistory) {
            if (ops.get(i).startsWith("nop") || ops.get(i).startsWith("jmp")) {
                ArrayList<String> modifiedOpCode = (ArrayList)ops.clone();
                String modifiedOpInstruction = FlipOpInstruction(modifiedOpCode.get(i));
                modifiedOpCode.set(i, modifiedOpInstruction);
                var testRunner = new OpCodeRunner(modifiedOpCode);
                if (testRunner.Run()) {
                    System.out.println("Fixed op code. Accumulator = " + testRunner.Accumulator());
                    break;
                }
            }
        }

    }

    private static String FlipOpInstruction(String opInstructionString) {
        String[] opInstruction = opInstructionString.trim().split(" ");
        String op = opInstruction[0];

        String modifiedOpInstruction = opInstructionString;
        if (op.equals("nop")) {
            modifiedOpInstruction = "jmp " + opInstruction[1];
        } else if (op.equals("jmp")) {
            modifiedOpInstruction = "nop " + opInstruction[1];
        }
        return modifiedOpInstruction;
    }
}

import java.util.ArrayList;
import java.util.List;

public class OpCodeRunner {
    /**
     * Constructs a new OpCodeRunner instance using a list of op codes to execute
     * @param opCodes List of op codes
     */
    public OpCodeRunner(ArrayList<String> opCodes) {
        this.opCodes = opCodes;
    }

    /**
     * @return True if code runs without infinite loops
     */
    public Boolean Run() {
        int currentOpIndex = 0;
        accumulator = 0;

        String currentOp = opCodes.get(currentOpIndex);
        while (currentOp != null && currentOpIndex != opCodes.size()) {
            String[] opInstruction = currentOp.trim().split(" ");
            String op = opInstruction[0];
            Integer opValue = Integer.parseInt(opInstruction[1]);

            opCodes.set(currentOpIndex, null);
            history.add(currentOpIndex);
            switch (op) {
                case "acc":
                    accumulator += opValue;
                    ++currentOpIndex;
                    break;
                case "jmp":
                    currentOpIndex += opValue;
                    break;
                case "nop":
                    ++currentOpIndex;
                    break;
            }
            if (currentOpIndex < opCodes.size()) {
                currentOp = opCodes.get(currentOpIndex);
            }
        }
        return currentOpIndex == opCodes.size();
    }

    public Integer Accumulator() {
        return accumulator;
    }

    public List<Integer> History() {
        return history;
    }

    private ArrayList<String> opCodes = null;
    private Integer accumulator;
    private ArrayList<Integer> history = new ArrayList<>();
}

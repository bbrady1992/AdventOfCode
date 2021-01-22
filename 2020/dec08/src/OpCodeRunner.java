import java.util.ArrayList;

public class OpCodeRunner {
    /**
     * Constructs a new OpCodeRunner instance by cloning a list of op codes
     * @param opCodes List of op codes. Reference is cloned
     */
    OpCodeRunner(ArrayList<String> opCodes) {
        this.opCodes = (ArrayList)opCodes.clone();
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
            currentOp = opCodes.get(currentOpIndex);
        }
        return currentOpIndex == opCodes.size();
    }

    public Integer Accumulator() {
        return accumulator;
    }

    private ArrayList<String> opCodes = null;
    private Integer accumulator;
}

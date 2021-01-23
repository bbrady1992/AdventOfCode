import java.util.ArrayList;
import java.util.List;

public class OpCodeRunner {
    public class OpCodeResult {
        private Boolean finished = false;
        private Integer accumulator = 0;

        public Boolean Finished() {
            return finished;
        }

        public void SetFinished(Boolean finished) {
            this.finished = finished;
        }

        public Integer Accumulator() {
            return accumulator;
        }

        public void Accumulate(Integer value) {
            accumulator += value;
        }
    }

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
    public OpCodeResult Run() {
        int currentOpIndex = 0;
        OpCodeResult result = new OpCodeResult();

        String currentOp = opCodes.get(currentOpIndex);
        while (currentOp != null && currentOpIndex != opCodes.size()) {
            String[] opInstruction = currentOp.trim().split(" ");
            String op = opInstruction[0];
            Integer opValue = Integer.parseInt(opInstruction[1]);

            opCodes.set(currentOpIndex, null);
            history.add(currentOpIndex);
            switch (op) {
                case "acc":
                    result.Accumulate(opValue);
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
        result.SetFinished(currentOpIndex == opCodes.size());
        return result;
    }

    public List<Integer> History() {
        return history;
    }

    private ArrayList<String> opCodes = null;
    private ArrayList<Integer> history = new ArrayList<>();
}

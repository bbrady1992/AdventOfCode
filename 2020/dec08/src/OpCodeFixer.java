import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class OpCodeFixer {
    public OpCodeFixer(ArrayList<String> opCode, Integer nthreads) {
        executorService = Executors.newFixedThreadPool(nthreads);
        opCodeOriginal = opCode;
        modifiedOpCodeResults = Collections.synchronizedList(new ArrayList<>());
    }

    /**
     * Iteratively tests fixed opcodes and returns the Accumulator value of the
     * correct opCode
     * @return Accumulator value of correct opCode
     */
    public Integer Run() {
        var opCodeRunner = new OpCodeRunner((ArrayList) opCodeOriginal.clone());
        var part1Result = opCodeRunner.Run();
        System.out.println("OpCodeFixer - Found infinite loop. Accumulator = " + part1Result.Accumulator());

        Thread resultChecker = new Thread() {
            public void run() {
                while (!resultFound) {
                    synchronized (modifiedOpCodeResults) {
                        var i = modifiedOpCodeResults.iterator();
                        while (i.hasNext()) {
                            Future<OpCodeRunner.OpCodeResult> nextResult = i.next();
                            if (nextResult.isDone()) {
                                OpCodeRunner.OpCodeResult result = null;
                                try {
                                    result = nextResult.get();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if (result.Finished()) {
                                    resultFound = true;
                                    correctedCodeFound.set(true);
                                    correctedCodeAccumulator.set(result.Accumulator());
                                }
                            }
                        }
                    }
                }
            }

            private Boolean resultFound = false;
        };
        resultChecker.start();;

        var failedHistory = opCodeRunner.History();
        for (var i: failedHistory) {
            if (opCodeOriginal.get(i).startsWith("nop") || opCodeOriginal.get(i).startsWith("jmp")) {
                ArrayList<String> modifiedOpCode = (ArrayList)opCodeOriginal.clone();
                String modifiedOpInstruction = FlipOpInstruction(modifiedOpCode.get(i));
                modifiedOpCode.set(i, modifiedOpInstruction);
                var resultFuture = RunOpCode(modifiedOpCode);
                synchronized (modifiedOpCodeResults) {
                    modifiedOpCodeResults.add(resultFuture);
                }
            }
            if (correctedCodeFound.get()) {
                break;
            }
        }
        try {
            resultChecker.join();
            executorService.shutdown();
        } catch (Exception e) {
        }
        return correctedCodeAccumulator.get();
    }

    private Future<OpCodeRunner.OpCodeResult> RunOpCode(ArrayList<String> opCode) {
        return executorService.submit(() -> {
            return new OpCodeRunner(opCode).Run();
        });
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

    private ExecutorService executorService;
    private ArrayList<String> opCodeOriginal;
    private List<Future<OpCodeRunner.OpCodeResult>> modifiedOpCodeResults;
    private AtomicInteger correctedCodeAccumulator = new AtomicInteger(0);
    private AtomicBoolean correctedCodeFound = new AtomicBoolean(false);
}

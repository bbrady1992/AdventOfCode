import java.util.List;

public class Part2 {
    public static void main(String[] args) {
        List<Integer> inputs = InputHandler.GetInputs("input.txt");
        for (int index = 0; index < inputs.size() - 1; index++) {
            Integer n1 = inputs.get(index);
            Integer n2 = inputs.get(index + 1);
            Integer complement = 2020 - n1 - n2;
            int complementIndex = -1;
            if ((complementIndex = inputs.indexOf(complement)) != -1) {
                System.out.println(n1 * n2 * inputs.get(complementIndex));
                break;
            }
        }
    }
}
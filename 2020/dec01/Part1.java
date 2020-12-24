import java.util.List;


public class Part1 {
    public static void main(String[] args) {
          List<Integer> inputs = InputHandler.GetInputs("input.txt");
          for (Integer n : inputs) {
              Integer complement = 2020 - n;
              int index = -1;
              if ((index = inputs.indexOf(complement)) != -1) {
                  System.out.println(inputs.get(index) * n);
                  break;
              }
          }
    }
}

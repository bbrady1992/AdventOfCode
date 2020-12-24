import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;
import java.util.Collections;


public class Part1 {
    public static void main(String[] args) {
          List<Integer> inputs = GetInputs("input.txt");
          for (Integer n : inputs) {
              Integer complement = 2020 - n;
              int index = -1;
              if ((index = inputs.indexOf(complement)) != -1) {
                  System.out.println(inputs.get(index) * n);
                  break;
              }
          }
    }

    public static List<Integer> GetInputs(String filename) {
        List<Integer> l = new ArrayList<Integer>();
        try {
            File inputFile = new File(filename);
            Scanner in = new Scanner(inputFile);
            while (in.hasNextInt()) {
                Integer n = in.nextInt();
                l.add(n);
            }
            Collections.sort(l);
            return l;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

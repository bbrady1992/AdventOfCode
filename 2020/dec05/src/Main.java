import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: 'java Main <input_file>");
        }

        FileReader f;
        try {
            f = new FileReader(args[0]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        List<Integer> hashes = new ArrayList<>();
        new Scanner(f).useDelimiter("\n").forEachRemaining((partitionString) -> {
            int row = BinaryPartition(0, 'F', 127, 'B', partitionString.substring(0, 7));
            int col = BinaryPartition(0, 'L', 7, 'R', partitionString.substring(7));
            hashes.add(Hash(row, col));
        });

        var max = new Object(){int hash = 0;};
        hashes.forEach((h) -> max.hash = Math.max(max.hash, h));
        System.out.printf("Checked %d hashes. Maximum is %d\n", hashes.size(), max.hash);
    }

    static int BinaryPartition(int lowerBound, final char lowerBoundChar, int upperBound, final char upperBoundChar, String partitionString) {
        for (int i = 0; i < partitionString.length(); ++i) {
            if (partitionString.charAt(i) == lowerBoundChar) {
                upperBound = lowerBound + Math.floorDiv(upperBound - lowerBound, 2);
            } else if (partitionString.charAt(i) == upperBoundChar) {
                lowerBound = lowerBound + ((upperBound - lowerBound + 1) / 2);
            }
        }
        return lowerBound;
    }

    static Integer Hash(int row, int column) {
        return (row * 8) + column;
    }
}
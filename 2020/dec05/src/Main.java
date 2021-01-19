import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

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

        Set<Integer> hashes = new HashSet<>();
        var max = new Object(){int hash = 0;};
        new Scanner(f).useDelimiter("\n").forEachRemaining((partitionString) -> {
            String binaryString = partitionString.trim()
                    .replace('F', '0')
                    .replace('B', '1')
                    .replace('L', '0')
                    .replace('R', '1');
            Integer row = Integer.parseInt(binaryString.substring(0, 7), 2);
            Integer col = Integer.parseInt(binaryString.substring(7), 2);
            Integer seatID = Hash(row, col);
            max.hash = Math.max(max.hash, seatID);
            hashes.add(seatID);
        });
        System.out.printf("Checked %d hashes. Maximum is %d\n", hashes.size(), max.hash);

        for (int r = 0; r < 128; ++r) {
            for (int c = 0; c < 8; ++c) {
                Integer h = Hash(r, c);
                if (!hashes.contains(h) && hashes.contains(h - 1) && hashes.contains(h + 1)) {
                    System.out.println("Found seat ID - " + h);

                    return;
                }
            }
        }
    }

    static Integer Hash(int row, int column) {
        return (row * 8) + column;
    }
}

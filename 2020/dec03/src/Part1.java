import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;

public class Part1 {
    public static void main(String[] args) {
        for (var i = 0; i < args.length; ++i) {
            System.out.printf("Arg %d = '%s'\n", i, args[i]);
        }
        if (args.length != 1) {
            System.out.println("Usage: java Part1 <input_file>");
            return;
        }

        TreeMap map = new TreeMap(args[0]);
        int treesHit = 0;
        try {
            map.Read();
            int x = 0;
            int y = 0;
            while (!map.AtBottom(y)) {
                treesHit += (map.HitTreeAt(x, y) ? 1 : 0);
                x += 3;
                y += 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.printf("Hit %d trees\n", treesHit);
    }
}

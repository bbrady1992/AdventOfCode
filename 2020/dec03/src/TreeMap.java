import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class TreeMap {
    private final String filename;
    private ArrayList<String> treeRows = new ArrayList<>();

    private final char empty = '.';
    private final char tree = '#';
    private int rowLength;

    TreeMap(String filename) {
        this.filename = filename;
    }

    public void Read() throws FileNotFoundException {
        FileReader f = new FileReader(filename);
        Scanner in = new Scanner(f);
        while (in.hasNextLine()) {
            treeRows.add(in.nextLine());
        }
        rowLength = treeRows.get(0).length();
    }

    public boolean HitTreeAt(int x, int y) {
        return treeRows.get(y).charAt(x % rowLength) == tree;
    }

    public boolean AtBottom(int y) {
        return y >= treeRows.size();
    }
}

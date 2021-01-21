import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputParser {
    List<Group> ParseGroups(String filename) {
        FileReader f = null;
        try {
            f = new FileReader(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Group> groups = new ArrayList<>();
        Scanner in = new Scanner(f);

        StringBuilder sb = new StringBuilder();
        while (in.hasNextLine()) {
            String nextLine = in.nextLine();
            if (nextLine.equals("")) {
                groups.add(new Group(sb.toString()));
                sb.setLength(0);
            }
            sb.append(nextLine);
            sb.append("\n");
        }
        if (sb.length() != 0) {
            groups.add(new Group(sb.toString()));
        }
        return groups;
    }
}

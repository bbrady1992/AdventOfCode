import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputReader {
    InputReader(String filename) {
        File inputFile = new File(filename);
        try{
            fileScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    List<PasswordCandidate> Read() {
        var passwordCandidates = new ArrayList<PasswordCandidate>();
        while (fileScanner.hasNextLine()) {
            passwordCandidates.add(new PasswordCandidate(fileScanner.nextLine()));
        }
        return passwordCandidates;
    }

    Scanner fileScanner = null;
}

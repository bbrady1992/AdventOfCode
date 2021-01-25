import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class InputFileClient {
    public static void main(String[] args) {
        Integer PORT = 12345;
        String HOSTNAME = "127.0.0.1";
        Integer MAX_BUFFER_SIZE = 32 * 1024;

        byte[] fileBuffer = new byte[MAX_BUFFER_SIZE];
        try (
                Socket s = new Socket(HOSTNAME, PORT);
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("client_input.txt"));
                BufferedInputStream socketIn = new BufferedInputStream(s.getInputStream());
        ) {
            System.out.println("Server connection established. Starting to receive file");
            int totalBytesRead = 0;
            int bytesRead = 0;
            while ((bytesRead = socketIn.read(fileBuffer)) > 0) {
                out.write(fileBuffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
            System.out.printf("Received %d bytes from server\n", totalBytesRead);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

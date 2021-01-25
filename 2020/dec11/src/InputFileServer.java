import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class InputFileServer {
    public static void main(String[] args) {
        Integer PORT = 12345;
        Integer MAX_BUFFER_SIZE = 32 * 1024;
        String filename = args[0];


        byte[] fileBuffer = new byte[MAX_BUFFER_SIZE];
        try (
                ServerSocket serverSocket = new ServerSocket(PORT);
                Socket clientSocket = serverSocket.accept();
                OutputStream clientSocketStream = clientSocket.getOutputStream();
                BufferedInputStream in = new BufferedInputStream(new FileInputStream(filename), MAX_BUFFER_SIZE);
        ) {
            System.out.println("Client connection established. Starting to send file");
            // Iteratively read bytes into byte[] and then send to client
            int totalBytesRead = 0;
            int bytesRead = 0;
            while ((bytesRead = in.read(fileBuffer, 0, MAX_BUFFER_SIZE)) > 0) {
                clientSocketStream.write(fileBuffer, 0, bytesRead);
                totalBytesRead += bytesRead;
            }
            System.out.printf("Wrote %d bytes to client\n", totalBytesRead);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}

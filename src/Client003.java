import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Client003 {
    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("Localhost", 2105);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        out.println("3500");

        String response = in.readLine();

        System.out.println(response);

        socket.close();
    }
}

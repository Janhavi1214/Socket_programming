import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server002 {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2105);
        System.out.println("Restaurant server started...");

        Socket socket = serverSocket.accept();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

        String order = in.readLine();

        System.out.println("Order recieved : " + order);

        System.out.println("Order confirmed : " + order);

        socket.close();
        serverSocket.close();
    }
}

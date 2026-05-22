import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

class ClientHandler_003 extends Thread{ // multi threading

    Socket socket;
    static int balance = 6000;

    public ClientHandler_003(Socket socket) {
        this.socket = socket;
    }
    public void run(){
        try {
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            int deposit = Integer.parseInt(in.readLine());

            synchronized (ClientHandler_003.class){
                balance = balance + deposit;
                out.print("Deposit successful. Updated balance" + balance);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Server003 {
    public static void main(String[] args) throws IOException {

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Bank Server started.....");

        while(true){
            Socket socket = serverSocket.accept();
            new ClientHandler_003(socket).start();
        }
    }
}

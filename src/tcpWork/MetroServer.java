package tcpWork;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class MetroServer extends Thread{
    MetroCardBank bank;
    private ServerSocket serverSocket = null;
    private int port = -1;
    private boolean isStopped;

    public MetroServer(int port) {
        this.bank = new MetroCardBank();
        this.port = port;
    }

    public MetroCardBank getBank() {
        return bank;
    }
    public static void main(String[] args) {
        MetroServer srv = new MetroServer(7891);
        srv.start();
    }

    @Override
    public void run() {
        try {
            this.serverSocket = new ServerSocket(port);
            System.out.println("Metro Server started");
            while (!isStopped) {
                System.out.println("New Client Waiting...");
                Socket sock = serverSocket.accept();
                System.out.println("New client: " + sock);
                ClientHandler ch = new ClientHandler(this.getBank(), sock);
                ch.start();
            }
        } catch (IOException e) {
            if(isStopped){
                System.out.println("Server stopped!");
                return;
            }
            System.out.println("Error: " + e);
        } finally {
            try {
                serverSocket.close();
                System.out.println("Metro Server stopped");
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }
    public synchronized void stopServer() throws IOException {
        isStopped = true;
        serverSocket.close();
    }
}

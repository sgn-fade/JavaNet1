package tcpWork.Server;

import tcpWork.Data.MetroCardBank;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MetroServer extends Thread {
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
            if (isStopped) {
                System.out.println("Server stopped!");
                return;
            }
            System.out.println("Error: " + e);
        } finally {
            stopServer();
            System.out.println("Metro Server stopped");
        }
    }

    public synchronized void stopServer() {
        try {
            serverSocket.close();
            isStopped = true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

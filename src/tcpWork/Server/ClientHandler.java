package tcpWork.Server;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tcpWork.Data.MetroCard;
import tcpWork.Data.MetroCardBank;
import tcpWork.Data.User;
import tcpWork.Operations.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread {
    private ObjectInputStream is = null;
    private ObjectOutputStream os = null;
    private boolean isStopped;
    private final MetroCardBank cardBank;
    private final Socket socket;

    public ClientHandler(MetroCardBank bnk, Socket socket) {
        this.cardBank = bnk;
        this.socket = socket;
        this.isStopped = false;
        try {
            this.is = new ObjectInputStream(socket.getInputStream());
            this.os = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    @Override
    public void run() {
        synchronized (cardBank) {
            System.out.println("Client Handler Started for: " + socket);
            while (!isStopped) {
                Object obj;
                try {
                    obj = is.readObject();
                    processOperation(obj);
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println("Error: " + e);
                }
            }
            try {
                System.out.println("Client Handler Stopped for: " + socket);
                socket.close();
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        }
    }

    //command pattern
    private void processOperation(Object obj) throws
            IOException, ClassNotFoundException {
        if (obj instanceof StopOperation) {
            finish();
        } else if (obj instanceof CardOperation) {
            os.writeObject(((CardOperation) obj).execute(cardBank));
        } else {
            error();
        }
    }

    private void finish() throws IOException {
        isStopped = true;
        os.writeObject("Finish Work " + socket);
        os.flush();
    }

    private void error() throws IOException {
        os.writeObject("Bad Operation");
        os.flush();
    }


}

package tcpWork.Server;

import tcpWork.Data.MetroCardBank;
import tcpWork.Operations.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler extends Thread{
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
        } else if (obj instanceof AddMetroCardOperation) {
            addCard(obj);
        } else if (obj instanceof AddMoneyOperation) {
            addMoney(obj);
        } else if (obj instanceof PayMoneyOperation) {
            payMoney(obj);
        } else if (obj instanceof RemoveCardOperation) {
            removeCard(obj);
        } else if (obj instanceof ShowBalanceOperation) {
            showBalance(obj);
        } else if (obj instanceof FullInfoMetroCardOperation) {
            cardInfo(obj);
        } else {
            error();
        }
    }
    private void finish() throws IOException {
        isStopped = true;
        os.writeObject("Finish Work " + socket);
        os.flush();
    }
    private void addCard(Object obj)
            throws IOException, ClassNotFoundException {
        cardBank.addCard(((AddMetroCardOperation) obj).getCrd());
        os.writeObject("Card Added");
        os.flush();
    }
    private void addMoney(Object obj)
            throws IOException, ClassNotFoundException {
        AddMoneyOperation op = (AddMoneyOperation) obj;
        boolean res = cardBank.addMoney(op.getSerNum(), op.getMoney());
        if (res) {
            os.writeObject("Balance Added");
            os.flush();
        } else {
            os.writeObject("Cannot Balance Added");
            os.flush();
        }
    }
    private void payMoney(Object obj)
            throws IOException, ClassNotFoundException {
        PayMoneyOperation op = (PayMoneyOperation) obj;
        boolean res = cardBank.getMoney(op.getSerNum(), op.getMoney());
        if (res) {
            os.writeObject("Money Payed");
            os.flush();
        } else {
            os.writeObject("Cannot Pay Money");
            os.flush();
        }
    }
    private void removeCard(Object obj)
            throws IOException, ClassNotFoundException {
        RemoveCardOperation op = (RemoveCardOperation) obj;
        boolean res = cardBank.removeCard(op.getSerNum());
        if (res) {
            os.writeObject("Metro Card Succesfully Remove: " + op.getSerNum());
            os.flush();
        } else {
            os.writeObject("Cannot Remove Card" + op.getSerNum());
            os.flush();
        }
    }
    private void showBalance(Object obj)
            throws IOException, ClassNotFoundException {
        ShowBalanceOperation op = (ShowBalanceOperation) obj;
        int ind = cardBank.findMetroCard(op.getSerNum());
        if (ind >= 0) {
            os.writeObject("Card : " + op.getSerNum() + " balance: "
                    + cardBank.getStore().get(ind).getBalance());
            os.flush();
        } else {
            os.writeObject("Cannot Show Balance for Card: " + op.getSerNum());
        }
    }
    private void cardInfo(Object obj) throws IOException, ClassNotFoundException {
        FullInfoMetroCardOperation op = (FullInfoMetroCardOperation) obj;
        int ind = cardBank.findMetroCard(op.getSerNum());
        if (ind >= 0) {
            os.writeObject(cardBank.getStore().get(ind).toString());
            os.flush();
        } else {
            os.writeObject("Cannot Show Balance for Card: " + op.getSerNum());
        }
    }
    private void error() throws IOException {
        os.writeObject("Bad Operation");
        os.flush();
    }
}

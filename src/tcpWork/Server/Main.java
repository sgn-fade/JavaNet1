package tcpWork.Server;

import tcpWork.Data.User;
import tcpWork.Operations.*;

public class Main {
    public static void main(String[] args) {
        MetroServer srv = new MetroServer(7891);
        srv.start();

        Client cl = new Client("localhost", 7891);
        AddMetroCardOperation op = new AddMetroCardOperation();
        op.getCrd().setUsr(new User("Petr", "Petrov", "M", "25.12.1968"));
        op.getCrd().setSerNum("00001");
        op.getCrd().setCollege("KhNU");
        op.getCrd().setBalance(25);
        cl.applyOperation(op);
        cl.finish();
        cl = new Client("localhost", 7891);
        cl.applyOperation(new AddMoneyOperation("00001", 100));
        cl.applyOperation(new ShowBalanceOperation("00001"));
        cl.applyOperation(new PayMoneyOperation("00001", 10));
        cl.applyOperation(new ShowBalanceOperation("00001"));
        cl.applyOperation(new WriteToXMLOperation());
        cl.finish();


    }
}

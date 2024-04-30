package tcpWork.Operations;

import tcpWork.Data.MetroCardBank;

public abstract class CardOperation implements java.io.Serializable {
    public abstract String execute(MetroCardBank bank);
}


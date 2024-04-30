package tcpWork.Operations;

import tcpWork.Data.MetroCard;
import tcpWork.Data.MetroCardBank;

public class AddMetroCardOperation extends CardOperation{
    private MetroCard crd;
    public AddMetroCardOperation() {
        crd = new MetroCard();
    }
    public MetroCard getCrd() {
        return crd;
    }

    @Override
    public String execute(MetroCardBank bank) {
        bank.addCard(crd);
        return "Card added";
    }
}


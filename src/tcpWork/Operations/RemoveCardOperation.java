package tcpWork.Operations;

import tcpWork.Data.MetroCardBank;

public class RemoveCardOperation extends CardOperation{
    private String serNum = null;
    public RemoveCardOperation(String serNum) {
        this.serNum = serNum;
    }
    public RemoveCardOperation() {
    }
    public String getSerNum() {
        return serNum;
    }
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }
    @Override
    public String execute(MetroCardBank bank) {
        if(bank.removeCard(serNum)) return "Card removed";
        return "Card not removed";
    }
}

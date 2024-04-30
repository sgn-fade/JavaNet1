package tcpWork.Operations;

import tcpWork.Data.MetroCardBank;

public class FullInfoMetroCardOperation extends CardOperation{
    private String serNum = null;

    public FullInfoMetroCardOperation(String serNum, double money) {
        this.serNum = serNum;
    }

    public FullInfoMetroCardOperation() {
        this("null", 0.0);
    }

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    @Override
    public String execute(MetroCardBank bank) {
        return bank.toString();
    }
}

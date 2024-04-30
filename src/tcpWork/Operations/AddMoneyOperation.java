package tcpWork.Operations;

import tcpWork.Data.MetroCardBank;

public class AddMoneyOperation extends CardOperation{
    private String serNum;
    private double money;
    public AddMoneyOperation(String serNum, double money) {
        this.serNum = serNum;
        this.money = money;
    }
    public AddMoneyOperation() {
        this("null", 0.0);
    }
    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public String getSerNum() {
        return serNum;
    }
    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }
    @Override
    public String execute(MetroCardBank bank) {
        if(bank.addMoney(serNum, money)) return "Money added";
        return "Cannot add money";
    }
}

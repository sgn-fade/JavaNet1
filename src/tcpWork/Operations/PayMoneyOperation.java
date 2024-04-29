package tcpWork.Operations;

public class PayMoneyOperation extends CardOperation{
    private String serNum;
    private double money;
    public PayMoneyOperation(String serNum, double money) {
        this.serNum = serNum;
        this.money = money;
    }
    public PayMoneyOperation() {
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
}

package tcpWork;

public class MetroCard {
    private String serNum;
    private User usr;
    private String colledge;
    private double balance;

    public String getSerNum() {
        return serNum;
    }

    public void setSerNum(String serNum) {
        this.serNum = serNum;
    }

    public User getUsr() {
        return usr;
    }

    public void setUsr(User usr) {
        this.usr = usr;
    }

    public String getColledge() {
        return colledge;
    }

    public void setColledge(String colledge) {
        this.colledge = colledge;
    }

    public double getBalance() {
        return balance;
    }

    public boolean changeBalance(double money) {
        if(this.balance + money <= 0){
            return false;
        }
        this.balance += money;
        return true;
    }

    public MetroCard(String serNum, User usr, String colledge, double balance) {
        this.serNum = serNum;
        this.usr = usr;
        this.colledge = colledge;
        this.balance = balance;
    }

    public MetroCard() {
    }

    @Override
    public String toString() {
        return "MetroCard{" +
                "serNum='" + serNum + '\'' +
                ", usr=" + usr +
                ", colledge='" + colledge + '\'' +
                ", balance=" + balance +
                '}';
    }

}

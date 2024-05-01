package tcpWork.Data;

import java.io.Serializable;

public class MetroCard implements Serializable {
    private String serNum;
    private User usr;
    private String college;
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

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
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

    public MetroCard(String serNum, User usr, String college, double balance) {
        this.serNum = serNum;
        this.usr = usr;
        this.college = college;
        this.balance = balance;
    }

    public MetroCard() {
    }

    @Override
    public String toString() {
        return "MetroCard{" +
                "serNum='" + serNum + '\'' +
                ", usr=" + usr +
                ", colledge='" + college + '\'' +
                ", balance=" + balance +
                '}';
    }

    public void setBalance(int i) {
        this.balance = i;
    }
}

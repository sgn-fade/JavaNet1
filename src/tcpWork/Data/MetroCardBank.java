package tcpWork.Data;

import java.io.Serializable;
import java.util.ArrayList;

public class MetroCardBank implements Serializable {


    private ArrayList<MetroCard> store;
    public ArrayList<MetroCard> getStore() {
        return store;
    }
    public MetroCardBank() {
        store = new ArrayList<>();
    }
    public int findMetroCard(String serNum) {
        for(MetroCard card : store) {
            if(card.getSerNum().equals(serNum)){
                return store.indexOf(card);
            }
        }
        return -1;
    }

    public int numCards() {
        return store.size();
    }
    public void addCard(MetroCard newCard) {
        store.add(newCard);
    }
    public boolean removeCard(String serNum) {
        return store.remove(findMetroCard(serNum)) != null;
    }
    public boolean addMoney(String serNum, double money){
        return store.get(findMetroCard(serNum)).changeBalance(money);
    }
    public boolean getMoney(String serNum, double money){
        return store.get(findMetroCard(serNum)).changeBalance(-money);
    }
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder("List of MetroCards:");
        for (MetroCard c : store) {
            buf.append("\n\n").append(c);
        }
        return buf.toString();
    }
}

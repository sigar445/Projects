package org.sigar.designPatterns.MVC;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.*;

public class StoreOrder {

    private final Map<String,Double> itemPriceMap;
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);


    public StoreOrder(){
        itemPriceMap = new HashMap<>();
    }

    public List<String> getItemList(){
        return new ArrayList<>(itemPriceMap.keySet());
    }

    public void addItem(String item,double price){
        if (item == null || item.isEmpty() || price < 0) {
            throw new IllegalArgumentException("Item name cannot be null or empty and price must be non-negative.");
        }
        itemPriceMap.put(item,price);
        support.firePropertyChange("itemAdded", null, item + ": " + price);
    }
    public void removeItem(String item){
        if(item == null || !itemPriceMap.containsKey(item))
            throw new IllegalArgumentException("Item name cannot be null and should be present in the itemMap ");
        double oldPrice  = itemPriceMap.remove(item);
        support.firePropertyChange("itemRemoved", item + ": " + oldPrice,null);

    }
    public void updateItemPrice(String item,double price){
        if (item == null || item.isEmpty() || price < 0) {
            throw new IllegalArgumentException("Item name cannot be null or empty and price must be non-negative.");
        }

        double lastPrice = itemPriceMap.get(item);
        itemPriceMap.put(item,price);
        support.firePropertyChange("priceUpdated",item + " : " + lastPrice,item + " : " + price);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    // Removing a listener
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        support.removePropertyChangeListener(listener);
    }
}

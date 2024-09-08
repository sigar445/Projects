package org.sigar.designPatterns.MVC;

public class StoreController {
    private StoreOrder model;
    private StoreView view; 

    public StoreController(StoreOrder storeOrder,StoreView view){
        this.model = storeOrder;
        this.view = view;
    }

    public void addItem(String item,Double price){
        model.addItem(item,price);
    }

    // Remove item without direct display call
    public void removeItem(String item) {
        model.removeItem(item);
    }

    // Update item price without direct display call
    public void updateItemPrice(String item, double price) {
        model.updateItemPrice(item, price);
    }

    // Display items list explicitly when needed
    public void displayItems() {
        view.displayItemList(model.getItemList());
    }
}

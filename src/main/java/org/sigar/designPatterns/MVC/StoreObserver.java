package org.sigar.designPatterns.MVC;

import lombok.AllArgsConstructor;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
@AllArgsConstructor
public class StoreObserver implements PropertyChangeListener {
    private final StoreView view;
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
//        System.out.println("Property " + evt.getPropertyName() + " changed from " + evt.getOldValue() + " to " + evt.getNewValue());
//        @Override
//        public void propertyChange(PropertyChangeEvent evt) {
            String itemChangeMessage = switch (evt.getPropertyName()) {
                case "itemAdded" -> "Item added: " + evt.getNewValue();
                case "itemRemoved" -> "Item removed: " + evt.getOldValue();
                case "priceUpdated" -> "Price updated for " + evt.getNewValue();
                default -> "Unknown change";
            };
            view.displayMessage(itemChangeMessage);
        //    System.out.println(itemChangeMessage);
        }


    public static void main(String[] args) {
        StoreOrder model = new StoreOrder();
        StoreView view = new StoreView();
        StoreController controller = new StoreController(model, view);

        // Create and add observer
        StoreObserver observer = new StoreObserver(view);
        model.addPropertyChangeListener(observer);

        // Example interactions
        controller.addItem("Coffee", 5.99);
        controller.addItem("Tea", 3.49);
        controller.updateItemPrice("Coffee", 6.49);
        controller.removeItem("Tea");

        // Display current items
        controller.displayItems();
    }
}

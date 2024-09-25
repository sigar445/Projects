package org.sigar.designPatterns.StructuralPatterns.Proxy.Warehouse;

import java.util.concurrent.ConcurrentHashMap;

public class Warehouse implements Order {
    private static final ConcurrentHashMap<String,Integer> items = new ConcurrentHashMap<>();

    public void fulfillOrder(String itemName, int quantity) {
        removeItems(itemName,quantity);
    }
    public int removeItems(String itemName,int quantity){
        validateQuantity(quantity);
        Integer newQuantity = items.compute(itemName, (key, currentQuantity) -> {
            int available = currentQuantity == null ? 0 : currentQuantity;
            if (available < quantity) {
                System.out.println("Not able to fulfill order. Current capacity: " + available);
                return currentQuantity;  // Return without change if unable to fulfill
            }
            int remaining = available - quantity;
            System.out.println(remaining == 0 ?
                    "Removed all items of " + itemName :
                    "Removing item " + itemName + ", new count: " + remaining);
            return remaining == 0 ? null : remaining; // Return null to remove the item from the map if count reaches zero
        });
        return newQuantity == null ? 0 : newQuantity;
    }
    public int addItems(String itemName,int quantity){
        validateQuantity(quantity);
        return items.merge(itemName, quantity, Integer::sum);
//        int presentQuantity = items.getOrDefault(itemName,0);
//        items.put(itemName,presentQuantity+quantity);
//        System.out.println("Adding item " + itemName + " increasing item count from "
//        + presentQuantity + " to " + (quantity+presentQuantity));
//        return presentQuantity+quantity;
//        return items.merge(itemName, quantity, (existing, added) -> {
//            int newQuantity = existing + added;
//            System.out.println("Adding item " + itemName + ", increasing count from " + existing + " to " + newQuantity);
//            return newQuantity;
 //       });
    }
//    public int removeItems(String itemName, int quantity) {
//        validateQuantity(quantity);
//        return items.compute(itemName, (key, currentQuantity) -> {
//            if (currentQuantity == null || currentQuantity < quantity) {
//                System.out.println("Unable to fulfill order. Current capacity: " + (currentQuantity == null ? 0 : currentQuantity));
//                return currentQuantity;  // Return without modification
//            }
//            int newQuantity = currentQuantity - quantity;
//            if (newQuantity == 0) {
//                System.out.println("Removed all items of " + itemName);
//                return null;  // Remove the item from the map
//            }
//            System.out.println("Removing item " + itemName + ", new count: " + newQuantity);
//            return newQuantity;  // Update quantity
//        });
//    }

    public void validateQuantity(int quantity){
        if(quantity < 0 || quantity > 100000) throw  new IllegalArgumentException("Quantity is not valid 1-10000");
    }
}

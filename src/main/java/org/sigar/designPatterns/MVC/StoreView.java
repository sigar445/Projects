package org.sigar.designPatterns.MVC;

import java.util.List;

public class StoreView {
    public void displayItemList(List<String> items) {
        System.out.println("Current Items in Store:");
        items.forEach(System.out::println);
    }

    // Method to display change messages
    public void displayMessage(String message) {
        System.out.println(message);
    }
}

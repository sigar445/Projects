package org.sigar.designPatterns.StructuralPatterns.Adapter;

public class TouchScreeDemo {
    public static void main(String[] args) {
        TouchScreen touchScreen = new CoffeeScreenAdapter(new OldCoffeeMachine());
        touchScreen.chooseFirstSelection();
        touchScreen.chooseSecondSelection();
    }
}

package org.sigar.designPatterns.StructuralPatterns.Adapter;

public class CoffeeScreenAdapter implements TouchScreen {
    OldCoffeeMachine coffeeMachine;
    CoffeeScreenAdapter(OldCoffeeMachine machine){
        this.coffeeMachine = machine;
    }

    @Override
    public void chooseFirstSelection() {
        coffeeMachine.firstSelection();
    }

    @Override
    public void chooseSecondSelection() {
        coffeeMachine.secondSelection();
    }
}

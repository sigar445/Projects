package org.sigar.designPatterns.BehaviouralPatterns.State;

public class OutOfStockState extends State{
    @Override
    void insertMoney(VendingMachine machine) {
        System.out.println("Out of Stock Machine");
    }

    @Override
    void ejectMoney(VendingMachine machine) {

        System.out.println("Out of Stock Machine");
    }

    @Override
    void dispense(VendingMachine machine) {
        System.out.println("Out of Stock Machine");
    }
}

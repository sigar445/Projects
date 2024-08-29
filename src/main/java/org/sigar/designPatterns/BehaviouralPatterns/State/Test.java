package org.sigar.designPatterns.BehaviouralPatterns.State;

public class Test {
    public static void testVendingMachine(){
        VendingMachine vendingMachine = new VendingMachine(2);
        vendingMachine.dispense();
        vendingMachine.dispense();

        vendingMachine.insertMoney();
        vendingMachine.insertMoney();

        vendingMachine.dispense();
        vendingMachine.insertMoney();

        vendingMachine.dispense();
        vendingMachine.insertMoney();

        vendingMachine.dispense();


    }

    public static void main(String[] args) {
        testVendingMachine();
    }
}

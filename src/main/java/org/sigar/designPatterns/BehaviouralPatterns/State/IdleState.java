package org.sigar.designPatterns.BehaviouralPatterns.State;

public class IdleState extends State{

    void insertMoney(VendingMachine machine) {
        System.out.println("Dollar inserted");

        machine.setCurrState(machine.HasOneDollar);
    }

    @Override
    void ejectMoney(VendingMachine machine) {
        System.out.println("No money to return");
    }

    @Override
    void dispense(VendingMachine machine) {
        System.out.println("Payment Needed");
    }
}

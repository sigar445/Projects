package org.sigar.designPatterns.BehaviouralPatterns.State;

public class HasOneDollarState extends State{
    @Override
    void insertMoney(VendingMachine machine) {
        System.out.println("Already has one dollar ");
//        machine.ejectMoney();
//        machine.setCurrState(machine.Idle);
    }

    @Override
    void ejectMoney(VendingMachine machine) {
        System.out.println("Returning money");

        machine.ejectMoney();
        machine.setCurrState(machine.Idle);
    }

    @Override
    void dispense(VendingMachine machine) {
        int count = machine.getCount();
        if(count> 0) {
            System.out.println("Product released");
            machine.setCount(count-1);
            machine.setCurrState(machine.Idle);
        }else{
            System.out.println("Machine is Empty");
            machine.setCurrState(machine.OutOfStock);
        }

    }
}

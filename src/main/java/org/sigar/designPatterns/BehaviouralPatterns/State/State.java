package org.sigar.designPatterns.BehaviouralPatterns.State;


public abstract class State {



    abstract void insertMoney(VendingMachine machine);
    abstract void ejectMoney(VendingMachine machine);
    abstract void dispense(VendingMachine machine);
  //  abstract void addProduct(int val){};


}

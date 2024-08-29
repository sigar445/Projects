package org.sigar.designPatterns.BehaviouralPatterns.State;

import lombok.Getter;
import lombok.Setter;

@Setter
public class VendingMachine {
    public  final State Idle;
    public  final State HasOneDollar;
    public  final State OutOfStock;

    private State currState;
    @Getter
    private int count = 0;

    public VendingMachine(int count){
        if(count <= 0){
            throw new IllegalArgumentException("Count has be >=0");
        }
        Idle = new IdleState();
        HasOneDollar = new HasOneDollarState();
        OutOfStock = new OutOfStockState();
        this.count = count;
        setCurrState(Idle);
    }
    void insertMoney() {
        currState.insertMoney(this);
    }

    void ejectMoney() {
        currState.ejectMoney(this);
    }

    void dispense() {
        currState.dispense(this);
    }
}

package org.sigar.designPatterns.BehaviouralPatterns.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Fan {
    private String room;


    public void turnOn() {
        System.out.println(room + " fan is ON");
    }

    public void turnOff() {
        System.out.println(room + " fan is OFF");
    }
}

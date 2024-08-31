package org.sigar.designPatterns.BehaviouralPatterns.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Light {
    private String room;

    public void turnOn(){
        System.out.println(room + " light is on");
    }

    public void turnOff(){
        System.out.println(room + " light is off");
    }
}

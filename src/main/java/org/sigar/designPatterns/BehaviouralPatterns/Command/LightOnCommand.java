package org.sigar.designPatterns.BehaviouralPatterns.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LightOnCommand implements Command{
    Light light;


    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

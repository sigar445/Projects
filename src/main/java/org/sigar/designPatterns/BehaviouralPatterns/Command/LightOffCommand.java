package org.sigar.designPatterns.BehaviouralPatterns.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LightOffCommand implements Command{
    Light light;


    @Override
    public void execute() {
        light.turnOff();
    }

    @Override
    public void undo() {
        light.turnOn();
    }
}

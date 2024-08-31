package org.sigar.designPatterns.BehaviouralPatterns.Command;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FanOffCommand implements Command{
    private Fan fan;
    @Override
    public void execute() {
        fan.turnOff();
    }

    @Override
    public void undo() {
        fan.turnOn();
    }

}

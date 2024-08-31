package org.sigar.designPatterns.BehaviouralPatterns.Command;

import java.security.PublicKey;

public class RemoteControl {

    private Command[] onCommands;
    private Command[] offCommands;
    private Command lastCommand;

    public RemoteControl(int slots){
        onCommands = new Command[slots];
        offCommands  = new Command[slots];
        Command noCommand = new NoCommand();

        for(int ind=0;ind < slots ;ind++){
            onCommands[ind] = noCommand;
            offCommands[ind] = noCommand;
        }
        lastCommand = noCommand;
    }

    public void setCommand(int slot,Command onCommand,Command offCommand){
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }
    public void pressOnButton(int slot){
        onCommands[slot].execute();
        lastCommand = onCommands[slot];
    }
    public void pressOffButton(int slot){
        offCommands[slot].execute();
        lastCommand = offCommands[slot];
    }
    public void pressUndoButton(){
        lastCommand.undo();
    }

}

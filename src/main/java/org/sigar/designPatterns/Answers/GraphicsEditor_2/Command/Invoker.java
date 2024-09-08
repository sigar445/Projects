package org.sigar.designPatterns.Answers.GraphicsEditor_2.Command;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;
public class Invoker {
    private final List<Command> history = new ArrayList<>();

    public int addCommand(Command command){
        history.add(command);
        return history.size()-1;
    }
    public void executeCommand(int index) {
        if (index >= 0 && index < history.size()) {
            history.get(index).execute();
        } else {
            throw new IllegalArgumentException("Index should be between 0 - size");
        }
    }

    public void undoCommand(int index){
        if (index >= 0 && index < history.size()) {
            history.get(index).undo();
        } else {
            throw new IllegalArgumentException("Index should be between 0 - size");
        }
    }

}

package org.sigar.designPatterns.Answers.GraphicsEditor_2.Command;

import lombok.AllArgsConstructor;
import org.sigar.designPatterns.Answers.GraphicsEditor_2.Shape;

@AllArgsConstructor
public class ResizeCommand implements Command {
    private Shape shape;
    @Override
    public void execute() {
        shape.resize();
    }

    @Override
    public void undo() {
        System.out.println("Undo resize of" + shape.getClass().getSimpleName());
    }
}

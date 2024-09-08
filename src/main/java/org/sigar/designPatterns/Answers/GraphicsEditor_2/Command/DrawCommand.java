package org.sigar.designPatterns.Answers.GraphicsEditor_2.Command;

import lombok.AllArgsConstructor;
import org.sigar.designPatterns.Answers.GraphicsEditor_2.Shape;

@AllArgsConstructor
public class DrawCommand implements Command{
    private Shape shape;
    @Override
    public void execute() {
        shape.draw();
    }

    @Override
    public void undo() {
        System.out.println("Undo draw of" + shape.getClass().getSimpleName());
    }
}

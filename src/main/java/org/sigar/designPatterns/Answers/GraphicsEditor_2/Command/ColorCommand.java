package org.sigar.designPatterns.Answers.GraphicsEditor_2.Command;

import lombok.AllArgsConstructor;
import org.sigar.designPatterns.Answers.GraphicsEditor_2.Shape;
@AllArgsConstructor
public class ColorCommand implements Command{
    private Shape shape;
    @Override
    public void execute() {
        shape.color();
    }

    @Override
    public void undo() {
        System.out.println("Undo color of" + shape.getClass().getSimpleName());
    }
}

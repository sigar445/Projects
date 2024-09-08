package org.sigar.designPatterns.Answers.GraphicsEditor_2.Command;

import org.sigar.designPatterns.Answers.GraphicsEditor_2.Circle;
import org.sigar.designPatterns.Answers.GraphicsEditor_2.Rectangle;

import java.util.List;

public class Editor {

    public static void main(String[] args) {
        Invoker invoker = new Invoker();
        Circle circle = new Circle();
        Rectangle rectangle = new Rectangle();
        int drawCircleIndex = invoker.addCommand(new DrawCommand(circle));

        int resizeCircleIndex = invoker.addCommand(new ResizeCommand(circle));
        int drawRectangleIndex = invoker.addCommand(new DrawCommand(rectangle));
        int colorRectangleIndex = invoker.addCommand(new ColorCommand(rectangle));

        invoker.executeCommand(drawCircleIndex);
        invoker.executeCommand(colorRectangleIndex);

        invoker.undoCommand(drawCircleIndex);
        invoker.undoCommand(colorRectangleIndex);
    }
}

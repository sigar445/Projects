package org.sigar.designPatterns.Answers.GraphicsEditor_2.Composite;

import org.sigar.designPatterns.Answers.GraphicsEditor_2.Circle;
import org.sigar.designPatterns.Answers.GraphicsEditor_2.Rectangle;

public class CompositeDemo {
    public static void main(String[] args) {
        CompositeShape editor = new CompositeShape();
        editor.addShape(new Circle());
        editor.addShape(new Rectangle());

        editor.getShape(0).draw();

        editor.getShape(0).resize();
        editor.getShape(1).color();
        editor.getShape(1).draw();
    }
}

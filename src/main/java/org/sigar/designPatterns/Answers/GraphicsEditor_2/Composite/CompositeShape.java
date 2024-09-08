package org.sigar.designPatterns.Answers.GraphicsEditor_2.Composite;

import org.sigar.designPatterns.Answers.GraphicsEditor_2.Shape;

import java.util.ArrayList;
import java.util.List;

public class CompositeShape {
    private final List<Shape> shapes = new ArrayList<>();

    public void addShape(Shape shape){
        shapes.add(shape);
    }
    public void removeShape(Shape shape){
        shapes.remove(shape);
    }

    public Shape getShape(int index){
        if(index >= 0 && index < shapes.size()){
            return shapes.get(index);
        }else
            throw new IllegalArgumentException();
    }

}

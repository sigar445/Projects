package org.sigar.designPatterns.Answers.GraphicsEditor_2;

public class Rectangle extends Shape{
    @Override
    public void resize() {
        System.out.println("Resizing Rectangle");
    }

    @Override
    public void color() {
        System.out.println("Coloring Rectangle");
    }

    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

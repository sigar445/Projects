package org.sigar.designPatterns.Answers.GraphicsEditor_2;

public class Circle extends Shape{
    @Override
    public void resize() {
        System.out.println("Resizing Circle");
    }

    @Override
    public void color() {
        System.out.println("Coloring Circle");
    }

    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

package org.sigar.designPatterns.StructuralPatterns.Decorator.WEBIMP;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class WebPageDecorator implements WebPage{

    protected WebPage page;

    public  void display(){
        this.page.display();
    }
}

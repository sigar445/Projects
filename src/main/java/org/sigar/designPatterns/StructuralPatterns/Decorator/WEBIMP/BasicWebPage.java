package org.sigar.designPatterns.StructuralPatterns.Decorator.WEBIMP;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BasicWebPage implements WebPage {

    private String layout;
    private String html;
    @Override
    public void display() {
        System.out.println("Displaying basic webpage with layout " + layout + " html " + html);
    }
}

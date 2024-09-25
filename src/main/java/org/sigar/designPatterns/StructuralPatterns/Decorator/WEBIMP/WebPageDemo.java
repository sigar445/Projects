package org.sigar.designPatterns.StructuralPatterns.Decorator.WEBIMP;

public class WebPageDemo {

    public static void testWebPage(){

        WebPage page = new BasicWebPage("Standard","JSS");
        page = new AuthorizedWebPage(page);
        page = new AutheticatedWebPage(page);
        page.display();

    }

    public static void main(String[] args) {
        testWebPage();
    }
}

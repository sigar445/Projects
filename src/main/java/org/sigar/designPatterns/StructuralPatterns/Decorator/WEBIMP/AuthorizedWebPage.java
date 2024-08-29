package org.sigar.designPatterns.StructuralPatterns.Decorator.WEBIMP;

public class AuthorizedWebPage extends WebPageDecorator{

    public AuthorizedWebPage(WebPage page){
        super(page);
    }

    public void authorizeWebPage(){
        System.out.println("Authorize web page");
    }

    @Override
    public void display() {
        super.display();
        authorizeWebPage();
    }
}

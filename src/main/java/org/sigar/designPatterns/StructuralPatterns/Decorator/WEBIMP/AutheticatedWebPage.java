package org.sigar.designPatterns.StructuralPatterns.Decorator.WEBIMP;

public class AutheticatedWebPage extends WebPageDecorator{
    public AutheticatedWebPage(WebPage page) {
        super(page);
    }

    @Override
    public void display() {
        super.display();
        authenticateUser();
    }

    public void authenticateUser(){
        System.out.println("Authenticating User");;
    }
}

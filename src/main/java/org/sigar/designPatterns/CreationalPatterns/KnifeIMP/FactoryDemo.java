package org.sigar.designPatterns.CreationalPatterns.KnifeIMP;

public class FactoryDemo {
    public static void main(String[] args) {
        factoryDesignDemo();
    }

    public static void factoryDesignDemo(){

        SteakKnifeFactory steakKnifeFactory  = new SteakKnifeFactory();
        Knife steakNife = steakKnifeFactory.createKnife();
        steakNife.polish();
        steakNife.sharpen();
    }

}

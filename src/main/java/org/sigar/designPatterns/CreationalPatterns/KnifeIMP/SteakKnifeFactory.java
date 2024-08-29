package org.sigar.designPatterns.CreationalPatterns.KnifeIMP;

public class SteakKnifeFactory extends KnifeFactory{

    @Override
    public Knife createKnife() {
        return new SteakNife();
    }
}

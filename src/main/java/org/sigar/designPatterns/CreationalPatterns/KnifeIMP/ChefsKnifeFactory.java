package org.sigar.designPatterns.CreationalPatterns.KnifeIMP;

public class ChefsKnifeFactory extends KnifeFactory {

    @Override
    public Knife createKnife() {
        return new ChefsKnife();
    }
}

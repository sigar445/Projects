package org.sigar.designPatterns.CreationalPatterns.KnifeIMP.FactoryCorrect;

import org.sigar.designPatterns.CreationalPatterns.KnifeIMP.Knife;

public abstract class KnifeStore {
    public Knife orderKnife(String knifeType){
        Knife knife =  createKnife(knifeType);
        knife.polish();
        knife.sharpen();
        return knife;
    }
    abstract Knife createKnife(String knifeType);

}

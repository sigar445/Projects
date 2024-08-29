package org.sigar.designPatterns.CreationalPatterns.KnifeIMP.FactoryCorrect;

import org.sigar.designPatterns.CreationalPatterns.KnifeIMP.ChefsKnife;
import org.sigar.designPatterns.CreationalPatterns.KnifeIMP.Knife;
import org.sigar.designPatterns.CreationalPatterns.KnifeIMP.SteakNife;

public class BudgetKnifeStore extends KnifeStore {
    @Override
    Knife createKnife(String knifeType) {
        if(knifeType.equalsIgnoreCase("steak")){
            return  new SteakNife();
        }
        if(knifeType.equalsIgnoreCase("chef")){
            return  new ChefsKnife();
        }
        return null;
    }
}

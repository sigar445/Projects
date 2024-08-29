package org.sigar.designPatterns.CreationalPatterns.KnifeIMP.FactoryCorrect;

import org.sigar.designPatterns.CreationalPatterns.KnifeIMP.Knife;

public class TestFactory {
    public static void main(String[] args) {
        KnifeStore store = new BudgetKnifeStore();
        Knife knife = store.orderKnife("steak");
    }
}

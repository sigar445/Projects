package org.sigar.designPatterns.StructuralPatterns.Adapter.WebADP;

public class WebService {

    public int request(int val){
        return (int) (Math.random()*val);
    }

}

package org.sigar.designPatterns.CreationalPatterns.KnifeIMP;

public class KnifeFactory {

    public Knife createKnife(){
        return null;
    };
    public Knife orderKnife(String knifeType){
        Knife knife=null;

        if(knifeType.equalsIgnoreCase("steakKnife")){
            knife = new SteakNife();
        } else if (knifeType.equalsIgnoreCase("chefsknife")) {
            knife = new ChefsKnife();
        }
//        assert knife != null;
//        knife.polish();
//        knife.sharpen();

        return knife;

    }

}

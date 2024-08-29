package org.sigar.designPatterns.CreationalPatterns.Singleton;
//Commented code is correct, commented for checking purposes
public class SingletonEx {
    private SingletonEx() {
    }
    private static SingletonEx instance=null;
    //private static volatile SingletonEx  instance = null; correct Imp

    public static SingletonEx getInstance() {
       // if (instance == null) {
        //    synchronized (SingletonEx.class) {
                if (instance == null) {
                    instance = new SingletonEx();
                    System.out.println("Singleton instance created");
                }
       //     }
       // }
        return instance;
    }
}



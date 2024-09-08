package org.sigar.designPatterns.CreationalPatterns.Singleton;

public enum Singleton {
    INSTANCE(34);
    private final int val;

     Singleton(int val) {this.val = val;}
    public void displayVal(){
       System.out.println(INSTANCE.val);
    }
    private volatile SomeResource resource;

    public SomeResource getResource() {
        if (resource == null) {
            synchronized (this) {
                if (resource == null) {
                    resource = new SomeResource();
                }
            }
        }
        return resource;
    }
}

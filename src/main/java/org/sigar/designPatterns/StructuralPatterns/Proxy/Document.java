package org.sigar.designPatterns.StructuralPatterns.Proxy;

public interface Document {
    public enum UserRole {ADMIN,GUEST};
    public void read();
    public void write();
}

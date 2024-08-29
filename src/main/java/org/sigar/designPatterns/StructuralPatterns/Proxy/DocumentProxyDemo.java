package org.sigar.designPatterns.StructuralPatterns.Proxy;

public class DocumentProxyDemo {
    public static void checkProxy(){

        RealDocument original = new RealDocument("LOGFILE");
        Document admin = new DocumentProxy(original, Document.UserRole.ADMIN);
        admin.read();
        admin.write();

        Document guest = new DocumentProxy(original, Document.UserRole.GUEST);
        guest.read();
        guest.write();

    }

    public static void main(String[] args) {
        checkProxy();
    }
}

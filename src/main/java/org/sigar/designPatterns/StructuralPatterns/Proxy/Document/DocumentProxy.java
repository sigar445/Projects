package org.sigar.designPatterns.StructuralPatterns.Proxy.Document;

import lombok.AllArgsConstructor;
import org.sigar.designPatterns.StructuralPatterns.Proxy.RealDocument;

@AllArgsConstructor
public class DocumentProxy implements Document{

    RealDocument realDocument;
    UserRole role;
    @Override
    public void write() {
        if(role == UserRole.ADMIN)
            realDocument.write();
        else
            System.out.println("User is not permitted to write");
    }

    @Override
    public void read() {
        realDocument.read();
    }
}

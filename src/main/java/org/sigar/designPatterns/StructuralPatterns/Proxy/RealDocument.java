package org.sigar.designPatterns.StructuralPatterns.Proxy;

import lombok.AllArgsConstructor;
import org.sigar.designPatterns.StructuralPatterns.Proxy.Document.Document;

@AllArgsConstructor
public class RealDocument implements Document {

    String fileName;
    @Override
    public void read() {
        System.out.println("Reading Documet " + fileName );
    }

    @Override
    public void write() {
        System.out.println("Writing Document "+ fileName);
    }
}

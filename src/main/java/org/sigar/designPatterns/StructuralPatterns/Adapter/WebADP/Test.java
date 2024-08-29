package org.sigar.designPatterns.StructuralPatterns.Adapter.WebADP;

public class Test {
    public static void main(String[] args) {
        WebServiceClient service = new WebServiceClient();
        service.makeObject();
        System.out.println(
                service.request("Hello Are u listening"));

    }
}

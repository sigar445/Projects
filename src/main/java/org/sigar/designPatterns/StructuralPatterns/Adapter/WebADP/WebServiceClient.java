package org.sigar.designPatterns.StructuralPatterns.Adapter.WebADP;

public class WebServiceClient implements WebRequester{

    WebRequester requester;

    //WebRequester requester = new WebServiceAdapter(new WebService());

    public void makeObject(){
        requester = new WebServiceAdapter(new WebService());
    }
    public int request(String message) {
        return requester.request(message);
    }

}

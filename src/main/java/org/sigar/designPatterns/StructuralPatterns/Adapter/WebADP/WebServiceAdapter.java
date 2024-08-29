package org.sigar.designPatterns.StructuralPatterns.Adapter.WebADP;

public class WebServiceAdapter implements WebRequester {

    WebService webService;
    public WebServiceAdapter(WebService service){
        this.webService = service;
    }

    @Override
    public int request(String message) {
        return webService.request(message.length());
    }

}

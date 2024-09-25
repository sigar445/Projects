package org.sigarLLD.URLShortnerOldCode;

import java.util.HashMap;
import java.util.Map;

public class Client {

    // Can have more attributes and store all urls associated
    private String clientID;
 //   ArrayList<UrlShortened> urlList;

 //   public void addUrl(String url)
    //key  LongURL
    private Map<String,URLValues> clientURLMap;

    public Client(String clientID) {
        this.clientID = clientID;
        clientURLMap = new HashMap<>();
    }

    public URLValues getClientURLValuesFromID(String clientID){
        return clientURLMap.get(clientID);
    }

}


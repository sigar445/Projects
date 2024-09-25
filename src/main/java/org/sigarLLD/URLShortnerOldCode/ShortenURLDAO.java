package org.sigarLLD.URLShortnerOldCode;

import java.util.HashMap;
import java.util.Map;

public class ShortenURLDAO {
    // key Short Url
    static Map<String,URLValues> URLMAP = null;

    public ShortenURLDAO() {
        if(URLMAP == null){
            //Use concurrent MAP
            URLMAP = new HashMap<String,URLValues>();
        }
    }

    public void addURL(String shortURL, String longURL, String clientID) {
        URLMAP.put(shortURL,new URLValues(shortURL,longURL,clientID));
    }


}

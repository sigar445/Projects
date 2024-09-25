package org.sigarLLD.URLShortnerOldCode;

import java.util.HashMap;
import java.util.Map;

public class ClientCollectionDAO {
    // ClientId ,client
    Map<String,Client> userBaseMap;
    ClientCollectionDAO(){
        //Could be made same as ShortenURLDAO
        userBaseMap = new HashMap<>();
    }

    synchronized public boolean isValidClient(String clientId){
        return userBaseMap.containsKey(clientId);
    }

//    synchronized public String checkDuplicateShortenRequest(String longURL,String clientID){
//        if(isValidClient(clientID)){
//            Client client = userBaseMap.get(clientID);
//
//        }
//    }

}

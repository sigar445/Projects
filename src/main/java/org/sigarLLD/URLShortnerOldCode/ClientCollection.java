package org.sigarLLD.URLShortnerOldCode;

public class ClientCollection {

    ClientCollectionDAO clientCollectionDAO;
    ClientCollection(){
        clientCollectionDAO = new ClientCollectionDAO();
    }

    public boolean isValidClient(String clientId){
        return clientCollectionDAO.isValidClient(clientId);
    }

//    public String duplicateShortenRequest(String longURL,String clientID){
//        return clientCollectionDAO.checkDuplicateShortenRequest(longURL,clientID);
//    }
}

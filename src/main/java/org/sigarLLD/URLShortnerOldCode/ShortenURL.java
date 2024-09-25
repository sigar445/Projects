package org.sigarLLD.URLShortnerOldCode;

public class ShortenURL implements URL{

    KeyGenerator keyGenerator;
    ClientCollection clientCollection;
    ShortenURLDAO shortenURLDAO;

    public ShortenURL(){
        keyGenerator = new ShortenURLKeyGenerator(1000,8);
        clientCollection = new ClientCollection();
        shortenURLDAO = new ShortenURLDAO();
    }

    @Override
    public String getShortenedURL(String longURL, String clientID) {
        if(!clientCollection.isValidClient(clientID)){
            return ClientErrors.CLIENT_NOT_VALID_ERROR;
        }
        //Can check Duplicates from here
//        if(clientCollection.checkDuplicateRequest(longURL,clientID)){
//
//        }
        String shortURL =  keyGenerator.getKey(longURL,clientID);

        shortenURLDAO.addURL(shortURL,longURL,clientID);

        return shortURL;
    }
}

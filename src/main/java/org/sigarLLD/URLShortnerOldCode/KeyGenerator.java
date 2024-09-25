package org.sigarLLD.URLShortnerOldCode;

public interface KeyGenerator {

    public void generateKeys();
    public String getKey(String longURL,String clientId);
}

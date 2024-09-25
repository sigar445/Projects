package org.sigarLLD.URLShortnerOldCode;

import java.util.ArrayList;

public class ShortenURLKeyGenerator implements KeyGenerator{

    ArrayList<String> keys;
    Integer keysBucketSize;
    Integer keyLength;
    Integer usedKeyInd;


    ShortenURLKeyGenerator (int keysBucketSize,int keyLength){
        this.keysBucketSize = keysBucketSize;
        this.keyLength  = keyLength;
        usedKeyInd = 0;
    }
    @Override
    synchronized public String getKey(String longURL,String clientId) {
        if(keysBucketSize * 0.75 < usedKeyInd){
            generateKeys();
            usedKeyInd = 0;
        }
        return keys.get(usedKeyInd++);
    }
    @Override
    public void generateKeys() {

    }
}

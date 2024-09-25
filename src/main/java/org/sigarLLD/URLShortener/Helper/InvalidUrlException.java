package org.sigarLLD.URLShortener.Helper;

public class InvalidUrlException extends RuntimeException{
    public InvalidUrlException(String message){
        super(message);
    }

}

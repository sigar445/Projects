package org.sigarLLD.ConcurrentURLShortener.Helper;

public class InvalidUrlException extends RuntimeException{
    public InvalidUrlException(String message){
        super(message);
    }

}

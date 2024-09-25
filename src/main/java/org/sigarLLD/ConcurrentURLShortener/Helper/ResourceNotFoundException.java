package org.sigarLLD.ConcurrentURLShortener.Helper;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

package org.sigarLLD.ConcurrentURLShortener.Service;

public interface TinyURLGenerator {
    String generateTinyURL(String originalURL) throws Exception;
}

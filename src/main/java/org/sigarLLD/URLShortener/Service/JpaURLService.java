package org.sigarLLD.URLShortener.Service;


import org.sigarLLD.URLShortener.Helper.InvalidUrlException;
import org.sigarLLD.URLShortener.Helper.ResourceNotFoundException;
import org.sigarLLD.URLShortener.Helper.URLConstants;
import org.sigarLLD.URLShortener.Model.UrlShortened;
import org.sigarLLD.URLShortener.Repo.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Service
public class JpaURLService implements URLService{
    private  final UrlRepo urlRepository;
    @Autowired
    JpaURLService(UrlRepo urlRepo){
        this.urlRepository = urlRepo;
    }

    @Override
    public UrlShortened getOriginalUrl(String tinyUrl) {
        UrlShortened urlShortened= urlRepository.findByTinyUrl(tinyUrl);
        if(urlShortened == null){
            throw new ResourceNotFoundException("URL not found for tinyURL: " + tinyUrl);
        }
//
        urlShortened.incrementHits();
        return urlRepository.save(urlShortened);
        //return urlShortened;
    }

    @Override
    public UrlShortened createShortenedUrl(String originalUrl) {
        if(!isUrlValid(originalUrl))
            throw new InvalidUrlException("Invalid URL: " + originalUrl);
        UrlShortened existingUrlShortened = urlRepository.findByUrl(originalUrl);
        if (existingUrlShortened != null) {
            return existingUrlShortened; // Return existing TinyURL
        }
        return generateShortUrl(originalUrl);
    }

    @Override
    public boolean updateUrlAnalytics(String tinyUrl) {
        return false;
    }

    private UrlShortened generateShortUrl(String originalUrl) {
        String tinyUrl = generateTinyUrl();
        UrlShortened newUrlShortened = new UrlShortened(originalUrl,tinyUrl);
        newUrlShortened = urlRepository.save(newUrlShortened);
        return newUrlShortened; // Return the newly generated TinyURL
    }

    @Override
    public boolean isUrlValid(String longUrl) {
        try {
            longUrl = longUrl.trim(); // Trim whitespace
            URI uri = new URI(longUrl); // Create URI first
            new java.net.URL(uri.toString()); // Convert to URL
            return true;
        } catch (MalformedURLException | URISyntaxException e)  {
            System.out.println("Invalid URL exception: " + e.getMessage());
            return false;
        }
    }

    @Override
    public UrlShortened saveUrl(UrlShortened urlShortened) {
       return urlRepository.save(urlShortened);
    }
    private String generateTinyUrl() {
        return UUID.randomUUID().toString().substring(0, 8); // Generate random 8-character string
    }

}

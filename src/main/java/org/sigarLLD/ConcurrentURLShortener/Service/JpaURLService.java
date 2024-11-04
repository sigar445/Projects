package org.sigarLLD.ConcurrentURLShortener.Service;


import jakarta.persistence.OptimisticLockException;
import org.sigarLLD.ConcurrentURLShortener.Helper.CustomOptimisticLockException;
import org.sigarLLD.ConcurrentURLShortener.Helper.InvalidUrlException;
import org.sigarLLD.ConcurrentURLShortener.Helper.ResourceNotFoundException;
import org.sigarLLD.ConcurrentURLShortener.Model.UrlShortened;
import org.sigarLLD.ConcurrentURLShortener.Repo.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class JpaURLService implements URLService {
    private  final UrlRepo urlRepository;
    private final TinyURLGenerator tinyUrlGenerator = new TinyURLGeneratorWithZookeeper();
    @Autowired
    JpaURLService(UrlRepo urlRepo) throws Exception{

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
        try {
            return generateShortUrl(originalUrl);
        }catch (RuntimeException e){
            throw new InvalidUrlException(e.toString());
        }
    }

    @Override
    public boolean updateUrlAnalytics(String tinyUrl) {
        return false;
    }

    private UrlShortened generateShortUrl(String originalUrl) throws RuntimeException {
        try {
            String tinyUrl = tinyUrlGenerator.generateTinyURL(originalUrl);
            UrlShortened newUrlShortened = new UrlShortened(originalUrl, tinyUrl);
            newUrlShortened = saveUrl(newUrlShortened);
            return newUrlShortened; // Return the newly generated TinyURL
        }catch (Exception exception){
            throw new RuntimeException("Failed to generate tiny URL from Zookeeper", exception);
        }
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
    public UrlShortened saveUrl(UrlShortened urlShortened) throws CustomOptimisticLockException {

            try {
                return urlRepository.save(urlShortened);
            } catch (OptimisticLockException e) {
                // Handle the exception, e.g., retry the operation or notify the user
                throw new CustomOptimisticLockException("Update conflict occurred for URL: " + urlShortened.getUrl(), e);
            }
        }


//    private String generateTinyUrl() {
//        return UUID.randomUUID().toString().substring(0, 8); // Generate random 8-character string
//    }

}

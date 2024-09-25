package org.sigarLLD.ConcurrentURLShortener.Service;

import org.sigarLLD.ConcurrentURLShortener.Helper.CustomOptimisticLockException;
import org.sigarLLD.ConcurrentURLShortener.Model.UrlShortened;

public interface URLService {

 //   public UrlShortened fetchURL(String tinyURL);
//    public UrlShortened fetchUrlShortened(String url);
//    public boolean updateAnalytics(String url);
//    public UrlShortened createTinyUrl(String longURL);
//    public boolean isValidURL(String longURL);
//    //  public UrlShortened storeURL(UrlShortened url);
    /**
     * Fetches the original long URL from the shortened URL (tiny URL).
     *
     * @param tinyUrl the shortened URL
     * @return the corresponding UrlShortened entity
     */
    public UrlShortened getOriginalUrl(String tinyUrl);

    /**
     * Fetches the shortened URL information using the original long URL.
     *
     * @param longUrl the original URL
     * @return the corresponding UrlShortened entity
     */
    public UrlShortened createShortenedUrl(String longUrl);

    /**
     * Updates the analytics (e.g., number of hits) for a given shortened URL.
     *
     * @param tinyUrl the shortened URL
     * @return true if the analytics were updated successfully, false otherwise
     */
    public boolean updateUrlAnalytics(String tinyUrl);

    /**
     * Creates a shortened URL (tiny URL) for a given long URL.
     *
     * @param longUrl the original URL to shorten
     * @return the newly created UrlShortened entity
     */
   // public UrlShortened generateShortUrl(String longUrl);

    /**
     * Validates if the provided long URL is well-formed and valid.
     *
     * @param longUrl the URL to validate
     * @return true if the URL is valid, false otherwise
     */
    public boolean isUrlValid(String longUrl);

    /**
     * Stores a UrlShortened entity, typically used for persistence.
     *
     * @param urlShortened the UrlShortened entity to store
     * @return the stored UrlShortened entity
     */
    public UrlShortened saveUrl(UrlShortened urlShortened) throws CustomOptimisticLockException;

}

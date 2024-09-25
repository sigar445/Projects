package org.sigarLLD.URLShortener.Controller;

import org.sigarLLD.URLShortener.Helper.Errors;
import org.sigarLLD.URLShortener.Helper.InvalidUrlException;
import org.sigarLLD.URLShortener.Helper.URLConstants;
import org.sigarLLD.URLShortener.Model.UrlRequest;
import org.sigarLLD.URLShortener.Model.UrlShortened;
import org.sigarLLD.URLShortener.Service.JpaURLService;
import org.sigarLLD.URLShortener.Service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shortener")
public class URLController {
    private final  URLService  urlService;
    @Autowired
    URLController(URLService service){
        this.urlService = service;
    }
    @PostMapping("/create")
    public ResponseEntity<UrlShortened> storeURL(@RequestBody UrlRequest urlRequest){

//        UrlShortened urlShortened = urlService.createShortenedUrl(urlRequest.getUrl());
//        System.out.println("SAVED " + urlShortened.getUrl() + " tinyUrl " + urlShortened.getTinyUrl());
//        return urlShortened;

        try {
            UrlShortened urlShortened = urlService.createShortenedUrl(urlRequest.getUrl());
            return new ResponseEntity<>(urlShortened, HttpStatus.CREATED);
        } catch (InvalidUrlException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/{tinyURL}")
    public ResponseEntity<String> fetchURL(@PathVariable String tinyURL) {
//        String url = urlService.getOriginalUrl(tinyURL).getUrl();
//        System.out.println("Redirecting for " + tinyURL + "  to  " + url);
//        return url;

        try {
            UrlShortened urlShortened = urlService.getOriginalUrl(tinyURL);
            if (urlShortened == null) {
                return new ResponseEntity<>("URL not found", HttpStatus.NOT_FOUND);
            }
            System.out.println("Redirecting for " + tinyURL + "  to  " + urlShortened.getUrl());
            // urlService.updateUrlAnalytics(tinyURL); // Increment hit counter
            return new ResponseEntity<>(urlShortened.getUrl(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

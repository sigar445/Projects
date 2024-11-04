package org.sigarLLD.ConcurrentURLShortener.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
public class TinyURLGeneratorWithCounter {
        private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        private static final int BASE = BASE62.length();
        private AtomicLong counter = new AtomicLong(1); // Thread-safe counter
        private Map<String, String> urlMap = new ConcurrentHashMap<>(); // Thread-safe map
        private Map<String, String> reverseMap = new ConcurrentHashMap<>(); // Thread-safe reverse map

        // Method to encode ID to a Base62 short UrlShortened
        public String idToShortURL(long id) {
            StringBuilder shortURL = new StringBuilder();
            while (id > 0) {
                shortURL.append(BASE62.charAt((int) (id % BASE)));
                id /= BASE;
            }
            return shortURL.reverse().toString();
        }

        // Method to generate TinyURL (Thread-safe)
        public String generateTinyURL(String originalURL) {
            // Check if TinyURL already exists for the original UrlShortened
            if (reverseMap.containsKey(originalURL)) {
                return reverseMap.get(originalURL); // Return existing TinyURL if already generated
            }

            // Atomically increment the counter and generate a new unique TinyURL
            long id = counter.getAndIncrement();
            String tinyURL = idToShortURL(id);

            // Store the mappings in thread-safe maps
            urlMap.put(tinyURL, originalURL);
            reverseMap.put(originalURL, tinyURL);

            return tinyURL;
        }

        // Method to retrieve original UrlShortened from TinyURL (Thread-safe)
        public String getOriginalURL(String tinyURL) {
            return urlMap.get(tinyURL);
        }

}

package org.sigarLLD.ConcurrentURLShortener.Service;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;
@Service
public class TinyURLGeneratorWithZookeeper implements TinyURLGenerator {
    private static final String BASE62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = BASE62.length();
    private static final int BATCH_SIZE = 1000000; // Each server gets a batch of 1 million unique IDs

    private AtomicLong counter; // Current counter for this instance
    private long maxCounter; // Max counter for this instance

//    private final UrlRepo urlRepo;
    private final CuratorFramework client;
    private final InterProcessMutex lock;

    @Autowired
    public TinyURLGeneratorWithZookeeper() throws Exception {

//    public TinyURLGeneratorWithZookeeper(UrlRepo urlRepo) throws Exception {
//        this.urlRepo = urlRepo;

        // Connect to Zookeeper using Curator
        this.client = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(1000, 3));
        this.client.start();

        // Lock for controlling the ID range allocation
        this.lock = new InterProcessMutex(client, "/url-shortener/locks");

        // Fetch an initial range of IDs from Zookeeper
        allocateNewRange();
    }

    private void allocateNewRange() throws Exception {
        lock.acquire();
        try {
            byte[] currentValue = client.getData().forPath("/url-shortener/counter");
            long globalCounter = currentValue == null ? 1 : Long.parseLong(new String(currentValue));

            counter = new AtomicLong(globalCounter);
            maxCounter = globalCounter + BATCH_SIZE;

            client.setData().forPath("/url-shortener/counter", String.valueOf(maxCounter).getBytes());
        } finally {
            lock.release();
        }
    }
    private  String idToShortURL(long id) {
        StringBuilder shortURL = new StringBuilder();
        while (id > 0) {
            shortURL.append(BASE62.charAt((int) (id % BASE)));
            id /= BASE;
        }
        return shortURL.reverse().toString();
    }
    public synchronized String generateTinyURL(String originalURL) throws Exception {
      //  URLMapping existingMapping = urlMappingRepository.findByOriginalUrl(originalURL);
//        if (existingMapping != null) {
//            return existingMapping.getTinyUrl();
//        }

        if (counter.get() >= maxCounter) {
            allocateNewRange();
        }

        long id = counter.getAndIncrement();
        String tinyURL = idToShortURL(id);

        // Store the new mapping in the PostgreSQL database
//        URLMapping newMapping = new URLMapping();
//        newMapping.setTinyUrl(tinyURL);
//        newMapping.setOriginalUrl(originalURL);
//        urlMappingRepository.save(newMapping);

        return tinyURL;
    }
    public void close() {
        client.close();
    }

}

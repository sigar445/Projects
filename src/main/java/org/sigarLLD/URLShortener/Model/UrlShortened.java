package org.sigarLLD.URLShortener.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "urls", indexes = {
        @Index(name = "idx_tinyUrl", columnList = "tinyUrl"),
        @Index(name = "idx_url", columnList = "url")
})
@Getter
@NoArgsConstructor
public class UrlShortened {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    @Setter
    private  String tinyUrl;
    @Column(nullable = false, unique = true)
    private String url;
    private LocalDateTime created_on;
    private LocalDateTime expiry;
    private Integer hits;

    public UrlShortened(String url, String tinyUrl){
        this.url = url;
        this.tinyUrl = tinyUrl;
        created_on = LocalDateTime.now();
        expiry = created_on.plusDays(30);
        hits = 0;
    }
    public void incrementHits() {
        System.out.println("Incrementing hits for " + url);
        this.hits += 1;
    }

}

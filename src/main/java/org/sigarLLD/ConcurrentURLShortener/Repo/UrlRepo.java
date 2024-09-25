package org.sigarLLD.ConcurrentURLShortener.Repo;

import org.sigarLLD.ConcurrentURLShortener.Model.UrlShortened;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlRepo extends JpaRepository<UrlShortened,Long> {
    UrlShortened findByUrl(String url);
    UrlShortened findByTinyUrl(String tinyUrl);
}

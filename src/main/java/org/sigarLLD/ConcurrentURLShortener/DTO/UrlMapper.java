package org.sigarLLD.ConcurrentURLShortener.DTO;

import org.sigarLLD.ConcurrentURLShortener.Model.UrlShortened;

//@Mapper(componentModel = "spring")
public interface UrlMapper {
    UrlResponse toResponse(UrlShortened urlShortened);
}

package org.sigarLLD.ConcurrentURLShortener.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UrlRequest {
   // @NotEmpty(message = "URL cannot be empty")
    private String url;
}

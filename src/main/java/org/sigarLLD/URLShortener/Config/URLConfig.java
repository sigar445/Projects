package org.sigarLLD.URLShortener.Config;

import org.sigarLLD.URLShortener.Service.JpaURLService;
import org.sigarLLD.URLShortener.Service.URLService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
@Configuration
public class URLConfig {

}
//        @Bean
//        @Profile("sql") // Active when the 'sql' profile is active
//        public URLService sqlUrlService() {
//            return new SQLURLService();
//        }




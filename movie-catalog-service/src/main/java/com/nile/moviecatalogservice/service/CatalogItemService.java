package com.nile.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.nile.moviecatalogservice.CatalogResource.CatalogItem;
import com.nile.moviecatalogservice.CatalogResource.Movie;
import com.nile.moviecatalogservice.CatalogResource.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CatalogItemService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackCatalogItem" ,
    commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds" , value = "2000"),
            // looking last 5 request
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold" , value = "5"),
            // if have error or timeout in 2 , it will break
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage" , value = "50"),
            // wait for 5 second and then fix up again
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds" , value = "5000"),
    },
            // create threspool to separate
    threadPoolKey = "movieInfoPool",
            threadPoolProperties = {
            @HystrixProperty(name = "coreSize" , value = "20"),
            @HystrixProperty(name = "maxQueueSize" , value = "10")
            }
    )
    public CatalogItem getCatalogItem(Rating rating) {
        Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getId(), Movie.class);
        return new CatalogItem(movie.getOriginalTitle(), movie.getDesciption(), rating.getRating());
    }

    public CatalogItem getFallbackCatalogItem(Rating rating) {
        return new CatalogItem("Movie name not found" , "desc" , 0);
    }
}

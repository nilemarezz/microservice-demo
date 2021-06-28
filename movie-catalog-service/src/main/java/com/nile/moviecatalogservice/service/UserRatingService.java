package com.nile.moviecatalogservice.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nile.moviecatalogservice.CatalogResource.Rating;
import com.nile.moviecatalogservice.CatalogResource.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class UserRatingService {
    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "getFallbackUserRating")
    public UserRating getUserRating(String userID) {
        return restTemplate.getForObject("http://rating-data-service/rating/user/" + userID, UserRating.class);
    }

    public UserRating getFallbackUserRating(String userID) {
        Rating rating = new Rating(0, 0);
        UserRating userRating = new UserRating();
        userRating.setUserRating(Arrays.asList(rating));
        return userRating;
    }
}

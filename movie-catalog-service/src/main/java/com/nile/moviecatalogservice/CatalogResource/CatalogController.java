package com.nile.moviecatalogservice.CatalogResource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/catalog")
public class CatalogController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/{userID}")
    public List<CatalogItem> getCatalogs(@PathVariable(name = "userID") String userID){

        // get all reated movie ID

        List<CatalogItem> catalogItems = new ArrayList<>();
        UserRating ratings = restTemplate.getForObject("http://rating-data-service/rating/user/"+userID , UserRating.class);
        for (Rating rating : ratings.getUserRating()){
            Movie movie = restTemplate.getForObject("http://movie-info-service/movie/" + rating.getId() , Movie.class);
            catalogItems.add(new CatalogItem(movie.getName() , "des" , rating.getRating()));
        }
        return catalogItems;
        // foe each movie id , call movie info

    }
}

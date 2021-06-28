package com.nile.moviecatalogservice.CatalogResource;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.nile.moviecatalogservice.service.CatalogItemService;
import com.nile.moviecatalogservice.service.UserRatingService;
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
    CatalogItemService catalogItemService;

    @Autowired
    UserRatingService userRatingService;

    @GetMapping("/{userID}")
    public List<CatalogItem> getCatalogs(@PathVariable(name = "userID") String userID) {

        // get all reated movie ID

        List<CatalogItem> catalogItems = new ArrayList<>();
        UserRating ratings = userRatingService.getUserRating(userID);
        for (Rating rating : ratings.getUserRating()) {
            CatalogItem item = catalogItemService.getCatalogItem(rating);
            catalogItems.add(new CatalogItem(item.getName() , item.getDesc() , rating.getRating()));
        }
        return catalogItems;
        // foe each movie id , call movie info

    }




}

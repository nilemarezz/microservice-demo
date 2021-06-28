package com.nile.ratingdataservice.RatingResource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/rating")
public class RatingController {
    @GetMapping("/{movieID}")
    public Rating getRating(@PathVariable(name = "movieID") String movieId){
        return new Rating(Integer.parseInt(movieId) , 3);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<UserRating> getUserRating(@PathVariable(name = "userId") String userId){
        UserRating userRating = new UserRating();
        List<Rating> ratings =  Arrays.asList(
                new Rating(123 , 4),
                new Rating(3 , 3)
        );
        userRating.setUserRating(ratings);
        return ResponseEntity.ok(userRating);
    }

}

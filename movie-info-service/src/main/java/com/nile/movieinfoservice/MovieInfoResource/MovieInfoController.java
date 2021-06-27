package com.nile.movieinfoservice.MovieInfoResource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movie")
public class MovieInfoController {

    @GetMapping("/{movieId}")
    public Movie getMovie(@PathVariable(name = "movieId") String id){
        return new Movie(Integer.parseInt(id) , "test");
    }
}

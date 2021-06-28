package com.nile.movieinfoservice.MovieInfoResource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movie")
public class MovieInfoController {

    @Autowired
    RestTemplate restTemplate;

    @Value("${key.api_key_move}")
    private String apiKey;

    @GetMapping("/{movieId}")
    public ResponseEntity<Movie> getMovie(@PathVariable(name = "movieId") String id){
        Movie movie =  restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+id + "?api_key="+apiKey , Movie.class);
        return ResponseEntity.ok(movie);
    }
}

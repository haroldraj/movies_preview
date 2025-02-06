package dev.haroldraja.movies_preview.controller;

import dev.haroldraja.movies_preview.model.MovieDTO;
import dev.haroldraja.movies_preview.service.MovieService;
import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/v1/movies")
public class MovieController {
    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("")
    public ResponseEntity<List<MovieDTO>> allMovies(){
        return new ResponseEntity<>(movieService.getAllMovies(), HttpStatus.OK);
    }

    @GetMapping("/{imdbId}")
    public ResponseEntity<Optional<MovieDTO>> getMovieByImdbId(@PathVariable String imdbId){
        return  new ResponseEntity<>(movieService.getMovieByImdbId(imdbId), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Optional<MovieDTO>> getMovieById(@RequestParam String id){
        return  new ResponseEntity<>(movieService.getMovieById(id), HttpStatus.OK);
    }

}

package dev.haroldraja.movies_preview.service;

import dev.haroldraja.movies_preview.entity.Movie;
import dev.haroldraja.movies_preview.model.MovieDTO;
import dev.haroldraja.movies_preview.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    private MovieDTO toDTO(Movie movie){
        return new MovieDTO(
                movie.getImdbId(),
                movie.getTitle(),
                movie.getReleaseDate(),
                movie.getTrailerLink(),
                movie.getPoster(),
                movie.getGenres(),
                movie.getBackdrops(),
                movie.getReviewIds()
        );
    }

    public List<MovieDTO> getAllMovies(){
        return movieRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public Optional<MovieDTO> getMovieById(String id){
        return movieRepository.findById(id)
                .stream()
                .map(this::toDTO)
                .findFirst();
    }

    public Optional<MovieDTO> getMovieByImdbId(String id){
        return movieRepository.findByImdbId(id)
                .stream()
                .map(this::toDTO)
                .findFirst();
    }

    public boolean existsByImdbId(String imdbId){
        boolean exists = movieRepository.existsByImdbId(imdbId);
        if(exists){
            return true;
        }
        throw new IllegalArgumentException("Movie with imdbId: " + imdbId + " does not exist");
    }
}

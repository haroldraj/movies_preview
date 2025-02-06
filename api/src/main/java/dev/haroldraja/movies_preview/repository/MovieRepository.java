package dev.haroldraja.movies_preview.repository;

import dev.haroldraja.movies_preview.entity.Movie;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends MongoRepository<Movie, String> {
    Optional<Movie> findByImdbId(String imdbId);
    boolean existsByImdbId(String imdbId);
}

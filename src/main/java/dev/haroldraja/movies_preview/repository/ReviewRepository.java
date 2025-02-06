package dev.haroldraja.movies_preview.repository;

import dev.haroldraja.movies_preview.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {
}

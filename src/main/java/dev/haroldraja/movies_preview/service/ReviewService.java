package dev.haroldraja.movies_preview.service;

import dev.haroldraja.movies_preview.entity.Movie;
import dev.haroldraja.movies_preview.entity.Review;
import dev.haroldraja.movies_preview.model.ReviewCreationDTO;
import dev.haroldraja.movies_preview.model.ReviewDTO;
import dev.haroldraja.movies_preview.repository.ReviewRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieService movieService;
    private final MongoTemplate mongoTemplate;

    public ReviewService(ReviewRepository reviewRepository, MovieService movieService, MongoTemplate mongoTemplate) {
        this.reviewRepository = reviewRepository;
        this.movieService = movieService;
        this.mongoTemplate = mongoTemplate;
    }

    public ReviewDTO createReview(ReviewCreationDTO reviewCreationDTO){
        Review newReview = new Review();
        newReview.setBody(reviewCreationDTO.reviewBody());
        if(movieService.existsByImdbId(reviewCreationDTO.imdbId())){
            Review savedReview = reviewRepository.save(newReview);
            //movieService.addReviewToMovie(imdbID, savedReview.getId());
            mongoTemplate.update(Movie.class)
                    .matching(Criteria.where("imdbId").is(reviewCreationDTO.imdbId()))
                    .apply(new Update().push("reviewIds").value(savedReview.getId()))
                    .first()
            ;
            return new ReviewDTO(savedReview.getId(), savedReview.getBody());
        }
        return null;
    }
}

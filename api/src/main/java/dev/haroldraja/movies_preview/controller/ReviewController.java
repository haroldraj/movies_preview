package dev.haroldraja.movies_preview.controller;

import dev.haroldraja.movies_preview.model.ReviewCreationDTO;
import dev.haroldraja.movies_preview.model.ReviewDTO;
import dev.haroldraja.movies_preview.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewCreationDTO payload){
        return new ResponseEntity<>(reviewService.createReview(payload), HttpStatus.CREATED);
    }
}

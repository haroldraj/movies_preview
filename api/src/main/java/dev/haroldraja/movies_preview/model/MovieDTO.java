package dev.haroldraja.movies_preview.model;

import org.bson.types.ObjectId;

import java.util.List;

public record MovieDTO(String imdbId, String title, String releaseDate, String trailerLink,
                       String poster, List<String> genres, List<String> backdrops,
                       List<String> reviewIds) {
}

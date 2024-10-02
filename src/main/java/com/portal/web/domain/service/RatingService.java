package com.portal.web.domain.service;

import com.portal.web.domain.entity.Rating;

public interface RatingService {
    void setRating(Rating rating) throws RatingException;
    int getAverageRating(String game) throws RatingException;
    int getRating(String game, Long id) throws RatingException;
    void reset() throws RatingException;
}

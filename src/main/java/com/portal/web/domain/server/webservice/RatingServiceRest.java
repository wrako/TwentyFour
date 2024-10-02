package com.portal.web.domain.server.webservice;

import com.portal.web.domain.service.RatingService;
import com.portal.web.domain.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/rating")
public class RatingServiceRest {
    @Autowired
    private RatingService ratingService;

    @GetMapping("/{game}")
    public int getAverageRating(@PathVariable String game) {
        return ratingService.getAverageRating(game);
    }

    @GetMapping("/all/{game}")
    public int getRating(@PathVariable String game, Long id){return ratingService.getRating(game, id);}

    @PostMapping
    public void setRating(@RequestBody Rating rating) {
        ratingService.setRating(rating);
    }
}

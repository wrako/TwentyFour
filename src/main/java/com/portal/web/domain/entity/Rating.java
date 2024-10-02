package com.portal.web.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import java.util.Date;
@Data
@NoArgsConstructor
@Entity

@NamedQuery( name = "Rating.getAveRating",
        query = "SELECT AVG(c.rating) as ror FROM Rating c WHERE c.game = :game")
@NamedQuery( name = "Rating.getRatingByUsrId",
        query = "SELECT r FROM Rating r WHERE r.game=:game and r.usrId =: usrId")
@NamedQuery( name = "Rating.resetRating",
        query = "DELETE FROM Rating ")
@Table(name = "rating", schema = "public")

public class Rating {
    @Id
    @GeneratedValue
    private Long ratId;

    @Id
    private Long usrId;

    private int rating;

    private String game;

    private Date ratedOn;


    public Rating(Long usrId, int rating, String game, Date ratedOn) {
        this.usrId = usrId;
        this.rating = rating;
        this.game = game;
        this.ratedOn = ratedOn;
    }
}

package com.portal.web.domain.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.util.Date;


@Data
@NoArgsConstructor
@Entity
@NamedQuery( name = "Score.getTopScores",
        query = "SELECT s FROM Score s WHERE s.game=:game ORDER BY s.points DESC")
@NamedQuery( name = "Score.getScoreById",
        query = "SELECT s FROM Score s WHERE s.game=:game and s.usrId =: usrId")
@NamedQuery( name = "Score.resetScores",
        query = "DELETE FROM Score")

@Table(name = "score", schema = "public")

public class Score{
    @Id
    @GeneratedValue
    private Long scrId;

    @Id
    private Long usrId;

    @Column(name = "points")
    private int points;

    @Column(name = "playedon")
    private Date playedOn;

    @Column(name = "game")
    private String game;

    public Score(Long usrId, int points, Date playedOn, String game) {
        this.usrId = usrId;
        this.points = points;
        this.playedOn = playedOn;
        this.game = game;
    }
}

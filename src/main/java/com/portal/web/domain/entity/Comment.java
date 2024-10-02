package com.portal.web.domain.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@NamedQuery( name = "Comment.getComments",
        query = "SELECT c FROM Comment c WHERE c.game=:game ORDER BY c.commentedOn DESC")
@NamedQuery( name = "Comment.resetComments",
        query = "DELETE FROM Comment")

@Table(name = "comment", schema = "public")

public class Comment {
    @Id
    @GeneratedValue
    private Long comId;

    @Id
    private Long usrId;

    private String comment;

    private Date commentedOn;

    private String game;

    public Comment(Long userId, String comment, Date commentedOn, String game) {
        this.usrId = userId;
        this.comment = comment;
        this.commentedOn = commentedOn;
        this.game = game;
    }
}


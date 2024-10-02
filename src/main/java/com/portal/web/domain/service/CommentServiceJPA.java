package com.portal.web.domain.service;

import com.portal.web.domain.entity.Comment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;


@Transactional
public class CommentServiceJPA implements CommentService{

    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addComment(Comment comment) throws CommentException {
        entityManager.persist(comment);

    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        List<Comment> comments = entityManager.createNamedQuery("Comment.getComments")
                .setParameter("game", game).getResultList();

        return comments;

    }

    @Override
    public void reset() throws CommentException {
        entityManager.createNamedQuery("Comment.resetComments").executeUpdate();

    }
}

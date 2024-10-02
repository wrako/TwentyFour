package com.portal.web.domain.service;

import com.portal.web.domain.entity.Score;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addScore(Score score) throws ScoreException{
        entityManager.persist(score);
    }

    @Override
    public void updateScore(Score score) throws ScoreException{
        entityManager.merge(score);
//        entityManager.getTransaction().begin();
    }

    @Override
    public List<Score> getTopScores(String game) throws ScoreException {
        return entityManager.createNamedQuery("Score.getTopScores")
                .setParameter("game", game)
                .setMaxResults(10)
                .getResultList();

    }



    @Override
    public void reset() {
        entityManager.createNamedQuery("Score.resetScores").executeUpdate();
    }

    @Override
    public Score getScoreById(String game, Long usrId){
        try {
        return (Score) entityManager.createNamedQuery("Score.getScoreById")
                .setParameter("game", game)
                .setParameter("usrId", usrId)
                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

    }
}

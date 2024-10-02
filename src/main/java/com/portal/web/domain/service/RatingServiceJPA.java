
package com.portal.web.domain.service;


import com.portal.web.domain.entity.Rating;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Transactional
public class RatingServiceJPA implements RatingService {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void setRating(Rating rating) throws RatingException {
        try {
            Rating existingRating = (Rating) entityManager.createNamedQuery("Rating.getRatingByUsrId")
                    .setParameter("usrId", rating.getUsrId())
                    .setParameter("game", rating.getGame())
                    .getSingleResult();

            if (existingRating != null) {
                existingRating.setRating(rating.getRating());
                existingRating.setRatedOn(rating.getRatedOn());
                entityManager.merge(existingRating);
            }
        } catch (NoResultException e) {
            entityManager.persist(rating);
//            throw new RatingException("Error setting rating: " + e.getMessage(), e);
        }
    }


    @Override
    public int getAverageRating(String game) throws RatingException {
        Object object =  entityManager.createNamedQuery("Rating.getAveRating")
                .setParameter("game", game)
                .getSingleResult();
        if(object == null) return 0;

        return (int) Math.round((double) object);
    }




    @Override
    public int getRating(String game, Long id) throws RatingException {
        return entityManager.find(Rating.class, id).getRating();
    }


    @Override
    public void reset() {
        entityManager.createNamedQuery("Rating.resetRating").executeUpdate();

    }
}

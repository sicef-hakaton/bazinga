package com.bazinga.hakaton.repository;

import com.bazinga.hakaton.model.Card;
import com.bazinga.hakaton.model.User2Card;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Ivan on 23.11.2014.
 */
@Repository
@Transactional
public class User2CardRepositoryBean implements User2CardRepository {

    protected EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<User2Card> getAllByUserId(Long userId) {
        Query query = entityManager.createQuery("from User2Card u where userId = :userId");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public List<Card> getWatchedItems(Long userId) {
        Query query = entityManager.createQuery("select c from User2Card uc, Card c where userId = :userId and uc.cardId = c.id and uc.watch = true");
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    @Override
    public void setWatched(Long userId, Long cardId, boolean watch) {
        User2Card user2Card = null;
        try{
            user2Card = getUser2Card(userId, cardId);
        } catch (NoResultException e){
            user2Card = new User2Card();
            user2Card.setUserId(userId);
            user2Card.setCardId(cardId);
        }
        user2Card.setWatch(watch);
        entityManager.merge(user2Card);
    }

    @Override
    public void rateCard(Long userId, Long cardId, int rating) {
        User2Card user2Card = null;
        try{
            user2Card = getUser2Card(userId, cardId);
            if (user2Card.isRated()){
                return;
            }
        } catch (NoResultException e){
            user2Card = new User2Card();
            user2Card.setUserId(userId);
            user2Card.setCardId(cardId);
        }
        user2Card.setRated(true);
        entityManager.merge(user2Card);
        Card card = entityManager.find(Card.class, cardId);
        card.setRateCount(card.getRateCount()+1);
        card.setRateSum(card.getRateSum()+rating);
    }

    @Override
    public void reportProblemInCard(Long userId, Long cardId) {
        User2Card user2Card = null;
        try{
            user2Card = getUser2Card(userId, cardId);
        } catch (NoResultException e){
            user2Card = new User2Card();
            user2Card.setUserId(userId);
            user2Card.setCardId(cardId);
        }
        user2Card.setReported(true);
        entityManager.merge(user2Card);
    }

    private User2Card getUser2Card(Long userId, Long cardId) {
        Query query = entityManager.createQuery("from User2Card u where userId = :userId and cardId = :cardId");
        query.setParameter("userId", userId);
        query.setParameter("cardId", cardId);
        return (User2Card) query.getSingleResult();
    }

}

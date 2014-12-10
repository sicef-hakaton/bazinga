package com.bazinga.hakaton.repository;


import com.bazinga.hakaton.model.Card;
import com.bazinga.hakaton.model.User2Card;

import java.util.List;

/**
 * Created by Ivan on 23.11.2014.
 */
public interface User2CardRepository {

    List<User2Card> getAllByUserId(Long userId);

    List<Card> getWatchedItems(Long userId);

    void setWatched(Long userId, Long cardId, boolean watch);

    void rateCard(Long userId, Long cardId, int rating);

    void reportProblemInCard(Long userId, Long cardId);
}

package com.bazinga.hakaton.service;

import com.bazinga.hakaton.model.Card;
import com.bazinga.hakaton.model.User;
import com.bazinga.hakaton.model.User2Card;

import java.util.List;

/**
 * Created by Ivan on 23.11.2014.
 */
public interface UserService {

    List<Card> getWatchedItems(Long userId);

    List<User2Card> getUser2CardList(Long userId);

    void addWatchedItem(Long userId, Long cardId);

    void removeWatchedItem(Long userId, Long cardId);

    User login(String email, String password);

    User register(String email, String password);

    void rateCard(Long userId, Long cardId, int rating);

    void reportProblemInCard(Long userId, Long cardId);

}

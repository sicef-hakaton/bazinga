package com.bazinga.hakaton.service;

import com.bazinga.hakaton.model.Card;
import com.bazinga.hakaton.model.User;
import com.bazinga.hakaton.model.User2Card;
import com.bazinga.hakaton.repository.User2CardRepository;
import com.bazinga.hakaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by Ivan on 23.11.2014.
 */
@Service
@Transactional
public class UserServiceBean implements UserService {

    @Autowired
    private User2CardRepository user2CardRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Card> getWatchedItems(Long userId) {
        return user2CardRepository.getWatchedItems(userId);
    }

    @Override
    public List<User2Card> getUser2CardList(Long userId) {
        return user2CardRepository.getAllByUserId(userId);
    }

    @Override
    public void addWatchedItem(Long userId, Long cardId) {
        user2CardRepository.setWatched(userId, cardId, true);
    }

    @Override
    public void removeWatchedItem(Long userId, Long cardId) {
        user2CardRepository.setWatched(userId, cardId, false);
    }

    @Override
    public User login(String email, String password){
        User user = userRepository.findUserByEmailAndPassword(email, md5(password));
        return user;
    }

    @Override
    public User register(String email, String password){
        if(userRepository.findUserByEmail(email)!=null){
            return null;
        }
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setPassword(md5(password));
        return userRepository.save(newUser);
    }

    @Override
    public void rateCard(Long userId, Long cardId, int rating) {
        user2CardRepository.rateCard(userId, cardId, rating);
    }

    @Override
    public void reportProblemInCard(Long userId, Long cardId) {
        user2CardRepository.reportProblemInCard(userId, cardId);
    }

    private String md5(String s) {
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(s.getBytes(), 0, s.length());
            BigInteger i = new BigInteger(1, digest.digest());
            return String.format("%1$032x", i);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

}

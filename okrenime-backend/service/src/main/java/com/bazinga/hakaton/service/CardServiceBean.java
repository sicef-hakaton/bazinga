package com.bazinga.hakaton.service;

import com.bazinga.hakaton.model.Card;
import com.bazinga.hakaton.model.Subject;
import com.bazinga.hakaton.model.User;
import com.bazinga.hakaton.repository.CardRepository;
import com.bazinga.hakaton.repository.SubjectRepository;
import com.bazinga.hakaton.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Ivan on 23.11.2014.
 */
@Service
public class CardServiceBean implements CardService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private CardRepository cardRepository;

    @Override
    public Card storeCard(Long userId, Long subjectId, String question, String answer) {
        User user = userRepository.getOne(userId);
        Subject subject = subjectRepository.getOne(subjectId);
        if (user==null || subject==null){
            return null;
        }
        Card card = new Card();
        card.setRateSum(0);
        card.setRateCount(0);
        card.setCreator(user);
        card.setSubject(subject);
        card.setQuestion(question);
        card.setAnswer(answer);
        return cardRepository.save(card);
    }

}

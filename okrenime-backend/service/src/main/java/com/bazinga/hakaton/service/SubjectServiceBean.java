package com.bazinga.hakaton.service;

import com.bazinga.hakaton.model.Card;
import com.bazinga.hakaton.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * Created by Ivan on 23.11.2014.
 */
@Service
@Transactional
public class SubjectServiceBean implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<Card> getCardsBySubject(Long subjectId) {
        List<Card> cards = subjectRepository.findOne(subjectId).getCards();
        cards.size(); //one cool hack for hackaton :)
        Collections.sort(cards);
        return cards;
    }

}

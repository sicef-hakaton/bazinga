package com.bazinga.hakaton.service;

import com.bazinga.hakaton.model.Card;

/**
 * Created by Ivan on 23.11.2014.
 */
public interface CardService {

    public Card storeCard(Long userId, Long subjectId, String question, String answer);

}

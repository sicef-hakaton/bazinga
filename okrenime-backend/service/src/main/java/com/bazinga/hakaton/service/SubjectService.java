package com.bazinga.hakaton.service;

import com.bazinga.hakaton.model.Card;

import java.util.List;

/**
 * Created by Ivan on 23.11.2014.
 */
public interface SubjectService {

    List<Card> getCardsBySubject(Long subjectId);
}

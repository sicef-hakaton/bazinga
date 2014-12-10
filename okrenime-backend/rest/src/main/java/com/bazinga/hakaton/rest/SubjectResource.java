package com.bazinga.hakaton.rest;

import com.bazinga.hakaton.dto.CardDto;
import com.bazinga.hakaton.dto.DtoTransformer;
import com.bazinga.hakaton.model.Card;
import com.bazinga.hakaton.model.User2Card;
import com.bazinga.hakaton.service.SubjectService;
import com.bazinga.hakaton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Ivan on 22.11.2014.
 */

@RestController
@RequestMapping("/subject")
public class SubjectResource {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "{id}/{userId}", method = RequestMethod.GET)
    public List<CardDto> getCardsBySubject(@PathVariable("id") Long subjectId, @PathVariable("userId") Long userId) {
        List<Card> cardsBySubject = subjectService.getCardsBySubject(subjectId);
        List<User2Card> user2CardList = userService.getUser2CardList(userId);
        return DtoTransformer.convertCardList(cardsBySubject, user2CardList);
    }

}

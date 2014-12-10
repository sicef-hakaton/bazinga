package com.bazinga.hakaton.rest;

import com.bazinga.hakaton.dto.CardDto;
import com.bazinga.hakaton.dto.DtoTransformer;
import com.bazinga.hakaton.dto.UserDto;
import com.bazinga.hakaton.model.Card;
import com.bazinga.hakaton.model.User;
import com.bazinga.hakaton.model.User2Card;
import com.bazinga.hakaton.service.CardService;
import com.bazinga.hakaton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/user")
public class UserResource {

    private String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Autowired
    private UserService userService;

    @Autowired
    private CardService cardService;

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ResponseEntity<UserDto> login(@RequestBody UserDto login) {
        if (login.getEmail()==null || login.getPassword()==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User user = userService.login(login.getEmail(), login.getPassword());
        HttpStatus status = user!=null ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(DtoTransformer.convert(user), status);
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public ResponseEntity<UserDto> register(@RequestBody UserDto login) {
        if (login.getEmail()==null || login.getPassword()==null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        matcher = pattern.matcher(login.getEmail());
        if (!matcher.matches()){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        User user = userService.register(login.getEmail(), login.getPassword());
        HttpStatus status = user!=null ? HttpStatus.OK : HttpStatus.UNPROCESSABLE_ENTITY;
        return new ResponseEntity<>(DtoTransformer.convert(user), status);
    }

    @RequestMapping(value="/{id}/watch", method = RequestMethod.GET)
    public List<CardDto> getWatchedItems(@PathVariable("id") Long userId) {
        List<Card> watchedItems = userService.getWatchedItems(userId);
        List<User2Card> user2CardList = userService.getUser2CardList(userId);
        return DtoTransformer.convertCardList(watchedItems, user2CardList);
    }

    @RequestMapping(value="/{userId}/watch/{cardId}", method = RequestMethod.POST)
    public void addWatchedItem(@PathVariable("userId") Long userId, @PathVariable("cardId") Long cardId) {
        userService.addWatchedItem(userId, cardId);
    }

    @RequestMapping(value="/{userId}/watch/{cardId}", method = RequestMethod.DELETE)
    public void removeWatchedItem(@PathVariable("userId") Long userId, @PathVariable("cardId") Long cardId) {
        userService.removeWatchedItem(userId, cardId);
    }

    @RequestMapping(value="/{userId}/rate/{cardId}/{rating}", method = RequestMethod.POST)
    public void rateCard(@PathVariable("userId") Long userId, @PathVariable("cardId") Long cardId, @PathVariable("rating") int rating) {
        userService.rateCard(userId, cardId, rating);
    }

    @RequestMapping(value="/{userId}/report/{cardId}", method = RequestMethod.POST)
    public void reportProblemInCard(@PathVariable("userId") Long userId, @PathVariable("cardId") Long cardId) {
        userService.reportProblemInCard(userId, cardId);
    }

    @RequestMapping(value="/{userId}/{subjectId}/card", method = RequestMethod.POST)
    public ResponseEntity<CardDto> createCard(@PathVariable("userId") Long userId, @PathVariable("subjectId") Long subjectId, @RequestBody CardDto cardDto) {
        Card card = cardService.storeCard(userId, subjectId, cardDto.getQuestion(), cardDto.getAnswer());
        if (card==null){
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
        List<User2Card> user2CardList = userService.getUser2CardList(userId);
        return new ResponseEntity<>(DtoTransformer.convert(card, user2CardList), HttpStatus.OK);
    }

}

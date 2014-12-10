package com.bazinga.hakaton.dto;

import com.bazinga.hakaton.model.Card;
import com.bazinga.hakaton.model.EducationalUnit;
import com.bazinga.hakaton.model.Subject;
import com.bazinga.hakaton.model.User;
import com.bazinga.hakaton.model.User2Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ivan on 23.11.2014.
 */
public class DtoTransformer {

    public static List<EducationalUnitDto> convert(List<EducationalUnit> educationalUnit){
        List<EducationalUnitDto> educationalUnitDtos = new ArrayList<>();
        for (EducationalUnit unit : educationalUnit) {
            educationalUnitDtos.add(convert(unit));
        }
        return educationalUnitDtos;
    }

    public static EducationalUnitDto convert(EducationalUnit educationalUnit){
        EducationalUnitDto educationalUnitDto = new EducationalUnitDto();
        educationalUnitDto.setId(educationalUnit.getId());
        educationalUnitDto.setName(educationalUnit.getName());
        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (Subject subject : educationalUnit.getSubjects()) {
            subjectDtos.add(convert(subject));
        }
        educationalUnitDto.setSubjects(subjectDtos);
        return educationalUnitDto;
    }

    public static SubjectDto convert(Subject subject){
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        return subjectDto;
    }

    public static List<SubjectDto> convertSubjectList(List<Subject> subjects){
        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (Subject subject : subjects) {
            subjectDtos.add(convert(subject));
        }
        return subjectDtos;
    }

    public static UserDto convert(User user){
        if (user==null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    public static List<CardDto> convertCardList(List<Card> cards, List<User2Card> user2CardList){
        List<CardDto> cardDtos = new ArrayList<>();
        for (Card card : cards) {
            cardDtos.add(convert(card, user2CardList));
        }
        return cardDtos;
    }

    public static CardDto convert(Card card, List<User2Card> user2CardList) {
        CardDto cardDto = new CardDto();
        cardDto.setId(card.getId());
        cardDto.setQuestion(card.getQuestion());
        cardDto.setAnswer(card.getAnswer());
        User2Card u2c = getByCardId(user2CardList, card.getId());
        if (u2c!=null){
            cardDto.setDidIRate(u2c.isRated());
            cardDto.setWatch(u2c.isWatch());
            cardDto.setDidIReport(u2c.isReported());
        }
        cardDto.setRating(card.getRateCount()==0?0:((double)card.getRateSum())/card.getRateCount());
        return cardDto;
    }

    private static User2Card getByCardId(List<User2Card> user2Cards, Long cardId){
        for (User2Card u2c : user2Cards) {
            if (u2c.getCardId().equals(cardId)){
                return u2c;
            }
        }
        return null;
    }
}

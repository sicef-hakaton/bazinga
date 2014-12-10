package com.bazinga.hakaton.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Random;

/**
 * Created by Ivan on 22.11.2014.
 */
@Entity
public class Card implements Comparable<Card> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String question;
    private String answer;
    private int rateCount;
    private int rateSum;
    private int reportCount;
    @ManyToOne(fetch = FetchType.LAZY)
    private Subject subject;
    @ManyToOne(fetch = FetchType.LAZY)
    private User creator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getRateCount() {
        return rateCount;
    }

    public void setRateCount(int rateCount) {
        this.rateCount = rateCount;
    }

    public int getRateSum() {
        return rateSum;
    }

    public void setRateSum(int rateSum) {
        this.rateSum = rateSum;
    }

    public int getReportCount() {
        return reportCount;
    }

    public void setReportCount(int reportCount) {
        this.reportCount = reportCount;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Card card = (Card) o;

        if (rateCount != card.rateCount) return false;
        if (rateSum != card.rateSum) return false;
        if (reportCount != card.reportCount) return false;
        if (answer != null ? !answer.equals(card.answer) : card.answer != null) return false;
        if (creator != null ? !creator.equals(card.creator) : card.creator != null) return false;
        if (id != null ? !id.equals(card.id) : card.id != null) return false;
        if (question != null ? !question.equals(card.question) : card.question != null) return false;
        if (subject != null ? !subject.equals(card.subject) : card.subject != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        result = 31 * result + rateCount;
        result = 31 * result + rateSum;
        result = 31 * result + reportCount;
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Card o) {
        if (o.getRateCount()==0){
            return new Random().nextInt(2)-1;
        }
        double myRating = getRateCount() == 0 ? 0.0 : ((double)getRateSum())/getRateCount();
        double otherRating = o.getRateSum() == 0 ? 0.0 : ((double)o.getRateSum())/o.getRateCount();
        return otherRating-myRating > 0 ? 1 : -1;
    }
}

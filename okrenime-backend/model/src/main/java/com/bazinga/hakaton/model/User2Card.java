package com.bazinga.hakaton.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

/**
 * Created by Ivan on 22.11.2014.
 */
@Entity
@IdClass(User2CardId.class)
public class User2Card {

    @Id
    private Long userId;
    @Id
    private Long cardId;

    private boolean rated;

    private boolean watch;

    private boolean reported;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public boolean isRated() {
        return rated;
    }

    public void setRated(boolean rated) {
        this.rated = rated;
    }

    public boolean isWatch() {
        return watch;
    }

    public void setWatch(boolean watch) {
        this.watch = watch;
    }

    public boolean isReported() {
        return reported;
    }

    public void setReported(boolean reported) {
        this.reported = reported;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User2Card user2Card = (User2Card) o;

        if (rated != user2Card.rated) return false;
        if (reported != user2Card.reported) return false;
        if (watch != user2Card.watch) return false;
        if (cardId != null ? !cardId.equals(user2Card.cardId) : user2Card.cardId != null) return false;
        if (userId != null ? !userId.equals(user2Card.userId) : user2Card.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (cardId != null ? cardId.hashCode() : 0);
        result = 31 * result + (rated ? 1 : 0);
        result = 31 * result + (watch ? 1 : 0);
        result = 31 * result + (reported ? 1 : 0);
        return result;
    }
}

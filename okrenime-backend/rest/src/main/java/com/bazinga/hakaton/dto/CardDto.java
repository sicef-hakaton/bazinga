package com.bazinga.hakaton.dto;

public class CardDto {
    private Long id;
    private String question;
    private String answer;
    private double rating;
    private boolean didIRate;
    private boolean watch;
    private boolean didIReport;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public boolean isDidIRate() {
        return didIRate;
    }

    public void setDidIRate(boolean didIRate) {
        this.didIRate = didIRate;
    }

    public boolean isWatch() {
        return watch;
    }

    public void setWatch(boolean watch) {
        this.watch = watch;
    }

    public boolean isDidIReport() {
        return didIReport;
    }

    public void setDidIReport(boolean didIReport) {
        this.didIReport = didIReport;
    }
}

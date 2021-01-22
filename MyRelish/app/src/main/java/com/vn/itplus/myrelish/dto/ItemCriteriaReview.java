package com.vn.itplus.myrelish.dto;

public class ItemCriteriaReview {
    private int userId, gameId, criteriaId;
    private String criteriaName;
    private int score;
    private String review;

    public ItemCriteriaReview(int userId, int gameId, int criteriaId, String criteriaName, int score, String review) {
        this.userId = userId;
        this.gameId = gameId;
        this.criteriaId = criteriaId;
        this.criteriaName = criteriaName;
        this.score = score;
        this.review = review;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public int getCriteriaId() {
        return criteriaId;
    }

    public void setCriteriaId(int criteriaId) {
        this.criteriaId = criteriaId;
    }

    public String getCriteriaName() {
        return criteriaName;
    }

    public void setCriteriaName(String criteriaName) {
        this.criteriaName = criteriaName;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }
}

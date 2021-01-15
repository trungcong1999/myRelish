package com.vn.itplus.myrelish.dto;

public class ItemCriteriaReview {
    private String criteriaName;
    private int score;
    private String review;

    public ItemCriteriaReview(String criteriaName, int score, String review) {
        this.criteriaName = criteriaName;
        this.score = score;
        this.review = review;
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

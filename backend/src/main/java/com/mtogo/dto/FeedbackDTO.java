package com.mtogo.dto;

import java.time.LocalDateTime;

public class FeedbackDTO {
    private Long id;
    private int rating;
    private String comments;
    private LocalDateTime createdAt;

    public FeedbackDTO() {}

    public FeedbackDTO(Long id, int rating, String comments, LocalDateTime createdAt) {
        this.id = id;
        this.rating = rating;
        this.comments = comments;
        this.createdAt = createdAt;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComments() { return comments; }
    public void setComments(String comments) { this.comments = comments; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

package com.mtogo.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Customer feedback submitted after an order is delivered")
public class FeedbackDTO {

    @Schema(
        description = "Unique identifier of the feedback entry",
        example = "301"
    )
    private Long id;

    @Schema(
        description = "Customer rating between 1 and 5",
        example = "5",
        minimum = "1",
        maximum = "5"
    )
    private int rating;

    @Schema(
        description = "Optional comments provided by the customer",
        example = "Fantastic delivery service and very friendly courier!"
    )
    private String comments;

    @Schema(
        description = "Timestamp when feedback was created",
        example = "2025-05-13T14:30:00"
    )
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

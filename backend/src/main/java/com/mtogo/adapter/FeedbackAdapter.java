package com.mtogo.adapter;

import com.mtogo.dto.FeedbackDTO;
import com.mtogo.model.Feedback;

public class FeedbackAdapter {

    public static FeedbackDTO toDTO(Feedback feedback) {
        if (feedback == null) return null;
        return new FeedbackDTO(
                feedback.getId(),
                feedback.getRating(),
                feedback.getComments(),
                feedback.getCreatedAt()
        );
    }

    public static Feedback toEntity(FeedbackDTO dto) {
        if (dto == null) return null;
        Feedback feedback = new Feedback();
        feedback.setId(dto.getId());
        feedback.setRating(dto.getRating());
        feedback.setComments(dto.getComments());
        feedback.setCreatedAt(dto.getCreatedAt());
        return feedback;
    }
}

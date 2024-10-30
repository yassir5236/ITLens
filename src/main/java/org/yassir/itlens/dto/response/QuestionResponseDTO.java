package org.yassir.itlens.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponseDTO {
    private Long id;
    private String text;
    private String type; // CHOIX_UNIQUE or CHOIX_MULTIPLE
    private List<AnswerResponseDTO> answers; // Liste des réponses
    private int answerCount; // Nombre total de réponses à cette question
}
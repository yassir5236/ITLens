package org.yassir.itlens.dto.request;

import lombok.Data;

import java.util.List;
import lombok.Data;
import org.yassir.itlens.model.Enum.QuestionType;

@Data
public class QuestionRequestDTO {
    private String content;                     // Texte de la question
    private QuestionType type;                  // Type de question (CHOIX_UNIQUE, CHOIX_MULTIPLE)
    private List<String> options;
    private Long subChapterId; // Référence au sous-chapitre associé

    // Getters et Setters
}

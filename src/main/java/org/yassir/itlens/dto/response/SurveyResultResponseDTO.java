package org.yassir.itlens.dto.response;


import lombok.Data;
import java.util.List;

@Data
public class SurveyResultResponseDTO {
    private String surveyTitle;
    private List<ChapterResultDTO> chapters; // Résultats par chapitre
}

@Data
class ChapterResultDTO {
    private String title;
    private List<SubChapterResultDTO> subChapters; // Résultats par sous-chapitre
}

@Data
class SubChapterResultDTO {
    private String title;
    private String question; // Texte de la question
    private List<AnswerCountDTO> answers; // Réponses avec leurs compteurs
    private int totalAnswers; // Nombre total de réponses

    @Data
    class AnswerCountDTO {
        private String answer; // Texte de la réponse
        private int count; // Compte de cette réponse
    }
}

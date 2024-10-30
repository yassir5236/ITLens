package org.yassir.itlens.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class SurveyResponseDTO {
    private Long id;
    private String title;
    private String description;
    private Long ownerId; // Référence au propriétaire
    private List<ChapterResponseDTO> chapters; // Liste des chapitres
}
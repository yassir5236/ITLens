package org.yassir.itlens.dto.response;


import lombok.Data;

@Data
public class AnswerResponseDTO {
    private Long id;
    private String text;
    private int selectionCount; // Nombre de sélections pour cette réponse
    private double percentage; // Pourcentage des sélections par rapport au nombre total de réponses
}
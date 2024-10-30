package org.yassir.itlens.dto.response;


import lombok.Data;
import java.util.List;

@Data
public class SubChapterResponseDTO {
    private Long id;
    private String title;
    private List<QuestionResponseDTO> questions; // Liste des questions
}


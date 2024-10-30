package org.yassir.itlens.dto.response;

import lombok.Data;
import java.util.List;

@Data
public class ChapterResponseDTO {
    private Long id;
    private String title;
    private List<SubChapterResponseDTO> subChapters; // Liste des sous-chapitres
}

package org.yassir.itlens.dto.Answer;

public record AnswerResponse(
        Long id ,
        String text,
        int selectionCount
//        Long questionId
) {
}

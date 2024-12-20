package org.yassir.itlens.dto.Participation;

import java.util.List;

public record Response(
        Long questionId,
        List<AnswerPayload> answers
) {}


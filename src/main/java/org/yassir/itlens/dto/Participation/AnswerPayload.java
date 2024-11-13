package org.yassir.itlens.dto.Participation;

import jakarta.persistence.Column;

public record AnswerPayload(
        @Column(nullable = false)
        Long answerId
) {}


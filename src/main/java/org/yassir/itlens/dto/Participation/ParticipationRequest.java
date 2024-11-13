package org.yassir.itlens.dto.Participation;

import java.util.List;

public record ParticipationRequest(
        List<Response> responses
) {}


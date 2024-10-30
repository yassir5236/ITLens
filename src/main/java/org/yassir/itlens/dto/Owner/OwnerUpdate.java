package org.yassir.itlens.dto.Owner;

import jakarta.validation.constraints.NotNull;

public record OwnerUpdate(
        Long id,
        @NotNull(message = "name is required")
        String name
) {}

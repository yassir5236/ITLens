package org.yassir.itlens.dto.Owner;

import jakarta.validation.constraints.NotNull;
import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

public record OwnerUpdate(
        @IdExist(message =  "Owner with this ID does not exist")
        Long id,
        @NotNull(message = "name is required")
        @UniqueField
        String name
) {}

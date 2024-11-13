package org.yassir.itlens.dto.Owner;

import jakarta.validation.constraints.NotNull;
import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

public record OwnerUpdate(

        @NotNull(message = "name is required")
        @UniqueField
        String name
) {}

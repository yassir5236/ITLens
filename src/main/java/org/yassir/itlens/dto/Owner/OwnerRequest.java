package org.yassir.itlens.dto.Owner;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.yassir.itlens.validation.UniqueField;

public record OwnerRequest(

        @NotBlank(message = "name is required")
        @NotNull(message = "name is required")
        @UniqueField
        String name

) {
}

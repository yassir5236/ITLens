package org.yassir.itlens.dto.Owner;

import jakarta.validation.constraints.NotBlank;

public record OwnerRequest(

    @NotBlank(message = "age is required")
    String name

) {
}

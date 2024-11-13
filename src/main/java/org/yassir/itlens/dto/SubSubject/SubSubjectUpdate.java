package org.yassir.itlens.dto.SubSubject;

import org.yassir.itlens.validation.IdExist;
import org.yassir.itlens.validation.UniqueField;

public record SubSubjectUpdate(

        @UniqueField
        String title,

        @IdExist(message = "subject id not found")
        Long subjectId

) {
}

package org.yassir.itlens.model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;


@Entity
@Table(name ="answers")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Answer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String text ;
    private int selectionCount ;

    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question ;
}

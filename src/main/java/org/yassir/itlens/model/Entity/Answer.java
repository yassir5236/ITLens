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

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private int selectionCount;

    @ManyToOne(optional = false)
    @JoinColumn(name="question_id", nullable = false)
    private Question question;
}

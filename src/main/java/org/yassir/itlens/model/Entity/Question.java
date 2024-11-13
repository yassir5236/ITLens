package org.yassir.itlens.model.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.yassir.itlens.model.Enum.QuestionType;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "questions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Question implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( nullable = false)
    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @Column( nullable = false)
    private int answerCount;

    @ManyToOne
    @JoinColumn(name = "sub_subject_id")
    private SubSubject subSubject;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}

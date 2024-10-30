package org.yassir.itlens.model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name ="questions")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Question  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private int answerCount;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private SubSubject subSubject;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;
}

package org.yassir.itlens.model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subjects")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Subject implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    String title;

    @ManyToOne
    @JoinColumn(name = "surveyEdition_id")
    private SurveyEdition surveyEdition;

    //    @OneToMany(mappedBy = "subject")
    @OneToMany(mappedBy = "subject", fetch = FetchType.EAGER)

    private List<SubSubject> subSubjects;


}

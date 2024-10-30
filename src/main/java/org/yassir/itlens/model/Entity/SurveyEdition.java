package org.yassir.itlens.model.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "surveyEditions")

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class SurveyEdition implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    LocalDate creationDate;
    LocalDate startDate;
    LocalDate endDate;
    int year;

    @ManyToOne
    @JoinColumn(name = "survey_id")
    private  Survey survey;

    @OneToMany(mappedBy = "surveyEdition")
    private List<Subject> subjects;



}

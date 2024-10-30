package org.yassir.itlens.model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.util.List;


@Entity
@Table(name ="surveys")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Survey implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "title is required")
    @Column(name = "title", nullable = false , unique = true)
    private String title;

    @NotNull(message = "description is required")
    @Column(name = "description", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name="owner_id")
    private Owner owner;

    @OneToMany(mappedBy = "survey")
    private List<SurveyEdition> surveyEdition;

}

package org.yassir.itlens.model.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.yassir.itlens.validation.UniqueField;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name ="owners")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString

public class Owner implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "name is required")
    @Column(name = "name", nullable = false)
//    @UniqueField
    private String name;

    @OneToMany(mappedBy = "owner")
    List<Survey> surveys = new ArrayList<>();

}

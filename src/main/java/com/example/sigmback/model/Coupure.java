package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coupure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupure_Id")
    private Long coupureId;

    @Column(name = "coupure_Libelle",length = 50)
    private String coupureLibelle;

    @Column(name = "masse")
    private Integer masse;

    @OneToMany(mappedBy = "coupure")
    @JsonIgnore
    private List<Analyses> analyses;

}

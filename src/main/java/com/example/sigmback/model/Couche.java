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
public class Couche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "couche_Id",length = 10)
    private Long coucheId;

    @Column(name = "couche_Code",length = 10,unique = true,nullable = false)
    private String coucheCode;

    @Column(name = "couche_Libelle",length = 10,nullable = false)
    private String coucheLibelle;

    @Column(name = "couche_Ordre",nullable = false)
    private Integer coucheOrdre;

    @OneToMany(mappedBy = "couche")
    @JsonIgnore
    private List<Geologie> geologies;
}

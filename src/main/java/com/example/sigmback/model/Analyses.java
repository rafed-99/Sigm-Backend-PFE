package com.example.sigmback.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Analyses {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "analyse_Id")
    private Long analyseId;

    @Column(name = "valeur_Analyse",nullable = false)
    private Float valeurAnalyse;


    @Column(name = "etat_Analyse",nullable = false,columnDefinition = "varchar(255) default 'Nouvelle' ")
    @Enumerated(EnumType.STRING)
    private EtatAnalyse etatAnalyse;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Echantillon echantillon;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Coupure coupure;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Element element;

}

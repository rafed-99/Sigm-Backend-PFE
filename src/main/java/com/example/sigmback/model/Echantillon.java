package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Echantillon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "echantillon_Id")
    private Long echantillonId;

    @Column(name = "depth_From",nullable = false)
    private Float depthFrom;

    @Column(name = "depth_To",nullable = false)
    private Float depthTo;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_Envoi")
    private Date dateEnvoi;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_Reception")
    private Date dateReception;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_Analyse")
    private Date dateAnalyse;

    @Column(name = "observation",length = 50)
    private String observation;

    @Column(name = "url_Docs",length = 50)
    private String urlDocs;

    @Column(name = "notes_Geologie",length = 100)
    private String notesGeologie;

    @Column(name = "notes_CentreRecherche",length = 100)
    private String notesCentreRecherche;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat_Echantillons",columnDefinition = "varchar(255) default 'Nouveau'")
    private EtatEchantillon etatEchantillon;

    @Column(name = "analyse_Granulometrique",length = 100)
    private String analyseGranulometrique;

    @Column(name = "analyse_Mineralogique",length = 100)
    private String notesMineralogique;

    //table fille(relation echantillon et geologie)
    @ManyToOne
    @JoinColumn(nullable = false)
    private Geologie geologie;

    //table fille(relation echantillon et bordereau)
    @ManyToOne
    @JoinColumn
    private Bordereau bordereau;

    @OneToMany(mappedBy = "echantillon")
    @JsonIgnore
    private List<Analyses> analyses;
}

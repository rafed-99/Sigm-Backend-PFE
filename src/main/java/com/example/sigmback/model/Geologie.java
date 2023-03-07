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
public class Geologie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "geologie_Id",length = 10)
    private Long geologieId;

    @Column(name = "depth_From")
    private Float depthFrom;

    @Column(name = "depth_To")
    private Float depthTo;

    @Column(name = "puissance")
    private Float puissance;

    @Column(name = "puissance_Reelle")
    private Float puissanceReelle;

    @Column(name = "description_Lithologique",length = 50)
    private String descriptionLithologique;

    //table fille(relation geologie et couche)
    @ManyToOne
    @JoinColumn(nullable = false)
    private Couche couche;

    //table fille(relation geologie et point)
    @ManyToOne
    @JoinColumn(nullable = false)
    private Point point;

    //table mere(relation entre geologie et echantillon)
    @OneToMany(mappedBy = "geologie")
    @JsonIgnore
    private List<Echantillon> echantillons;
}

package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Gisement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gisement_Id")
    private Long gisementId;

    @Column(name = "gisement_Code",unique = true,nullable = false)
    private String gisementCode;

    @Column(name = "gisement_Libelle",length = 45,nullable = false)
    private String gisementLibelle;

    @Column(name = "secteur",length = 45,nullable = false)
    @Enumerated(EnumType.STRING)
    private Secteur secteur;

    //table mere (relation gisement et point)
    @OneToMany(mappedBy = "gisement")
    @JsonIgnore
    private List<Point> points;
}

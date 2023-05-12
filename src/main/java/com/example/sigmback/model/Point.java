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
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_Id",length = 10)
    private Long pointId;

    @Column(name = "hole_Id",length = 10,unique = true,nullable = false)
    private String holeId;

    @Column(name = "X",length = 15,nullable = false)
    private String x;

    @Column(name = "Y",length = 15,nullable = false)
    private String y;

    @Column(name = "Z",length = 15)
    private String z;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "varchar(255) default 'UTM' ")
    private SystemeCoordonnees systemeCoordonnees;

    @Column(name = "X_c",length = 15)
    private String xC;

    @Column(name = "Y_c",length = 15)
    private String yC;

    @Column(name = "Z_c",length = 15)
    private String zC;

    @Column(name = "dip",length = 10)
    private String dip;

    @Column(name = "azimut",length = 10)
    private String azimut;

    @Column(name = "depth_max",length = 10)
    private String depthMax;

    @Column(name = "export_gis")
    private Boolean exportGis;

    @Column(name = "niveau_pizometrique")
    private String niveauPizometrique;

    @Column(name = "point_Type",nullable = false,columnDefinition = "varchar(255) default 'Sondage' ")
    @Enumerated(EnumType.STRING)
    private PointType pointType;


    //table fille (relation gisement et point)
    @ManyToOne
    @JoinColumn(nullable = false)
    private Gisement gisement;

    //table fille (relation archive et point)

    @ManyToOne
    @JoinColumn
    private Archive archive;

    @OneToMany(mappedBy = "point")
    @JsonIgnore
    private List<Geologie> geologies;

}

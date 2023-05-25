package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bordereau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bordereau_Id",length = 10)
    private Long bordereauId;

    @Column(name = "bordereau_Code",length = 15,unique = true)
    private String bordereauCode;

    @Column(name = "date_Envoi",nullable = false, updatable = false)
    @CreationTimestamp
    private Date dateEnvoi;

    @Column(name = "analyse_demande",columnDefinition = "varchar(255) default 'Analyse Complete'")
    //@Enumerated(EnumType.STRING)
    private String analyseDemande;

    @Column(name = "exigences",length = 50)
    private String exigences;

    @Column(name = "urgences",columnDefinition = "varchar(255) default 'Normale'")
    @Enumerated(EnumType.STRING)
    private Urgences urgences;

    @Column(columnDefinition = "varchar(255) default 'En_Attente'")
    @Enumerated(EnumType.STRING)
    private EtatsBordereaux etatsBordereaux;

    @Column(name = "date_Reception")
    @Temporal(TemporalType.DATE)
    @CreatedDate
    private Date dateReception;

    //table mere(relation entre bordereau et echantillon)
    @OneToMany(mappedBy = "bordereau")
    @JsonIgnore
    private List<Echantillon> echantillons;

    @ManyToOne
    @JoinColumn
    private Archive archive;
}

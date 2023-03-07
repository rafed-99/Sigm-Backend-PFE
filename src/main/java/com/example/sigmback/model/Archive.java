package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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
public class Archive {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "archive_Id")
    private Long archiveId;

    @Column(name = "archive_Libelle",length = 50,nullable = false)
    private String archiveLibelle;

    @Column(name = "archive_Date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date archiveDate;

    @Column(name = "archive_Type",nullable = false,columnDefinition = "varchar(255) default 'Bordereau'")
    @Enumerated(EnumType.STRING)
    private ArchiveType archiveType;

    @OneToMany(mappedBy = "archive")
    @JsonIgnore
    private List<Point> points;

    @OneToMany(mappedBy = "archive")
    @JsonIgnore
    private List<Bordereau> bordereaux;
}

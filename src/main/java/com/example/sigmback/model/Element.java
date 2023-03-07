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
public class Element {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "element_Id")
    private Long elementId;

    @Column(name = "element_Code",length = 10,unique = true,nullable = false)
    private String elementCode;

    @Column(name = "element_Libelle",length = 50,nullable = false)
    private String elementLibelle;

    @OneToMany(mappedBy = "element")
    @JsonIgnore
    private List<Analyse> analyses;
}

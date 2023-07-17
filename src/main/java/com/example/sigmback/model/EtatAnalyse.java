package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EtatAnalyse {
    @JsonProperty("New")Nouvelle, @JsonProperty("Confirmed")Confirme, @JsonProperty("Valid")Valide
}

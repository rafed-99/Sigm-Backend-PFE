package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EtatsBordereaux {
    @JsonProperty("To Verify")A_verifier, @JsonProperty("On Hold")En_Attente, @JsonProperty("In progress")En_Cours, @JsonProperty("Analysed")Analyse
}

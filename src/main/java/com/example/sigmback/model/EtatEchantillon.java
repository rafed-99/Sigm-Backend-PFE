package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum EtatEchantillon {
    @JsonProperty("New")Nouvelle, @JsonProperty("To Verify")AVerifier, @JsonProperty("Sent")Envoye, @JsonProperty("Received")Recu, @JsonProperty("Analysed")Analyse, @JsonProperty("Terminated")Termine

}

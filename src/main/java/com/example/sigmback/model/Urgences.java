package com.example.sigmback.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Urgences {
    @JsonProperty("Normal")Normale , @JsonProperty("Urgent")Urgente, @JsonProperty("Very Urgent")Tres_Urgente
}

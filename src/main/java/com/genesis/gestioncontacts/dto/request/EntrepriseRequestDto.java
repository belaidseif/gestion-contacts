package com.genesis.gestioncontacts.dto.request;

import jakarta.validation.constraints.NotNull;

public class EntrepriseRequestDto {

    @NotNull
    private Integer tva;

    @NotNull
    private String adresse;

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}

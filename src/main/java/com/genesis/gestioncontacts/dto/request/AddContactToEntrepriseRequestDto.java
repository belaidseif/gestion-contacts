package com.genesis.gestioncontacts.dto.request;

import jakarta.validation.constraints.NotNull;

public class AddContactToEntrepriseRequestDto {

    @NotNull
    private Long contactId;

    @NotNull
    private Long entrepriseId;

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Long getEntrepriseId() {
        return entrepriseId;
    }

    public void setEntrepriseId(Long entrepriseId) {
        this.entrepriseId = entrepriseId;
    }
}

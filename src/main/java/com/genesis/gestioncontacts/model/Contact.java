package com.genesis.gestioncontacts.model;


import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CONTACTS")

public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "NOM", nullable = false)
    private String nom;

    @Column(name = "PRENOM",nullable = false)
    private String prenom;

    @Column(name = "ADRESSE",nullable = false)
    private String adresse;

    @Column(name = "DATE_CREATION")
    private ZonedDateTime dateCreation;

    @Column(name = "TVA")
    private Integer tva;


    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE",nullable = false)
    private ContactType type;


    @ManyToMany
    @JoinTable(
            name = "entreprise_contacts",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "entreprise_id"))
    @JsonIgnore
    private Set<Entreprise> entreprises;


    public Contact(){}

    public Contact(String nom, String prenom, String adresse, Integer tva, ContactType type, Entreprise entreprise) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.tva = tva;
        this.type = type;
        dateCreation = ZonedDateTime.now();
        entreprises = new HashSet<>();
        entreprises.add(entreprise);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public ZonedDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(ZonedDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    public Set<Entreprise> getEntreprises() {
        return entreprises;
    }

    public void setEntreprises(Set<Entreprise> entreprises) {
        this.entreprises = entreprises;
    }

    public void addEnterprise(Entreprise entreprise) {
        entreprises.add(entreprise);
    }
}

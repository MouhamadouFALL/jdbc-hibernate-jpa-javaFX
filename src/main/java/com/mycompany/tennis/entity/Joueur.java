package com.mycompany.tennis.entity;


import org.hibernate.annotations.NamedQuery;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@NamedQuery(query = "select j from Joueur j where j.sexe=?0", name = "given_sexe")
@Entity
public class Joueur {

    @Id // iidique l'id de l'objet
    @GeneratedValue(strategy = GenerationType.IDENTITY) // permet la generation automatique de l'identifiant (id)
    private long id;
    private String nom;
    private String prenom;
    private Character sexe;

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public Character getSexe() {
        return sexe;
    }

    public void setSexe(Character sexe) {
        this.sexe = sexe;
    }
}

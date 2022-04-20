package com.mycompany.tennis.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Epreuve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private short annee;

    @Column(name = "type_epreuve")
    private Character typeEpreuve;

    //@ManyToOne(fetch = FetchType.EAGER) // valeur par defaut pour les relations ManyToOne
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tournoi")
    private Tournoi tournoi;

    @ManyToMany
    @JoinTable(
            name = "participants",
            joinColumns = {@JoinColumn(name = "id_epreuve")},
            inverseJoinColumns = {@JoinColumn(name = "id_joueur")}
    )
    private Set<Joueur> participants;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getAnnee() {
        return annee;
    }

    public void setAnnee(short annee) {
        this.annee = annee;
    }

    public Character getTypeEpreuve() {
        return typeEpreuve;
    }

    public void setTypeEpreuve(Character typeEpreuve) {
        this.typeEpreuve = typeEpreuve;
    }

    public Tournoi getTournoi() {
        return tournoi;
    }

    public void setTournoi(Tournoi tournoi) {
        this.tournoi = tournoi;
    }

    public Set<Joueur> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<Joueur> participants) {
        this.participants = participants;
    }
}

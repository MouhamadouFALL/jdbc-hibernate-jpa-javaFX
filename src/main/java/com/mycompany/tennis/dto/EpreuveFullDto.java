package com.mycompany.tennis.dto;

import java.util.Set;

public class EpreuveFullDto {


    private Long id;
    private short annee;
    private Character typeEpreuve;
    private TournoiDto tournoi;
    private Set<JoueurDto> participants;

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

    public TournoiDto getTournoi() {
        return tournoi;
    }

    public void setTournoi(TournoiDto tournoi) {
        this.tournoi = tournoi;
    }

    public Set<JoueurDto> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<JoueurDto> participants) {
        this.participants = participants;
    }
}

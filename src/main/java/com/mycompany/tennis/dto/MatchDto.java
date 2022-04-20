package com.mycompany.tennis.dto;

public class MatchDto {


    private Long id;
    private JoueurDto vainqueur;
    private JoueurDto finaliste;
    private EpreuveFullDto epreuve;
    private ScoreFullDto scoreFullDto;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JoueurDto getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(JoueurDto vainqueur) {
        this.vainqueur = vainqueur;
    }

    public JoueurDto getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(JoueurDto finaliste) {
        this.finaliste = finaliste;
    }

    public EpreuveFullDto getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(EpreuveFullDto epreuve) {
        this.epreuve = epreuve;
    }

    public ScoreFullDto getScoreFullDto() {
        return scoreFullDto;
    }

    public void setScoreFullDto(ScoreFullDto scoreFullDto) {
        this.scoreFullDto = scoreFullDto;
    }
}

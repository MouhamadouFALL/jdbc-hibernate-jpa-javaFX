package com.mycompany.tennis.entity;

import javax.persistence.*;

@Entity
@Table(name = "match_tennis")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_vainqueur")
    private Joueur vainqueur;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_finaliste")
    private Joueur finaliste;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_epreuve")
    private Epreuve epreuve;

    @OneToOne(mappedBy = "match", fetch = FetchType.LAZY)
    private Score score;

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Joueur getVainqueur() {
        return vainqueur;
    }

    public void setVainqueur(Joueur vainqueur) {
        this.vainqueur = vainqueur;
    }

    public Joueur getFinaliste() {
        return finaliste;
    }

    public void setFinaliste(Joueur finaliste) {
        this.finaliste = finaliste;
    }

    public Epreuve getEpreuve() {
        return epreuve;
    }

    public void setEpreuve(Epreuve epreuve) {
        this.epreuve = epreuve;
    }
}

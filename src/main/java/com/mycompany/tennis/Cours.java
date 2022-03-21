package com.mycompany.tennis;

import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.service.JoueurService;
import com.mycompany.tennis.service.MatchService;

import java.util.List;

public class Cours {

    public static void main(String[] args) {


        MatchService matchService = new MatchService();

        Match match = new Match();
        Score score = new Score();

        score.setSet1((byte)3);
        score.setSet2((byte)4);
        score.setSet3((byte)6);

        match.setScore(score);
        score.setMatch(match);

        Joueur bouh = new Joueur();
        bouh.setId(62);
        Joueur modou = new Joueur();
        modou.setId(65);

        match.setVainqueur(bouh);
        match.setFinaliste(modou);

        Epreuve epreuve = new Epreuve();
        epreuve.setId(73l);
        match.setEpreuve(epreuve);

        matchService.saveNewMatch(match);

        System.out.println("l'identifiant du match cré est : " + match.getId());

    }

}

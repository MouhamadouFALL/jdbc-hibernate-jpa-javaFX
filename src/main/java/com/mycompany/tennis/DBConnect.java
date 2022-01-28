package com.mycompany.tennis;

import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.service.JoueurService;

import java.util.List;

public class DBConnect {

    public static void main(String[] args) {
        JoueurService joueurService = new JoueurService();


        Joueur momo = new Joueur();
        momo.setSexe('M');
        momo.setNom("NDour");
        momo.setPrenom("Modou");

        joueurService.createPlayer(momo);
    }

}

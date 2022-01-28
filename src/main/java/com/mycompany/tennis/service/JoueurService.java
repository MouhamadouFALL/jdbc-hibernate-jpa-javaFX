package com.mycompany.tennis.service;

import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    // Constructeur
    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }


    public void createPlayer(Joueur joueur) {
        joueurRepository.create(joueur);
    }
}

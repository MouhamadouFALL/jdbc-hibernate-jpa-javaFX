package com.mycompany.tennis;

import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import com.mysql.cj.jdbc.MysqlDataSource;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.List;

public class DBConnect {

    public static void main(String[] args) {
        JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();

        List<Joueur> joueurs = joueurRepository.allPlayers();

        for (Joueur j : joueurs ) {
            System.out.println(j.getPrenom()+"  "+j.getNom());
        }

        Joueur bouh = new Joueur();
        bouh.setSexe('M');
        bouh.setNom("Diallo");
        bouh.setPrenom("Bouh");

        joueurRepository.createPlayer(bouh);
    }

}

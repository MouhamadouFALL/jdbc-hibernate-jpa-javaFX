package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.entity.Score;
import org.hibernate.Session;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreRepositoryImpl {

    // Créer un Match
    public void create(Score score) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(score);
        System.out.println("Score ajouté !");
    }

    public void delete(Long id){

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Score score = session.get(Score.class, id);
        session.delete(score);
        System.out.println("Score supprimé !");
    }

    public Score getById(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Score score = session.get(Score.class, id);

        return score;
    }




}

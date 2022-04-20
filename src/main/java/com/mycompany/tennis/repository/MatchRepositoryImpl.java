package com.mycompany.tennis.repository;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Match;
import org.hibernate.Session;

public class MatchRepositoryImpl {

    // Créer un Match
    public void create(Match match) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.persist(match);
        System.out.println(" Match bien ajouté !");
    }

    public void delete(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Match match = session.get(Match.class, id);
        session.delete(match);
        System.out.println("Match supprimé !");

    }

    public Match getById(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Match match = session.get(Match.class, id);

        return match;
    }




}

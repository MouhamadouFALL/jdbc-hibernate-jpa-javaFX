package com.mycompany.tennis.repository;


import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;

import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;


public class EpreuveRepositoryImpl {


    public Epreuve getById(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Epreuve epreuve = session.get(Epreuve.class, id);


        return epreuve;
    }

    public List<Epreuve> liste(String codeTournoi) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Epreuve> query = session.createQuery("select e from Epreuve e join fetch e.tournoi where e.tournoi.code=?0", Epreuve.class);
        query.setParameter(0, codeTournoi);
        List<Epreuve> epreuves = query.getResultList();
        System.out.println("Epreuves lus :");

        return epreuves;
    }


}

package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;

import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {

    private EpreuveRepositoryImpl epreuveRepository;

    // Constructeur
    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }


    public Epreuve getEpreuve(Long id) {

        Epreuve epreuve = null;
        Session session = null;
        Transaction tx = null;

        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            epreuve = epreuveRepository.getById(id);

            tx.commit();

        }
        catch (Exception e) {
            if (tx != null)
                tx.rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

        return epreuve;
    }

}

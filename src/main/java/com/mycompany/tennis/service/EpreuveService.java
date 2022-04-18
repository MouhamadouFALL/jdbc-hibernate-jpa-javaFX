package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;

import com.mycompany.tennis.dto.EpreuveDto;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class EpreuveService {

    private EpreuveRepositoryImpl epreuveRepository;

    // Constructeur
    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }


    public Epreuve getEpreuveAvecTournoi(Long id) {

        Epreuve epreuve = null;
        Session session = null;
        Transaction tx = null;

        EpreuveDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            epreuve = epreuveRepository.getById(id);

            Hibernate.initialize(epreuve.getTournoi()); // Initialiser l'objet Proxy

            tx.commit();

            dto = new EpreuveDto();

            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());
            dto.setTournoi(epreuve.getTournoi());

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

        return dto;
    }


    public Epreuve getEpreuveSansTournoi(Long id) {

        Epreuve epreuve = null;
        EpreuveDto dto = null;
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);
            tx.commit();

            dto = new EpreuveDto();

            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

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

        return dto;
    }

}

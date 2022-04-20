package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;

import com.mycompany.tennis.dto.EpreuveFullDto;
import com.mycompany.tennis.dto.EpreuveLightDto;
import com.mycompany.tennis.dto.JoueurDto;
import com.mycompany.tennis.dto.TournoiDto;
import com.mycompany.tennis.entity.Epreuve;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.HashSet;

public class EpreuveService {

    private EpreuveRepositoryImpl epreuveRepository;

    // Constructeur
    public EpreuveService() {
        this.epreuveRepository = new EpreuveRepositoryImpl();
    }


    public EpreuveFullDto getEpreuveDetaillee(Long id) {

        Epreuve epreuve = null;
        Session session = null;
        Transaction tx = null;

        EpreuveFullDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            epreuve = epreuveRepository.getById(id);

            //Hibernate.initialize(epreuve.getTournoi()); // Initialiser l'objet Proxy
            //tx.commit();

            // Convertir une entité vers une objet DTO
            dto = new EpreuveFullDto();

            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(epreuve.getTournoi().getId());
            tournoiDto.setNom(epreuve.getTournoi().getNom());
            tournoiDto.setCode(epreuve.getTournoi().getCode());
            dto.setTournoi(tournoiDto);

            dto.setParticipants(new HashSet<>());

            for (Joueur j : epreuve.getParticipants()) {
                final JoueurDto joueurDto = new JoueurDto();
                joueurDto.setId(j.getId());
                joueurDto.setNom(j.getNom());
                joueurDto.setPrenom(j.getPrenom());
                joueurDto.setSexe(j.getSexe());
                dto.getParticipants().add(joueurDto);
            }

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

        return dto;
    }


    public EpreuveLightDto getEpreuveSansTournoi(Long id) {

        Epreuve epreuve = null;
        EpreuveLightDto dto = null;
        Session session = null;
        Transaction tx = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();
            epreuve = epreuveRepository.getById(id);

            // Convertir une entité vers une DTO
            dto = new EpreuveLightDto();

            dto.setId(epreuve.getId());
            dto.setAnnee(epreuve.getAnnee());
            dto.setTypeEpreuve(epreuve.getTypeEpreuve());

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

        return dto;
    }

}

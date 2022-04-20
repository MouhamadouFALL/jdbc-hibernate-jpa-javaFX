package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.*;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private MatchRepositoryImpl matchRepository;
    //private ScoreRepositoryImpl scoreRepository;

    public MatchService () {
        this.matchRepository = new MatchRepositoryImpl();
        //this.scoreRepository = new ScoreRepositoryImpl();
    }

    public void saveNewMatch(Match match) {

        matchRepository.create(match);
        //scoreRepository.create(match.getScore());
    }

    public MatchDto getMatch(Long id) {

        Match match = null;
        Session session = null;
        Transaction tx = null;

        MatchDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            match = matchRepository.getById(id);


            // Convertir une entité vers une objet DTO
            dto = new MatchDto();
            dto.setId(match.getId());

            JoueurDto finalisteDto = new JoueurDto();
            finalisteDto.setId(match.getFinaliste().getId());
            finalisteDto.setNom(match.getFinaliste().getNom());
            finalisteDto.setPrenom(match.getFinaliste().getPrenom());
            finalisteDto.setSexe(match.getFinaliste().getSexe());

            dto.setFinaliste(finalisteDto);

            JoueurDto vainqueurDto = new JoueurDto();
            vainqueurDto.setId(match.getFinaliste().getId());
            vainqueurDto.setNom(match.getFinaliste().getNom());
            vainqueurDto.setPrenom(match.getFinaliste().getPrenom());
            vainqueurDto.setSexe(match.getFinaliste().getSexe());

            dto.setVainqueur(vainqueurDto);

            EpreuveFullDto epreuveFullDto = new EpreuveFullDto();

            epreuveFullDto.setId(match.getEpreuve().getId());
            epreuveFullDto.setAnnee(match.getEpreuve().getAnnee());
            epreuveFullDto.setTypeEpreuve(match.getEpreuve().getTypeEpreuve());

            TournoiDto tournoiDto = new TournoiDto();
            tournoiDto.setId(match.getEpreuve().getTournoi().getId());
            tournoiDto.setNom(match.getEpreuve().getTournoi().getNom());
            tournoiDto.setCode(match.getEpreuve().getTournoi().getCode());
            epreuveFullDto.setTournoi(tournoiDto);

            dto.setEpreuve(epreuveFullDto);

            ScoreFullDto scoreFullDto = new ScoreFullDto();
            scoreFullDto.setId(match.getScore().getId());
            scoreFullDto.setSet1(match.getScore().getSet1());
            scoreFullDto.setSet2(match.getScore().getSet2());
            scoreFullDto.setSet3(match.getScore().getSet3());
            scoreFullDto.setSet4(match.getScore().getSet4());
            scoreFullDto.setSet5(match.getScore().getSet5());

            dto.setScoreFullDto(scoreFullDto);
            scoreFullDto.setMatchDto(dto);

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

package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.*;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.repository.EpreuveRepositoryImpl;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MatchService {

    private MatchRepositoryImpl matchRepository;
    private EpreuveRepositoryImpl epreuveRepository;
    private ScoreRepositoryImpl scoreRepository;
    private JoueurRepositoryImpl joueurRepository;

    public MatchService () {
        this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();
        this.epreuveRepository = new EpreuveRepositoryImpl();
        this.joueurRepository = new JoueurRepositoryImpl();
    }

    public void saveNewMatch(Match match) {

        matchRepository.create(match);
        //scoreRepository.create(match.getScore());
    }

    public void createMatch(MatchDto dto) {

        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            match = new Match();

            match.setEpreuve(epreuveRepository.getById(dto.getEpreuve().getId()));
            match.setVainqueur(joueurRepository.getById(dto.getVainqueur().getId()));
            match.setFinaliste(joueurRepository.getById(dto.getFinaliste().getId()));

            Score score = new Score();
            score.setMatch(match);
            match.setScore(score);
            score.setSet1(dto.getScoreFullDto().getSet1());
            score.setSet2(dto.getScoreFullDto().getSet2());
            score.setSet3(dto.getScoreFullDto().getSet3());
            score.setSet4(dto.getScoreFullDto().getSet4());
            score.setSet5(dto.getScoreFullDto().getSet5());

            tx.commit();
        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("ERROR : " + e.getClass() + " : " + e.getMessage());
        }
        finally {
            if (session != null) session.close();
        }
    }

    public void deleteMatch(Long id) {

        Session session = null;
        Transaction tx = null;
        Match match = null;

        try{
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            matchRepository.delete(id);

            tx.commit();
        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("ERROR : " + e.getClass() + " : " + e.getMessage());
        }
        finally {
            if (session != null) session.close();
        }
    }

    public void tapisVert(Long id) {

        Session session = null;
        Transaction tx = null;
        Match match = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            match = matchRepository.getById(id);

            Joueur ancienVainueur = match.getVainqueur();
            match.setVainqueur(match.getFinaliste());
            match.setFinaliste(ancienVainueur);

            match.getScore().setSet1((byte) 0);
            match.getScore().setSet2((byte) 0);
            match.getScore().setSet3((byte) 0);
            match.getScore().setSet4((byte) 0);
            match.getScore().setSet5((byte) 0);

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
            vainqueurDto.setId(match.getVainqueur().getId());
            vainqueurDto.setNom(match.getVainqueur().getNom());
            vainqueurDto.setPrenom(match.getVainqueur().getPrenom());
            vainqueurDto.setSexe(match.getVainqueur().getSexe());

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

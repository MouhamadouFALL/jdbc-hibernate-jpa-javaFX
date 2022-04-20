package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.dto.MatchDto;
import com.mycompany.tennis.dto.ScoreFullDto;
import com.mycompany.tennis.entity.Score;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class ScoreService {

    private ScoreRepositoryImpl scoreRepository;

    public ScoreService() {
        this.scoreRepository = new ScoreRepositoryImpl();
    }


    public void deletescore(Long id) {

        Session session = null;
        Transaction tx = null;
        Score score = null;

        try{

            session = HibernateUtil.getSessionFactory().getCurrentSession();

            tx = session.beginTransaction();

            scoreRepository.delete(id);

            tx.commit();
        }
        catch (Exception e) {
            if (tx != null) tx.rollback();
            System.err.println("Error : " + e.getClass() + " : " + e.getMessage());
        }
        finally {
            if (session != null){
                session.close();
            }
        }
    }

    public ScoreFullDto getScore(Long id) {

        Score score = null;
        Session session = null;
        Transaction tx = null;

        ScoreFullDto dto = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            tx = session.beginTransaction();

            score = scoreRepository.getById(id);


            // Convertir une entité vers une objet DTO
            dto = new ScoreFullDto();
            dto.setId(score.getId());
            dto.setSet1(score.getSet1());
            dto.setSet2(score.getSet2());
            dto.setSet3(score.getSet3());
            dto.setSet4(score.getSet4());
            dto.setSet5(score.getSet5());

            MatchDto matchDto = new MatchDto();
            matchDto.setId(score.getMatch().getId());

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

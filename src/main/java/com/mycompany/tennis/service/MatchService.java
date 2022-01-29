package com.mycompany.tennis.service;

import com.mycompany.tennis.dao.MatchDaoImpl;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;

public class MatchService {

    /*private MatchRepositoryImpl matchRepository;
    private ScoreRepositoryImpl scoreRepository;*/
    private MatchDaoImpl matchDao;

    public MatchService () {
        /*this.matchRepository = new MatchRepositoryImpl();
        this.scoreRepository = new ScoreRepositoryImpl();*/
        matchDao = new MatchDaoImpl();
    }

    public void saveNewMatch(Match match) {
        matchDao.createMatchwithscore(match);
        /*matchRepository.create(match);
        scoreRepository.create(match.getScore());*/
    }
}

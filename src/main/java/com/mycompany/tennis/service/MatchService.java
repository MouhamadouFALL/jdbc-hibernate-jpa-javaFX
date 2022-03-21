package com.mycompany.tennis.service;

import com.mycompany.tennis.dao.MatchDoaImpl;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.repository.MatchRepositoryImpl;
import com.mycompany.tennis.repository.ScoreRepositoryImpl;

public class MatchService {

    //private MatchRepositoryImpl matchRepository;
    //private ScoreRepositoryImpl scoreRepository;

    private MatchDoaImpl matchDoa;

    public MatchService () {
        //this.matchRepository = new MatchRepositoryImpl();
        //this.scoreRepository = new ScoreRepositoryImpl();
        matchDoa = new MatchDoaImpl();
    }

    public void saveNewMatch(Match match) {

        //matchRepository.create(match);
        //scoreRepository.create(match.getScore());

        matchDoa.newMatchWithScore(match);
    }
}

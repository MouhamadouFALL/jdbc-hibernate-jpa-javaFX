package com.mycompany.tennis.dao;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDoaImpl {

    public void newMatchWithScore(Match match) {

        Connection conn = null;

        try{

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            conn.setAutoCommit(false);

            PreparedStatement preparedStatement = conn.prepareStatement("insert into match_tennis(id_epreuve, id_vainqueur, id_finaliste) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            // inserer dans la base de donnees
            preparedStatement.executeUpdate();

            // Recupérer les id autogenerés
            ResultSet rs = preparedStatement.getGeneratedKeys();

            Long ident = null;
            if (rs.next()) {
                match.setId(rs.getLong(1));
                ident = match.getId();
            }

            System.out.println("Match "+ident+" a été bien enregistré.");


            //============================================= Creer le score ==============================================

            PreparedStatement preparedStatementScore = conn.prepareStatement("insert into score_vainqueur(id_match, set_1, set_2, set_3, set_4, set_5) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatementScore.setLong(1, match.getScore().getMatch().getId());
            if (match.getScore().getSet1() == null) {
                preparedStatementScore.setNull(2, Types.TINYINT);
            }
            else {
                preparedStatementScore.setByte(2, match.getScore().getSet1());
            }
            if (match.getScore().getSet2() == null) {
                preparedStatementScore.setNull(3, Types.TINYINT);
            }
            else {
                preparedStatementScore.setByte(3, match.getScore().getSet2());
            }
            if (match.getScore().getSet3() == null) {
                preparedStatementScore.setNull(4, Types.TINYINT);
            }
            else {
                preparedStatementScore.setByte(4, match.getScore().getSet3());
            }

            if (match.getScore().getSet4() == null) {
                preparedStatementScore.setNull(5, Types.TINYINT);
            }
            else {
                preparedStatementScore.setByte(5, match.getScore().getSet4());
            }
            if (match.getScore().getSet5() == null) {
                preparedStatementScore.setNull(6, Types.TINYINT);
            }
            else {
                preparedStatementScore.setByte(6, match.getScore().getSet5());
            }

            // inserer dans la base de donnees
            preparedStatementScore.executeUpdate();

            // Recupérer les id autogenerés
            ResultSet res = preparedStatementScore.getGeneratedKeys();

            Long idScore = null;
            if (res.next()) {
                match.getScore().setId(res.getLong(1));
                idScore = match.getScore().getId();
            }

            System.out.println(" Score numéro : "+idScore+" a été bien enregistré.");

            conn.commit();

        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            }catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        finally {
            try {
                if (conn != null) conn.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}

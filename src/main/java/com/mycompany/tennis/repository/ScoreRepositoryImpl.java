package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Match;
import com.mycompany.tennis.entity.Score;

import javax.sql.DataSource;
import java.sql.*;

public class ScoreRepositoryImpl {

    // Créer un Match
    public void create(Score score) {

        Connection conn = null;

        try{

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("insert into score_vainqueur(id_match, set_1, set_2, set_3, set_4, set_5) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setLong(1, score.getMatch().getId());
            if (score.getSet1() == null) {
                preparedStatement.setNull(2, Types.TINYINT);
            }
            else {
                preparedStatement.setByte(2, score.getSet1());
            }
            if (score.getSet2() == null) {
                preparedStatement.setNull(3, Types.TINYINT);
            }
            else {
                preparedStatement.setByte(3, score.getSet2());
            }
            if (score.getSet3() == null) {
                preparedStatement.setNull(4, Types.TINYINT);
            }
            else {
                preparedStatement.setByte(4, score.getSet3());
            }

            if (score.getSet4() == null) {
                preparedStatement.setNull(5, Types.TINYINT);
            }
            else {
                preparedStatement.setByte(5, score.getSet4());
            }
            if (score.getSet5() == null) {
                preparedStatement.setNull(6, Types.TINYINT);
            }
            else {
                preparedStatement.setByte(6, score.getSet5());
            }

            // inserer dans la base de donnees
            preparedStatement.executeUpdate();

            // Recupérer les id autogenerés
            ResultSet rs = preparedStatement.getGeneratedKeys();

            Long ident = null;
            if (rs.next()) {
                score.setId(rs.getLong(1));
                ident = score.getId();
            }

            System.out.println(" Score numéro : "+ident+" a été bien enregistré.");

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

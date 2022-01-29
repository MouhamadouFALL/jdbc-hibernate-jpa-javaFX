package com.mycompany.tennis.dao;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchDaoImpl {

    public void createMatchwithscore(Match match) {

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


            PreparedStatement preparedStatement2 = conn.prepareStatement("insert into score_vainqueur(id_match, set_1, set_2, set_3, set_4, set_5) values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatement2.setLong(1, match.getScore().getMatch().getId());
            if (match.getScore().getSet1() == null) {
                preparedStatement2.setNull(2, Types.TINYINT);
            }
            else {
                preparedStatement2.setByte(2, match.getScore().getSet1());
            }
            if (match.getScore().getSet2() == null) {
                preparedStatement2.setNull(3, Types.TINYINT);
            }
            else {
                preparedStatement2.setByte(3, match.getScore().getSet2());
            }
            if (match.getScore().getSet3() == null) {
                preparedStatement2.setNull(4, Types.TINYINT);
            }
            else {
                preparedStatement2.setByte(4, match.getScore().getSet3());
            }

            if (match.getScore().getSet4() == null) {
                preparedStatement2.setNull(5, Types.TINYINT);
            }
            else {
                preparedStatement2.setByte(5, match.getScore().getSet4());
            }
            if (match.getScore().getSet5() == null) {
                preparedStatement2.setNull(6, Types.TINYINT);
            }
            else {
                preparedStatement2.setByte(6, match.getScore().getSet5());
            }

            // inserer dans la base de donnees
            preparedStatement2.executeUpdate();

            // Recupérer les id autogenerés
            ResultSet rs2 = preparedStatement.getGeneratedKeys();

            Long ident2 = null;
            if (rs2.next()) {
                match.getScore().setId(rs.getLong(1));
                ident = match.getScore().getId();
            }

            System.out.println(" Score numéro : "+ident2+" a été bien enregistré.");

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

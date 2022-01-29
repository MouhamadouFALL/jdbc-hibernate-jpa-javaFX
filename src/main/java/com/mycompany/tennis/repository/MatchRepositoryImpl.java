package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Match;

import javax.sql.DataSource;
import java.sql.*;

public class MatchRepositoryImpl {

    // Créer un Match
    public void create(Match match) {

        Connection conn = null;

        try{

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

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

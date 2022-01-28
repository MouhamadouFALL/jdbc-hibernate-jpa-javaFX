package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.entity.Joueur;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurRepositoryImpl {

    // Définir le opérations du CRUD

    public void create(Joueur joueur) {

        Connection conn = null;

        try{

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatementInsert = conn.prepareStatement("insert into joueur(nom, prenom, sexe) values(?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            preparedStatementInsert.setString(1, joueur.getNom());
            preparedStatementInsert.setString(2, joueur.getPrenom());
            preparedStatementInsert.setString(3, joueur.getSexe().toString());

            // inserer dans la base de donnees
            preparedStatementInsert.executeUpdate();
            // Recupérer les id autogenerés
            ResultSet rs = preparedStatementInsert.getGeneratedKeys();

            Long ident = null;
            if (rs.next()) {
                joueur.setId(rs.getLong(1));
                ident = joueur.getId();
            }

            System.out.println("Joueur "+ident+" a été bien enregistré.");

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


    public void update(Joueur joueur) {

        Connection conn = null;

        try{

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatementUpdate = conn.prepareStatement("update joueur set nom=?, prenom=?, sexe=? where  id=?");
            preparedStatementUpdate.setString(1, joueur.getNom());
            preparedStatementUpdate.setString(2, joueur.getPrenom());
            preparedStatementUpdate.setString(3, joueur.getSexe().toString());
            preparedStatementUpdate.setLong(4, joueur.getId());

            // inserer dans la base de donnees
            preparedStatementUpdate.executeUpdate();

            System.out.println("Joueur modifié");

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


    public void delete(long id) {

        Connection conn = null;

        try{

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatementDelete = conn.prepareStatement("delete from joueur where id=?");
            preparedStatementDelete.setLong(1, id);


            // inserer dans la base de donnees
            preparedStatementDelete.executeUpdate();

            System.out.println("Joueur supprimé");

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


    public Joueur getById(long id) {

        Connection conn = null;

        Joueur joueur = null;

        try{

            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatementGet = conn.prepareStatement("select nom, prenom, sexe from joueur where id=?");
            preparedStatementGet.setLong(1, id);

            // obtenir un player
            ResultSet rs = preparedStatementGet.executeQuery();

            if (rs.next()) {
                joueur = new Joueur();
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("SEXE").charAt(0));
            }

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

        return joueur;
    }

    public List<Joueur> all() {

        Connection conn = null;
        List<Joueur> joueurs = new ArrayList<>();

        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            PreparedStatement preparedStatement = conn.prepareStatement("select id, nom, prenom, sexe from joueur");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                Joueur joueur = new Joueur();
                joueur.setId(rs.getLong("ID"));
                joueur.setNom(rs.getString("NOM"));
                joueur.setPrenom(rs.getString("PRENOM"));
                joueur.setSexe(rs.getString("Sexe").charAt(0));
                // Ajouter le joueur dans la collection
                joueurs.add(joueur);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conn != null) conn.rollback();
            }
            catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally {
            try {
                if (conn != null) conn.close();
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return joueurs;
    }


}

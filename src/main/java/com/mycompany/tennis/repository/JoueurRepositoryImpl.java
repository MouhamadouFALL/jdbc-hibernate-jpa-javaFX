package com.mycompany.tennis.repository;

import com.mycompany.tennis.DataSourceProvider;
import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Joueur;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class JoueurRepositoryImpl {

    public void rename(Long id, String newName){


    }

    // Définir le opérations du CRUD
    public void create(Joueur joueur) {

        Session session = null;
        Transaction tx;

        try{

            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();

            session.persist(joueur); // met l'objet dans la session
            //session.flush(); // déclenche automatiquement la requête INSERT et permet de synchroniser L'état la session et la base de données, mais non recommandé

            tx.commit();

            System.out.println("Joueur bien enregistré.");

        }
        catch (Throwable t) {
            t.printStackTrace();
        }
        finally {
            if (session != null) session.close();
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

        Joueur joueur = getById(id);

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        session.delete(joueur);

        System.out.println("Joueur bien supprimé");

    }


    public Joueur getById(long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Joueur joueur = session.get(Joueur.class, id);

        System.out.println("joueur lu");

        return joueur;
    }

    public List<Joueur> liste(char sexe) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Query<Joueur> query = session.createNamedQuery("given_sexe", Joueur.class);
        query.setParameter(0, sexe);
        List<Joueur> joueurs = query.getResultList();
        System.out.println("Joueurs lus :");

        return joueurs;
    }

}

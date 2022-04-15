package com.mycompany.tennis.service;

import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Joueur;
import com.mycompany.tennis.repository.JoueurRepositoryImpl;
import org.hibernate.Session;

public class JoueurService {

    private JoueurRepositoryImpl joueurRepository;

    // Constructeur
    public JoueurService() {
        this.joueurRepository = new JoueurRepositoryImpl();
    }


    public void createPlayer(Joueur joueur) {

        Session session = null;

        session = HibernateUtil.getSessionFactory().getCurrentSession(); // permet de recuperer la session courante

        session.persist(joueur);

        System.out.println("joueur modifié !");

    }

    public Joueur getJoueur(Long id) {

        Joueur joueur = null;
        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            joueur = joueurRepository.getById(id);

            session.getTransaction().commit();

        }
        catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

        return joueur;
    }

    public void rename(Long id, String newName){

        Joueur joueur = getJoueur(id);

        Session session = null;
        try {

            session = HibernateUtil.getSessionFactory().getCurrentSession(); // permet de recuperer la session courante
            session.beginTransaction();

            joueur.setNom(newName);
            Joueur joueur1 = (Joueur)session.merge(joueur);

            session.getTransaction().commit();

            System.out.println("joueur modifié !");

        }
        catch (Exception e) {
            if (session.getTransaction() != null)
                session.getTransaction().rollback();
            e.printStackTrace();
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void delete(Long id){

        Session session = null;

        try {
            session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();

            joueurRepository.delete(id);

            session.getTransaction().commit();
        }
        catch (Exception e) {
            if ( session.getTransaction() != null )
                session.getTransaction().rollback();
        }
        finally {
            if (session != null)
                session.close();
        }


    }
}

package com.mycompany.tennis.repository;


import com.mycompany.tennis.HibernateUtil;
import com.mycompany.tennis.entity.Epreuve;

import org.hibernate.Session;


public class EpreuveRepositoryImpl {


    public Epreuve getById(Long id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Epreuve epreuve = session.get(Epreuve.class, id);

        System.out.println("Epreuve lu : ");

        return epreuve;
    }




}

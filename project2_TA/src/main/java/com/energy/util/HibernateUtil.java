package com.energy.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    public static SessionFactory sf =
            new Configuration()
                    .configure("hibernate.cfg.xml")
                    .buildSessionFactory();

    private static Session session;

    public static Session getSession(){
        if(session == null){
            session = sf.openSession();
        }
        return session;
    }

    public static void closeSession(){
        session.close();
        session = null;
        sf.close();
    }

}

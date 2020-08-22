package ru.sysout;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

public class HibernateUtil {
    // Private constructor; Class cannot be initialized
    private HibernateUtil() {
    }

    private static SessionFactory sessionFactory;

    // create sessionFactory only once
    static {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building
            // the SessionFactory so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void doInHibernate(Consumer<Session> callable) {
        Session session = null;
        Transaction txn = null;
        try {
            session = getSessionFactory().openSession();
            txn = session.beginTransaction();

            callable.accept(session);

            txn.commit();
        } catch (Throwable t) {
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public static <T> T doInHibernate(Function<Session, T> callable) {
        T result = null;
        Session session = null;
        Transaction txn = null;
        try {
            session = getSessionFactory().openSession();
            txn = session.beginTransaction();

            result = callable.apply(session);

            txn.commit();
        } catch (Throwable t) {
            txn.rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }

}
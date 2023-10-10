package com.writershelper.repository.writer;

import com.google.common.base.Throwables;
import com.writershelper.model.Writer;
import com.writershelper.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class JdbcWriterRepositoryImpl implements WriterRepository {

    private static final String GET_ALL_HQL = "FROM Writer";

    @Override
    public Writer save(Writer writer) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(writer);

            transaction.commit();
            return writer;
        } catch (Exception e) {
            System.err.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save writer", e);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public Writer update(Writer writer) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(writer);

            transaction.commit();
            return writer;
        } catch (Exception e) {
            System.err.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update writer", e);
        }
    }

    @Override
    public List<Writer> getAll() {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Writer> query = session.createQuery(GET_ALL_HQL, Writer.class);

            transaction.commit();
            return query.list();
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to get writer", e);
        }
    }

    @Override
    public Optional<Writer> get(Long id) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Writer writer = session.get(Writer.class, id);

            transaction.commit();
            return Optional.ofNullable(writer);
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to get writer", e);
        }
    }
}

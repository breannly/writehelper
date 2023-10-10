package com.writershelper.repository.label;

import com.google.common.base.Throwables;
import com.writershelper.model.Label;
import com.writershelper.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class JdbcLabelRepositoryImpl implements LabelRepository {

    private static final String GET_FROM_IDS_HQL = "FROM Label WHERE id IN (:ids)";
    private static final String GET_ALL_HQL = "FROM Label";

    @Override
    public Label save(Label label) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(label);

            transaction.commit();
            return label;
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save label", e);
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public Label update(Label label) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(label);

            transaction.commit();
            return label;
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update label", e);
        }
    }

    @Override
    public List<Label> getAll() {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Label> query = session.createQuery(GET_ALL_HQL, Label.class);

            transaction.commit();
            return query.list();
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to get label", e);
        }
    }

    @Override
    public Optional<Label> get(Long id) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Label label = session.get(Label.class, id);

            transaction.commit();
            return Optional.ofNullable(label);
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to get label", e);
        }
    }

    @Override
    public List<Label> get(List<Long> ids) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Label> query = session.createQuery(GET_FROM_IDS_HQL, Label.class);
            query.setParameterList("ids", ids);

            transaction.commit();
            return query.list();
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to get label", e);
        }
    }
}
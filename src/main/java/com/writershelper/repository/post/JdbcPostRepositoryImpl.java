package com.writershelper.repository.post;

import com.google.common.base.Throwables;
import com.writershelper.model.Post;
import com.writershelper.utils.HibernateSessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class JdbcPostRepositoryImpl implements PostRepository {

    private static final String GET_ALL_HQL = "FROM Post";

    @Override
    public Post save(Post post) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.persist(post);

            transaction.commit();
            return post;
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
    public Post update(Post post) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            session.update(post);

            transaction.commit();
            return post;
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to update label", e);
        }
    }

    @Override
    public List<Post> getAll() {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<Post> query = session.createQuery(GET_ALL_HQL, Post.class);

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
    public Optional<Post> get(Long id) {
        Transaction transaction = null;
        try (Session session =  HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Post post = session.get(Post.class, id);

            transaction.commit();
            return Optional.ofNullable(post);
        } catch (Exception e) {
            System.out.println(Throwables.getStackTraceAsString(e));
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to get label", e);
        }
    }
}

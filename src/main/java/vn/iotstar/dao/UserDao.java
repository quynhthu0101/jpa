package vn.iotstar.dao;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import vn.iotstar.configs.JpaConfig;
import vn.iotstar.entity.User;

public class UserDao implements IUserDao {

	@Override
    public User get(int id) {
        EntityManager enma = JpaConfig.getEntityManager();
        try {
            return enma.find(User.class, id);
        } finally {
            enma.close();
        }
    }

    @Override
    public void insert(User user) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            enma.persist(user);
            trans.commit();
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            throw e;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistEmail(String email) {
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM User u WHERE u.email = :email";
        try {
            TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
            query.setParameter("email", email);
            return query.getSingleResult() > 0;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistUsername(String username) {
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM User u WHERE u.username = :username";
        try {
            TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
            query.setParameter("username", username);
            return query.getSingleResult() > 0;
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean checkExistPhone(String phone) {
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT COUNT(u) FROM User u WHERE u.phone = :phone";
        try {
            TypedQuery<Long> query = enma.createQuery(jpql, Long.class);
            query.setParameter("phone", phone);
            return query.getSingleResult() > 0;
        } finally {
            enma.close();
        }
    }

    @Override
    public User findByUserName(String username) {
        EntityManager enma = JpaConfig.getEntityManager();
        String jpql = "SELECT u FROM User u WHERE u.username = :username";
        try {
            TypedQuery<User> query = enma.createQuery(jpql, User.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } finally {
            enma.close();
        }
    }

    @Override
    public List<User> findAll() {
        EntityManager enma = JpaConfig.getEntityManager();
        try {
            TypedQuery<User> query = enma.createNamedQuery("User.findAll", User.class);
            return query.getResultList();
        } finally {
            enma.close();
        }
    }

    @Override
    public boolean changePassword(String username, String newPassword) {
        EntityManager enma = JpaConfig.getEntityManager();
        EntityTransaction trans = enma.getTransaction();
        try {
            trans.begin();
            String jpql = "UPDATE User u SET u.password = :newPassword WHERE u.username = :username";
            Query query = enma.createQuery(jpql);
            query.setParameter("newPassword", newPassword);
            query.setParameter("username", username);
            int updated = query.executeUpdate();
            trans.commit();
            return updated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            trans.rollback();
            return false;
        } finally {
            enma.close();
        }
    }
    
    @Override
    public void delete(int id) throws Exception {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            User user = em.find(User.class, id);
            if (user != null) {
                em.remove(user);
            } else {
                throw new Exception("User not found");
            }
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void update(User user) {
        EntityManager em = JpaConfig.getEntityManager();
        EntityTransaction trans = em.getTransaction();
        try {
            trans.begin();
            em.merge(user);
            trans.commit();
        } catch (Exception e) {
            trans.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}

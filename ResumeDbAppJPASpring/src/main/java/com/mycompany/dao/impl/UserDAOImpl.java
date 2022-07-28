package com.mycompany.dao.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
@Repository("userDao1")
@Transactional
public class UserDAOImpl implements UserDaoInter {

    private static final BCrypt.Hasher crypt = BCrypt.withDefaults();
    private static final BCrypt.Verifyer verifyer = BCrypt.verifyer();
    @PersistenceContext
    EntityManager manager;

    @Override
    public List<User> getAll() {
        Query q = manager.createQuery("select u from User u", User.class);
        List<User> result = q.getResultList();
        return result;
    }

    @Override
    public List<User> getAllBySearch(String name, String surname, Integer nationalityId) {
        String jpql = "select u from User u where 1=1";
        if (name != null && !name.trim().isEmpty()) {
            jpql += " and u.name=:name";
        }

        if (surname != null && !surname.trim().isEmpty()) {
            jpql += " and u.surname=:surname?";
        }

        if (nationalityId != null) {
            jpql += " and u.nationality.id=:nid";
        }
        Query q = manager.createQuery(jpql, User.class);
        if (name != null && !name.trim().isEmpty()) {
            q.setParameter("name", name);
        }

        if (surname != null && !surname.trim().isEmpty()) {
            q.setParameter("surname", surname);
        }

        if (nationalityId != null) {
            q.setParameter("nid", nationalityId);
        }

        List<User> result = q.getResultList();
        return result;
    }

    @Override
    public User getByEmailAndPassword(String email, String password) {
        Query q = manager.createQuery("select u from User u where u.email=:email and u.password=:password", User.class);
        q.setParameter("email", email);
        q.setParameter("password", password);
        List<User> list = q.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public User getByEmail(String email) {
        Query q = manager.createQuery("select u from User u where u.email=:email", User.class);
        q.setParameter("email", email);
        List<User> list = q.getResultList();

        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public User getByEmailCB(String email) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<User> q1 = cb.createQuery(User.class);
        Root<User> postRoot = q1.from(User.class);
        CriteriaQuery<User> q2 = q1.where(cb.equal(postRoot.get("email"), email));
        Query query = manager.createQuery(q2);
        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public User getByEmailNQ(String email) {
        Query query = manager.createNamedQuery("User.findByEmail", User.class);
        query.setParameter("email", email);
        List<User> list = query.getResultList();
        if (list.size() == 1) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public User getById(int userId) {
        User u = manager.find(User.class, userId);
        return u;
    }

    @Override
    public boolean removeUser(int id) {
        User u = manager.find(User.class, id);
        manager.remove(u);
        return true;
    }

    @Override

    public boolean updateUser(User u) {
        manager.merge(u);
        return true;
    }

    @Override
    public boolean addUser(User u) {
        u.setPassword(crypt.hashToString(4, u.getPassword().toCharArray()));
        manager.persist(u);
        return true;
    }
}

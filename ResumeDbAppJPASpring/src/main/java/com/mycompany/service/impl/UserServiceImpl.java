package com.mycompany.service.impl;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mycompany.dao.inter.UserDaoInter;
import com.mycompany.entity.User;
import com.mycompany.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserServiceInter {

    private static final BCrypt.Hasher crypt = BCrypt.withDefaults();
    private static final BCrypt.Verifyer verifyer = BCrypt.verifyer();
    @PersistenceContext
    EntityManager manager;
    @Autowired
    @Qualifier("userDao1")
    private UserDaoInter userDao;

    @Override
    public List<User> getAll() {
        Query q = manager.createQuery("select u from User u", User.class);
        List<User> result = q.getResultList();
        return result;
    }

    @Override
    public List<User> getAllBySearch(String name, String surname, Integer nationalityId) {
        return userDao.getAllBySearch(name, surname, nationalityId);
    }

    @Override
    public User getByEmailAndPassword(String email, String password) throws Exception {
        return userDao.getByEmailAndPassword(email, password);
    }

    @Override
    public User getByEmail(String email) {
        return userDao.getByEmail(email);
    }

    @Override
    public User getByEmailCB(String email) {
        return userDao.getByEmailCB(email);
    }

    @Override
    public User getByEmailNQ(String email) {
        return userDao.getByEmailNQ(email);
    }

    @Override
    public User getById(int userId) {
        return userDao.getById(userId);
    }

    @Override
    public boolean removeUser(int id) {
        return userDao.removeUser(id);
    }

    @Override
    public boolean updateUser(User u) {
        return userDao.updateUser(u);
    }

    @Override
    public boolean addUser(User u) {
        return userDao.addUser(u);
    }
}

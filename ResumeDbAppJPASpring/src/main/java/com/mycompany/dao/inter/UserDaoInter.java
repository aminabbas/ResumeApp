package com.mycompany.dao.inter;

import com.mycompany.entity.User;


import java.util.List;

public interface UserDaoInter {

    public List<User> getAll();

    public List<User> getAllBySearch(String name, String surname, Integer nationalityId);

    public User getByEmailAndPassword(String email, String password) throws Exception;

    public User getByEmail(String email);

    public User getByEmailCB(String email);

    public User getByEmailNQ(String email);

    public boolean removeUser(int id);

    public boolean updateUser(User u);

    public User getById(int id);

    public boolean addUser(User u);

}

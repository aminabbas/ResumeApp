package com.mycompany.dao.inter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.sql.Connection;
import java.sql.DriverManager;

public abstract class AbstractDao {

    public static Connection connect() throws Exception {
      //  Class.forName("com.mysql.cj.jdbc.Driver");//Java SE-de driver avtomatik upload olunur ona gore buna ehtiyac yoxdur.
        //Amma Java EE-de bunu mutleq etmek lazimdir cunki avtomatik yuklenmir

        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "12345";

        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
private static EntityManagerFactory factory = null;
    public EntityManager getManager() {
        if(factory == null){
           factory = Persistence.createEntityManagerFactory("resumeAppPu"); 
        }
        EntityManager manager = factory.createEntityManager();
        return manager;
    }
    
    public void closeFactory(){
        factory.close();
        
    }
}

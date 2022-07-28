package com.mycompany;

import com.mycompany.dao.impl.UserRepository;
import com.mycompany.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableCaching
public class ResumeDbAppJpaSpringApplication {
    public static void main(String[] args) {
        SpringApplication.run(ResumeDbAppJpaSpringApplication.class, args);
    }

//@Autowired
//    @Qualifier("userDao1")
//private UserDaoInter userDao;
//    @Bean
//    public CommandLineRunner run() {
//        CommandLineRunner clr = new CommandLineRunner() {
//
//            @Override
//            public void run(String... args) throws Exception {
//                List<User> allUser = userDao.getAllBySearch(null,null,null);
//                User u = allUser.get(0);
//                u.setName(u.getName().toLowerCase());
//                userDao.updateUser(u);
//
//            }
//        };
//        return clr;
//    }





    @Autowired
    private UserRepository userRepository;
    @Bean
    CommandLineRunner run() {
        CommandLineRunner clr = new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                List<User> allUser = userRepository.findAll();
                System.out.println(allUser);
            }
        };
        return clr;
    }

}

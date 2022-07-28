package com.mycompany.controller;

import com.mycompany.entity.User;
import com.mycompany.form.UserForm;
import com.mycompany.service.inter.UserServiceInter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserServiceInter userService;

//    @RequestMapping(method = RequestMethod.GET)
//    public String index(HttpServletRequest request, HttpServletResponse response) throws IOException {
//        String nationaloityIdStr = request.getParameter("nid");
//        Integer nationalityId = null;
//        if (nationaloityIdStr != null && !nationaloityIdStr.trim().isEmpty()) {
//            nationalityId = Integer.parseInt(nationaloityIdStr);
//        }
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        List<User> allUser = userService.getAllBySearch(name, surname, nationalityId);
//        if (allUser.isEmpty()) {
//            throw new IllegalArgumentException("User don't exists");
//        }
//        request.setAttribute("allUser", allUser);
//        return "users";
//    }

    @RequestMapping(method = RequestMethod.GET,value = "users")
    public ModelAndView index(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "surname", required = false) String surname,
            @RequestParam(value = "nid", required = false) Integer nationalityId
    ) throws IOException {
        List<User> allUser = userService.getAllBySearch(name, surname, nationalityId);
        if (allUser.isEmpty()) {
            throw new IllegalArgumentException("User doesn't exists");
        }
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("allUser", allUser);
        return mv;
    }

    @RequestMapping(method = RequestMethod.GET,value = "usersm")
    public ModelAndView indexM(
           @RequestBody UserForm u
    ) throws IOException {
        List<User> allUser = userService.getAllBySearch(u.getName(),u.getSurname(),u.getNationalityId());
        if (allUser.isEmpty()) {
            throw new IllegalArgumentException("User doesn't exist");
        }
        ModelAndView mv = new ModelAndView("users");
        mv.addObject("user",new UserForm());
        mv.addObject("allUser", allUser);
        return mv;
    }

}







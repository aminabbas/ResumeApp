<%-- 
    Document   : user
    Created on : 22-Mar-2022, 16:26:44
    Author     : Zeynəb
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.mycompany.entity.User" %>
<%@ page import="com.mycompany.entity.Country" %>
<%@ page import="com.mycompany.dao.inter.CountryDaoInter" %>
<%@ page import="com.mycompany.service.inter.UserServiceInter" %>
<%@ page import="com.mycompany.dao.impl.CountryDaoImpl" %>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="../assets/css/users_page.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>JSP Page</title>
</head>
<body>
<%
    //    if(request.getAttribute("owner") == null){
//        response.sendRedirect("error.jsp?msg=not found!!!");
//        return;
//    }
    User u = (User) request.getAttribute("user");
%>
<div class="container mycontainer">
    <div class="row">
        <div class="col-4">
            <form action="userDetails" method="POST">

                <input type="hidden" name="id" value="<%=u.getId()%>"/>
                <input type="hidden" name="action" value="update">
                <div class="form-group">
                    <label>name:</label>
                    <input class="form-control " type="text" name="name" value="<%=u.getName()%>"/>
                </div>

                <div class="control_panel_group">
                    <label>surname:</label>
                    <input class="form-control " type="text" name="surname" value="<%=u.getSurname()%>"/>
                </div>

                <div class="form-group">
                    <label>Email:</label>
                    <input class="form-control " type="email" name="email" value="<%=u.getEmail()%>"/>
                </div>

                <div class="form-group">
                    <label>Phone:</label>
                    <input class="form-control " type="text" name="phone" value="<%=u.getPhone()%>"/>
                </div>

                <div class="form-group">
                    <label>Profile Description:</label>
                    <textarea class="form-control" rows="3" name="profDesc"><%=u.getProfileDescription()%></textarea>

                </div>

                <div class="form-group">
                    <label>Address:</label>
                    <input class="form-control " type="text" name="address" value="<%=u.getAddress()%>"/>
                </div>

                <div class="form-group">
                    <label>Birthdate:</label>
                    <input class="form-control " type="date" name="birthdate" value="<%=u.getBirthDate()%>"/>
                </div>

                <div class="form-group">
                    <label>Birthplace:</label>

                    <select name="birthplace" class="form-control">
                        <option selected><%=u.getBirthPlace().toString()%>
                        </option>
                        <%

                            CountryDaoInter countryDao = new CountryDaoImpl();
                            List<Country> allCountry = countryDao.getAllCountry();

                            for (Country c : allCountry) {

                        %>
                        <option><%=c%>
                        </option>
                        <%}%>
                    </select>
                </div>

                <div class="form-group">
                    <label>Nationality:</label>

                    <select name="nationality" class="form-control">

                        <option selected><%=u.getNationality().toString()%>
                        </option>
                        <%
                            List<Country> allNationality = countryDao.getAllNationality();
                            for (Country n : allNationality) {
                        %>
                        <option><%=n%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <input class="btn btn-primary" type="submit" name="update" value="Update"/>

            </form>
        </div>
    </div>
</div>

</body>
</html>

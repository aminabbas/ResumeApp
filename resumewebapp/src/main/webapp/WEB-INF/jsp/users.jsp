<%--
    Document   : user
    Created on : 22-Mar-2022, 16:26:44
    Author     : Zeynəb
--%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="f" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://kit.fontawesome.com/f2e0738da1.js" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="../assets/css/users_page.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <script type="text/javascript" src="../assets/js/users.js"></script>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
            integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <title>JSP Page</title>
</head>
<body>

<div class="container mycontainer">
    <div class="row">
        <div class="col-4">
            <f:form action="users" method="GET" modelAttribute="user">
                <div class="form-group">
                    <label>name:</label>
                    <f:input  class="form-control " placeholder="Enter  name" path="name" />
                </div>

                <div class="control_panel_group">
                    <label>surname:</label>
                    <f:input class="form-control " placeholder="Enter  surname"  path="surname" />
                </div>
                <f:button visible="true" class="btn btn-primary" type="submit"  value="Search"
                       id="btnsearch"/>

            </f:form>
        </div>

        <div>
            <table class="table">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Nationality</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
               <c:forEach items="${allUser}" var="u">

                <tr>
                    <td>${u.name}</td>
                    <td>${u.surname}</td>
                    <td>${u.nationality.name}</td>
                    <td style="width: 5px">


                        <button type="submit" value="delete" class="btn btn-danger" data-toggle="modal"
                                data-target="#exampleModal" onclick="setIdForDelete(${u.id})">
                            <i class="fa-solid fa-circle-minus"></i>
                        </button>

                    </td>
                    <td style="width: 5px">
                        <form action="userDetails" method="GET">
                            <input type="hidden" name="id" value="${u.id}"/>
                            <button type="submit" value="update" class="btn btn-secondary">
                                <i class="fa-solid fa-pen"></i>
                            </button>
                        </form>
                    </td>
                </tr>
                </tbody>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<!-- Button trigger modal -->


<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Delete</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Are You Sure?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                <form action="userDetails" method="POST">
                    <input type="hidden" name="id" value="" id="idForDelete"/>
                    <input type="hidden" name="action" value="delete">
                    <input type="submit" class="btn btn-danger" value="Delete">
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>

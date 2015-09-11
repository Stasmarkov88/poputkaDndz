<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

  <c:set var="js" value="/assets/js/"/>
  <script src="${js}validate.js"></script>
  <script src="${js}showModals.js"></script>
  <link rel="stylesheet" href="/assets/css/custom.css">
  <link rel="stylesheet" href="/assets/css/adminStyle.css">
<title>Admin Room</title>
<div class="col-lg-12">
  <div class="bs-component">
    <h1 align="center">ADMIN'S ROOM</h1>

    <div class="row space">
      <a class="col-lg-2"/>
      <a class="btn btn-primary  col-lg-3 col-xs-12" href="/admin/usersList">Manage users</a>
      <a class="col-lg-2"/>
      <a class="btn btn-primary  col-lg-3 col-xs-12" href="/admin/pointsList">Manage points</a>
    </div>

    <div class="row">
      <div class="col-lg-12">
        <div class="bs-component">
          <table class="table table-striped table-hover">
            <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Gender</th>
              <th>Login</th>
              <th>Car</th>
              <th>Phone</th>
              <th colspan="3">Email</th>
            </tr>
            </thead>
            <c:forEach var="user" items="${requestScope.usersList}">
              <tbody>
              <tr class="info">
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.gender}</td>
                <td>${user.login}</td>
                <td>${user.car}</td>
                <td>${user.phoneNumber}</td>
                <td>${user.email}</td>
                <td>
                  <form:form class="form-inline" id="deleteLink" action="/admin/userlist/delete"
                             method="get"
                             commandName="deleteUser">
                    <button type="submit" class="btn btn-primary pull-right">Delete User</button>
                    <input type="hidden" name="id" value="${user.id}">
                  </form:form>
                </td>
              </tr>
              </tbody>
            </c:forEach>
          </table>
        </div>
      </div>
    </div>
  </div>
</div>


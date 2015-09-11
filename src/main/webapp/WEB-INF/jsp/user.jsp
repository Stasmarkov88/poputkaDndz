<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>

<title>Мой профиль</title>
<link rel="stylesheet" href="/assets/css/style.css">

<h1>Мой профиль</h1>
<div class="bs-docs-section">
<div class="row">
  <div class="col-lg-12">
    <div class="well bs-component">
      <div class="row">
        <div class="col-lg-3"></div>
        <div class="col-lg-6">
          <table class="table">
            <tbody>
            <tr class="whiteBackground">
              <td>Логин:</td>
              <td>${user.login}</td>
            </tr>
            <tr class="whiteBackground">
              <td>Имя:</td>
              <td>${user.name}</td>
            </tr>
            <tr class="whiteBackground">
              <td>Пол:</td>
              <td>
                <c:choose>
                  <c:when test="${user.gender=='MALE'}">
                    Мужчина
                  </c:when>
                  <c:otherwise>
                    Женщина
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
            <tr class="whiteBackground">
              <td>E-mail:</td>
              <td>${user.email}</td>
            </tr>
            <tr class="whiteBackground">
              <td>Телефон:</td>
              <td>${user.phoneNumber}</td>
            </tr>
            <tr class="whiteBackground">
              <td>Машина:</td>
              <td>
                <c:choose>
                  <c:when test="${empty user.car}">
                    нет
                  </c:when>
                  <c:otherwise>
                    ${user.car}
                  </c:otherwise>
                </c:choose>
              </td>
            </tr>
            </tbody>
          </table>
          <a href="/user/editProfile" class="btn btn-info pull-right">Редактировать профиль</a>
        </div>
        <div class="col-lg-3"></div>
        <%--<div class="col-lg-6">--%>
        <%--<div class="list-group">--%>
        <%--<p class="list-group-item list-group-item-info">--%>
        <%--Меню--%>
        <%--</p>--%>
        <%--<a href="/user/showTrips" class="list-group-item">Мои поездки</a>--%>
        <%--<a href="/user/findTrip" class="list-group-item">Найти поездку</a>--%>
        <%--<c:choose>--%>
        <%--<c:when test="${empty user.car}">--%>
        <%--</c:when>--%>
        <%--<c:otherwise>--%>
        <%--<a href="/user/createTrip" class="list-group-item">Создать поездку</a>--%>
        <%--</c:otherwise>--%>
        <%--</c:choose>--%>
        <%--<a href="/user/editProfile" class="list-group-item">Редактировать профиль</a>--%>
        <%--</div>--%>
        <%--</div>--%>
      </div>
    </div>
  </div>
</div>
</div>
</br>
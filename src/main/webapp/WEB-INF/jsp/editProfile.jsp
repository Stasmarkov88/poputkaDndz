<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page session="true" %>

<c:set var="js" value="/assets/js/"/>
<c:set var="css" value="/assets/css/"/>
<script src="${js}validate.js"></script>
<script src="${js}showModals.js"></script>
<script src="${js}validatePassword.js"></script>
<link rel="stylesheet" href="${css}animate.css">
<link rel="stylesheet" href="${css}style.css">
<title>Редактировать профиль</title>

<h1>Редактировать профиль</h1>
<div class="bs-docs-section">
<div class="well bs-component">
  <div class="row">
    <div class="col-lg-7">
      <sf:form class="form-horizontal" method="post" modelAttribute="user" role="form" id="form">
        <fieldset>
          <sf:input path="password" type="hidden"/>
          <sf:input path="login" type="hidden"/>
          <div class="form-group">
            <label class="control-label col-sm-2" for="fullname">Имя:</label>

            <div class="col-sm-10">
              <sf:input path="name" id="fullname" class="form-control" placeholder="Введите имя"/>
              <span id="nameError" class="center-block hidden text-danger">минимум 3 символа</span>
            </div>
          </div>
          <div class="form-group" id="blockemail">
            <label class="control-label col-sm-2" for="email">E-mail:</label>

            <div class="col-sm-10">
              <sf:input path="email" id="email" class="form-control"
                        placeholder="Введите e-mail"/>
              <span id="emailError" class="center-block hidden text-danger"> пример: rob@gmail.com </span>
            </div>
          </div>
          <div class="form-group" id="blockphone">
            <label class="control-label col-sm-2" for="phone">Телефон:</label>

            <div class="col-sm-10">
              <sf:input path="phoneNumber" id="phone" class="form-control" placeholder="Введите телефон"/>
              <span id="phoneError" class="center-block hidden text-danger"> пример: 8 050 333 22 11</span>
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-2" for="sex">Пол:</label>

            <div class="col-sm-10" id="sex">
              <sf:radiobutton path="gender" value="FEMALE"/>Женщина
              <sf:radiobutton path="gender" value="MALE"/>Мужчина
            </div>
          </div>
          <div class="form-group">
            <label class="control-label col-sm-2" for="car">Машина:</label>

            <div class="col-sm-10">
              <sf:input path="car" id="car" class="form-control"
                        placeholder="Введите описание машины"/>
            </div>
          </div>
        </fieldset>
        <div class="form-group">
          <div class="col-lg-10 col-lg-offset-2">
            <button type="submit" id="sendChanges" class="btn btn-info pull-right">Сохранить</button>
          </div>
        </div>
      </sf:form>
    </div>
    <div class="col-lg-5">
      <div class="list-group">
        <p class="list-group-item list-group-item-info">
          Меню
        </p>
        <a href="#" id="changePassword" class="list-group-item">Сменить пароль</a>
        <a href="#" id="deleteProfile" class="list-group-item">Удалить профиль</a>
      </div>
      <div id="deleteContent" class="panel panel-info displayNone">
        <div class="panel-heading">
          <p class="panel-title">Вы уверены?</p>
        </div>
        <div class="panel-body">
          <a class="btn btn-sm btn-danger" href="/user/editProfile/deleteProfile">Удалить</a>
          <a class="btn btn-sm btn-info" href="#" id="continue">Остаться</a>
        </div>
      </div>
      <div id="changeContent" class="panel panel-info displayNone">
        <div class="panel-heading">
          <p class="panel-title">Смена пароля</p>
        </div>
        <div class="panel-body">
          <sf:form id="passForm" role="form" method="post" modelAttribute="pass"
                   action="/user/editProfile/changePassword">
            <fieldset>
              <div class="form-group form-group-sm" id="currentPasswordBlock">
                <label for="currentPassword">
                  <small>Текущий пароль:</small>
                </label>
                <sf:input path="currentPassword" id="currentPassword" type="password" class="form-control input-sm"
                          placeholder="Введите текущий пароль"/>
                <span id="currentPassError" class="center-block hidden text-danger"> пароль слишком короткий, минимум 3 символа </span>
              </div>
              <div class="form-group form-group-sm" id="newPasswordBlock">
                <label for="newPassword">
                  <small>Новый пароль:</small>
                </label>
                <sf:input path="newPassword" id="newPassword" type="password" class="form-control input-sm"
                          placeholder="Введите новый пароль"/>
                <span id="passwordError" class="center-block hidden text-danger"> пароль слишком короткий, минимум 3 символа </span>
              </div>
              <div class="form-group form-group-sm" id="repeatPasswordBlock">
                <label for="repeatPassword">
                  <small>Подтверждение:</small>
                </label>
                <input type="password" class="form-control input-sm" name="compas" id="repeatPassword"
                       placeholder="Подтвердите новый пароль">
                <span id="comError" class="center-block hidden text-danger"> пароль не совпадает </span>
              </div>
              <div class="form-group form-group-sm pull-right">
                <button type="reset" class="btn btn-sm btn-warning" id="cancel">Отмена</button>
                <button type="submit" class="btn btn-sm btn-info" id="sendPass">Сохранить</button>
              </div>
            </fieldset>
          </sf:form>
        </div>
      </div>
      <c:set var="classSuccess" value="displayNone"></c:set>
      <c:set var="classFail" value="displayNone"></c:set>
      <c:choose>
        <c:when test="${mes=='profit'}">
          <c:set var="classSuccess" value=""></c:set>
        </c:when>
        <c:when test="${mes=='fail'}">
          <c:set var="classFail" value=""></c:set>
        </c:when>
      </c:choose>
      </br>
      <div id="successMessage" class="${classSuccess} alert alert-success" role="alert">
        <p>Password successfully changed!</p>
      </div>
      <div id="errorMessage" class="${classFail} alert alert-danger" role="alert">
        <p>You entered wrong current password! Please try again.</p>
      </div>
    </div>
  </div>
</div>
</div>
</br>




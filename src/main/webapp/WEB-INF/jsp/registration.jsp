<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
  $(document).on("click", ".alert", function(e) {
    bootbox.alert("Hello world!", function() {
      console.log("Alert Callback");
    });
  });
</script>

<title>Регистрация</title>

<div class="bs-docs-section">
  <div class="row">
    <div class="col-lg-12">
      <div class="page-header">
        <h1 id="forms">Регистрация</h1>
      </div>
    </div>
  </div>

  <div class="row">
    <div class="col-lg-12">
      <div class="well bs-component">
        <h6>${requestScope.message}</h6>
        <form:form class="form-horizontal" id="form" method="post" modelAttribute="user">
          <!---->
          <fieldset>
            <legend>Персональная информация</legend>
            <div class="form-group" id="login">
              <label for="login" class="col-lg-2 control-label">Логин:</label>

              <div class="col-lg-10">
                <form:input type="text" path="login" class="form-control" id="enterlogin"
                            placeholder="Введите логин"/>
                <span id="loginError" class="center-block hidden text-danger">минимум 3 символа</span>
                <span id="loginError2" class="center-block hidden text-danger">такой логин уже зарегистрирован</span>
              </div>
            </div>

            <div class="form-group" id="name">
              <label for="fullname" class="col-lg-2 control-label">Имя:</label>

              <div class="col-lg-10">
                <form:input type="text" path="name" class="form-control" id="fullname"
                            placeholder="Введите имя"/>
                <span id="nameError" class="center-block hidden text-danger">минимум 3 символа</span>
              </div>
            </div>

            <div class="form-group" id="gender">
              <label class="col-lg-2 control-label">Пол:</label>

              <div class="col-lg-10">
                <div class="radio">
                  <label>
                    <form:radiobutton path="gender" id="female" name="gender" value="FEMALE"/>
                    Женщина
                  </label>
                  <label>
                    <form:radiobutton path="gender" id="male" name="gender" value="MALE"/>
                    Мужчина
                  </label>
                </div>

                <span id="genderError" class="center-block hidden text-danger"> сделайте выбор </span>
              </div>
            </div>

            <div class="form-group" id="car">
              <label class="col-lg-2 control-label">Машина:</label>

              <div class="col-lg-10">
                <div class="radio">

                  <label>
                    <input type="radio" name="car" id="idonthavcar">
                    Нет машины
                  </label>
                </div>
                <div class="radio">
                  <label>
                    <input type="radio" name="car" id="ihavecar" value="car">
                    Есть машина
                  </label>
                </div>
                <span id="carError" class="center-block hidden text-danger">Есть ли у вас машина?</span>

                <div class="col-lg-5" id="info">
                  <form:input path="car" type="text" class="form-control hidden" id="aboutcar"
                              placeholder="Введите марку и цвет машины"/>
                  <span id="infocar" class="center-block hidden text-danger">минимум 3 символа</span>
                </div>

              </div>
            </div>
          </fieldset>

          <hr/>

          <fieldset>
            <legend>Контакты</legend>
            <div class="form-group" id="blockemail">
              <label for="email" class="col-lg-2 control-label">Email:</label>

              <div class="col-lg-10">
                <form:input path="email" type="text" class="form-control" name="email" id="email"
                            placeholder="Email"/>
                <span id="emailError" class="center-block hidden text-danger"> пример: rob@gmail.com </span>
              </div>
            </div>


            <div class="form-group" id="blockphone">
              <label for="phone" class="col-lg-2 control-label">Телефон:</label>

              <div class="col-lg-10">
                <form:input path="phoneNumber" type="text" class="form-control" name="phone" id="phone"
                            placeholder="Пример: 050 333 22 11"/>
                <span id="phoneError" class="center-block hidden text-danger"> пример: 050 333 22 11</span>
              </div>
            </div>
          </fieldset>

          <hr/>

          <fieldset>
            <legend>Пароль</legend>
            <div class="form-group" id="blockpassword">
              <label for="password" class="col-lg-2 control-label">Пароль:</label>

              <div class="col-lg-10">
                <form:input path="password" type="password" class="form-control" name="password" id="password"
                            placeholder="Password"/>
                <span id="passwordError" class="center-block hidden text-danger"> пароль слишком короткий, минимум 3 символа </span>
              </div>
            </div>

            <div class="form-group" id="blockcompare">
              <label for="compas" class="col-lg-2 control-label">Подтверждение:</label>

              <div class="col-lg-10">
                <input type="password" class="form-control" name="compas" id="compas" placeholder="Password">
                <span id="comError" class="center-block hidden text-danger"> пароль не совпадает </span>
              </div>
            </div>
          </fieldset>

          <div class="form-group">
            <div class="col-lg-10 col-lg-offset-2">
              <button type="submit" id="send" class="btn btn-primary pull-right">Зарегистрироваться</button>
            </div>
          </div>
        </form:form>
      </div>
    </div>

  </div>
</div>
</br>
<script src="/assets/js/validate.js"></script>
<script src="/assets/js/jquery.maskedinput.js"></script>
<script>
    jQuery(function($){
        $("#phone").mask("(999) 999-9999");
    });
</script>
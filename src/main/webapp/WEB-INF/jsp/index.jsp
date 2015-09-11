<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>

<c:url value="/assets/img/" var="images"/>
<c:url value="/assets/js/" var="js"/>
<c:url value="/assets/js/" var="css"/>
<title>EasyGo</title>
<link rel="stylesheet" href="${css}bootstrap.min.css">
<!-- Slider -->

<div class="row">
  <div class="col-lg-8 col-xs-12">
    <div class="bs-component">
      <div class="panel panel-default">
        <div class="panel-body">
          <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">

            <!-- Indicators -->
            <ol class="carousel-indicators">
              <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
              <li data-target="#carousel-example-generic" data-slide-to="1"></li>
              <li data-target="#carousel-example-generic" data-slide-to="2"></li>
              <li data-target="#carousel-example-generic" data-slide-to="3"></li>
            </ol>

            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
              <div class="item active">
                <img class="img-responsive" src="${images}slider-img-1-1300.jpg" alt="slider1">

              </div>
              <div class="item">
                <img class="img-responsive" src="${images}slider-img-2-1300.jpg" alt="slider2">

              </div>
              <div class="item">
                <img class="img-responsive" src="${images}slider-img-3-1300.jpg" alt="slider3">

              </div>
              <div class="item">
                <img class="img-responsive" src="${images}slider-img-4-1300.jpg" alt="slider4">

                <div class="carousel-caption">
                </div>
              </div>
            </div>
            <!-- Controls -->
            <a class="left carousel-control" href="#carousel-example-generic" role="button"
               data-slide="prev">
              <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
              <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#carousel-example-generic" role="button"
               data-slide="next">
              <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
              <span class="sr-only">Next</span>
            </a>
          </div>
        </div>
      </div>
    </div>
  </div>


  <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
    <div class="col-lg-4 col-xs-12">
      <div class="bs-component">

          <%--<div class="well bs-component">--%>

        <c:url value="/j_spring_security_check" var="loginUrl"/>


        <form class="form-horizontal" action="${loginUrl}" method="post">
          <fieldset>
            <div class="form-group">
              <div class="col-xs-12">
                <input type="text" class="form-control" id="inputEmail1"
                       name="j_username" placeholder="Логин">
              </div>
            </div>
            <div class="form-group">
              <div class="col-xs-12">
                <input type="password" class="form-control" id="inputEmail"
                       name="j_password"
                       placeholder="Пароль">
              </div>
            </div>
            <div class="form-group">
              <div class="col-xs-12">
                <button type="submit" class="btn btn-info btn-block">Вход</button>
                <a class="btn btn-info btn-block" href="/registration">Регистрация</a>
              </div>
            </div>
          </fieldset>
        </form>

      </div>
    </div>
  </sec:authorize>
  <sec:authorize access="hasAnyRole('ROLE_USER')">
    <div class="col-lg-4 col-xs-12">
      <div class="bs-component">
        <div class="list-group">
          <p class="list-group-item list-group-item-info">
            Вы вошли как <sec:authentication property="principal.username"/>
          </p>
          <a href="/user" class="list-group-item">Перейти в кабинет</a>
        </div>
      </div>
    </div>
  </sec:authorize>
  <sec:authorize access="hasAnyRole('ROLE_ADMIN')">
    <div class="col-lg-4 col-xs-12">
      <div class="bs-component">
        <div class="list-group">
          <p class="list-group-item list-group-item-info">
            Вы вошли как <sec:authentication property="principal.username"/>
          </p>
          <a href="/admin" class="list-group-item">Вернуться в Admin area</a>
        </div>
      </div>
    </div>
  </sec:authorize>
</div>





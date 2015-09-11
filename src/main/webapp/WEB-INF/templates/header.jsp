<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="sec"
          uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="t" %>
<link rel="stylesheet" href="/assets/css/custom.css">
<!-- Navbar -->
<div class="row">
  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
    <div class="bs-component">
      <div class="raw">
        <nav class="navbar navbar-default">
          <div class="container-fluid">
            <div class="navbar-left col-lg-10 col-md-9 col-sm-12">
              <div id="banner">
                <a href="/">
                  <img class="img-responsive" src="/assets/img/banner_header_full.svg">
                </a>
              </div>
            </div>
            <div class="col-lg-2 col-md-3 col-sm-12 pull-right buttonLinks">
              <sec:authorize ifAnyGranted="ROLE_ANONYMOUS">
                <p></p>
                <a href="/registration" style="text-decoration: none">
                  <button type="button" class="btn btn-info btn-block"
                          aria-label="Right Align">
                    <span class="glyphicon glyphicon-road" aria-hidden="true"></span>
                    Регистрация
                  </button>
                </a>
                <p></p>
                <a href="/about" style="text-decoration: none">
                  <button type="button" class="btn btn-info btn-block" aria-label="Right Align">
                    <span class="glyphicon glyphicon-search" aria-hidden="true"></span>
                    О проекте
                  </button>
                </a>
                <p></p>
              </sec:authorize>
            </div>
          </div>
          <sec:authorize access="isAuthenticated()">
            <c:set var="activeClass" value=""></c:set>
            <nav class="navbar default-navbar">
              <div class="container-fluid">
                <div class="collapse navbar-collapse" id="myNavbar">
                  <ul class="nav navbar-nav">
                    <c:set var="activeUser" value=""/>
                    <c:if test="${adress=='user'}">
                      <c:set var="activeUser" value="active"/>
                    </c:if>
                    <li class="${activeUser}"><a href="/user">Мой профиль</a></li>
                    <c:set var="activeTrips" value=""/>
                    <c:if test="${adress=='showTrips'}">
                      <c:set var="activeTrips" value="active"/>
                    </c:if>
                    <li class="${activeTrips}"><a href="/user/showTrips">Мои поездки</a></li>
                    <c:set var="activeFind" value=""/>
                    <c:if test="${adress=='findTrip'}">
                      <c:set var="activeFind" value="active"/>
                    </c:if>
                    <li class="${activeFind}"><a href="/user/findTrip">Поиск поездок</a></li>
                    <c:if test="${not empty user.car}">
                      <c:if test="${adress=='createTrip'}">
                        <c:set var="activeClass" value="active"/>
                      </c:if>
                      <li class="${activeClass}"><a href="/user/createTrip">Создать поездку</a></li>
                    </c:if>
                    <c:set var="activeAbout" value=""/>
                    <c:if test="${adress=='about'}">
                      <c:set var="activeAbout" value="active"/>
                    </c:if>
                    <li class="${activeAbout}"><a href="/about">О проекте</a></li>
                    <li class="pull-right"><a href="/j_spring_security_logout"><span class="glyphicon glyphicon glyphicon-log-out" aria-hidden="true"></span>&nbspВыйти</a></li>
                  </ul>
                </div>
              </div>
            </nav>
          </sec:authorize>
        </nav>
      </div>
      <div class="row">
        <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">

        </div>
      </div>
    </div>
  </div>
</div>
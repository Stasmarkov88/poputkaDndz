<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<div class="jumbotron col-lg-12 col-xs-12" align="center">
  <img style="height: 300px" src="/assets/img/dialog_error.png" class="img-responsive">

  <p>${requestScope.message}</p>

  <div class="row">
    <p class="collapse" id="viewdetails">${requestScope.exception}</p>
    <a class="btn btn-primary" data-toggle="collapse" data-target="#viewdetails">Сведения об ошибке &raquo;</a>
  </div>
  <div class="row"></div>
  <div class="row">
    <a class="btn btn-primary" style="margin-top: 2%" href="/">Вернуться на главную</a>
  </div>
</div>
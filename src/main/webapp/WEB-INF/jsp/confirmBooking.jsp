<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page session="true" %>
<c:set var="js" value="/assets/js/"/>
<c:set var="css" value="/assets/css/"/>
<script src="${js}validate.js"></script>
<script src="${js}booking.js"></script>
<script src="${js}dropdown-enhancement.js"></script>
<link rel="stylesheet" href="${css}animate.css">
<link rel="stylesheet" href="${css}style.css">
<link rel="stylesheet" href="${css}dropdown-enhancement.css">

<h1>Подтверждение брони</h1>

<div class="well bs-component">
  <div class="row">
    <div class="col-lg-12">
      <div class="text-center">
        <p><strong>Время старта поездки:&nbsp<fmt:formatDate value="${trip.startTime}" pattern="dd MMMM HH:mm"/></strong></p>

        <p><strong>Стоимость проезда составляет:&nbsp${trip.price}&nbspгрн.</strong></p>

        <p><strong>Для подтверждения брони укажите удобное для Вас место посадки:</strong></p>
      </div>
    </div>
  </div>
  <div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">
      <sf:form id="pointForm" method="post" modelAttribute="landing" action="/user/findTrip/confirmBooking">
        <fieldset>
          <div class="form-group">
            <sf:input path="description" id="description" type="hidden"></sf:input>
          </div>
          <div class="form-group">
            <label for="dropDownPoints">Выберите из списка:</label>
            <div class="btn-group align" id="dropDownPoints">
              <button data-toggle="dropdown" id="dropDowmPoint" class="btn btn-default dropdown-toggle">--ОСТАНОВКА--
                <span class="caret"></span></button>
              <ul class="dropdown-menu bullet">
                <c:forEach var="tripPoint" items="${trip.passengerNodePoints}">
                  <c:if test="${tripPoint.left == true}">
                    <li><input type="radio" id="ID${tripPoint.id}" name="points" value="${tripPoint.description}"><label
                        for="ID${tripPoint.id}">${tripPoint.description}</label></li>
                  </c:if>
                </c:forEach>
              </ul>
            </div>
          </div>
          <div class="form-group">
            <label for="comment">Оставьте комментарий для ориентиров:</label>
            <textarea class="form-control" rows="3" id="comment"></textarea>
          </div>
          <div class="alert alert-danger displayNone text-center" id="confirmError" role="alert">
            Выберите удобное для вас место посадки!
          </div>
          <div class="form-group pull-right">
            <a class="btn btn-sm btn-warning" href="/user/findTrip">Отмена</a>
            <button type="submit" class="btn btn-sm btn-info" id="confirm">Подтвердить</button>
          </div>
        </fieldset>
      </sf:form>
    </div>
    <div class="col-lg-3"></div>
  </div>
  <div class="row">
    <div class="col-lg-3"></div>
    <div class="col-lg-6">

    </div>
    <div class="col-lg-3"></div>
  </div>
</div>
</br>




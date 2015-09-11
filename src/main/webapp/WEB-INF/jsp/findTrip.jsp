<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<title>Поиск поездки</title>

<c:set var="js" value="/assets/js/"/>
<c:set var="css" value="/assets/css/"/>
<script type="text/javascript" src="${js}moment-with-locales.min.js"></script>
<script type="text/javascript" src="${js}bootstrap-datetimepicker.js"></script>
<link rel="stylesheet" href="${css}bootstrap-datetimepicker.min.css"/>
<link rel="stylesheet" href="${css}custom.css"/>
<link rel="stylesheet" href="${css}createTrip.css">
<link rel="stylesheet" type="text/css" href="${css}bootstrap-tour.min.css"/>
<link rel="stylesheet" href="${css}createTrip.css">


<%----%>
<script src="https://maps.googleapis.com/maps/api/js?v=AIzaSyAlMDftXoxe0Ig9Dpip_Y0TCuLRWA_TVqg&sensor=false"></script>
<script>
  $(document).ready(function () {
    var map;
    var markers = [];
    var x = [];
    function initialize() {
      var myLatlng = new google.maps.LatLng(48.536764, 34.571143);
      var mapOptions = {
        zoom: 11,
        center: myLatlng,
        scrollwheel: false,
        mapTypeId: google.maps.MapTypeId.ROADMAP
      };
      map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    }
    google.maps.event.addDomListener(window, 'load', initialize);
    //    для календаря
    $(function () {
      $('#datetimepicker1').datetimepicker(
          {
            locale: 'ru',
            defaultDate: new Date(),
            minDate: (new Date(new Date().getTime() - 600000))
          }
      );
    });
    $(function () {
      $('#datetimepicker2').datetimepicker(
          {
            locale: 'ru',
            defaultDate: new Date(),
            minDate: (new Date(new Date().getTime() - 600000))
          }
      );
    });
    //    получение и отображение все точек из объекта
    $(function () {
      x = [
        <c:forEach var="point" items="${points}" varStatus="status">
        {lat: "${point.latitude}", lng: "${point.longitude}", des: "${point.description}"},
        </c:forEach>
      ];
      function setMarkers(map, locations) {
        for (var i = 0; i < locations.length; i++) {
          var pleace = locations[i];
          var latt = pleace.lat;
          var long = pleace.lng;
          var dess = pleace.des;
          var myLatLng = new google.maps.LatLng(latt, long);
          var marker = new google.maps.Marker({
            position: myLatLng,
            map: map,
            title: dess
          });
          markers.push(marker);
        }
      }
      function setAllMap(map) {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(map);
        }
      }
      google.maps.event.addDomListener(window, 'load', function () {
        setMarkers(map, x);
        setAllMap(null);
      });
    });
    //    Прячет все маркеры на карте
    $(function () {
      // Sets the map on all markers in the array.
      function setAllMap(map) {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(map);
        }
      }
      $('#hidemarkers').on('click', function () {
//                    alert("Вы нажали на КНОПКУ");
            // Removes the markers from the map, but keeps them in the array.
            setAllMap(null);
            $("input:checkbox:checked").removeAttr('checked');
          }
      )
    });
    //    Добавление маркера по нажатию на чекбокс
    $(document).ready(function () {
      $("input[type='checkbox']").change(function () {
        var $this = $(this);
        var element = $this.val() - 1;
        var marker;
        if ($this.is(':checked')) {
          for (var i = 0; i <= markers.length; i++) {
            if (element == i) {
              markers[i].setIcon('https://www.google.com/mapfiles/marker_green.png');
              markers[i].setMap(map);
            }
          }
        } else {
          for (var i = 0; i <= markers.length; i++) {
            if (element == i) {
              markers[i].setMap(null);
            }
          }
        }
      });
    });
  });
</script>

<%----%>

<div class="bs-docs-section">

  <div class="row">
    <div class="col-lg-12">
      <h3 id="type">Поиск поездки</h3>
      <br>
    </div>
  </div>

  <div class="row">
    <%--Google Map--%>
    <div class="col-lg-12">
      <div class="bs-component">
        <div class="panel panel-default">
          <div class="panel-body">
            <%--<h2>Map</h2>--%>
            <div id="map-canvas" style="height:400px; width:100%"></div>
            <div id="map">
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>


  <div class="row">
    <div class="col-lg-4">
      <%--Forms--%>
      <form:form id="formfind" method="post" modelAttribute="dto" action="/user/findTrip#tables">
      <div class="bs-component">
        <div class="panel panel-default">
          <div class="panel-body">
            Укажите интервал времени, когда Вы хотите найти поездку
            <hr/>
            <div class="form-group">
              <div class="h4">От:</div>
              <div class="input-group date" id="datetimepicker1">
                <form:input path="startTime" type="text" class="form-control"/>
                                        <span class="input-group-addon">
                                            <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
              </div>
            </div>

            <div class="form-group">
              <h4>До:</h4>

              <div class="input-group date" id="datetimepicker2">
                <form:input path="endTime" type="text"  class="form-control"/>
                                    <span class="input-group-addon">
                                      <span class="glyphicon glyphicon-calendar"></span>
                                    </span>
              </div>
            </div>

            <input id="hidemarkers" class="btn btn-primary btn-sm" type=button value="Очистить">
            <button type="submit" class="btn btn-primary btn-sm pull-right">Поиск</button>

          </div>
        </div>
      </div>
    </div>
    <div class="col-lg-4">
      <div class="bs-component">
        Выберите подходящий Вам маршрут!
        Левый берег:
        <hr/>
        <div class="h5" id="start">
          <c:forEach var="point" items="${points}" varStatus="status">
            <c:if test="${point.isLeft()}">

              <div class="checkbox">
                <label>
                  <input type="checkbox" name="passengerNodePoints[${status.index}].id"
                         value="${point.id}"/>
                  <c:out value="${point.description}"/><br>
                </label>
              </div>

            </c:if>
          </c:forEach>
        </div>
      </div>
    </div>
    <div class="col-lg-4">
      <div class="bs-component">
        Выберите подходящий Вам маршрут!
        Правый берег:
        <hr/>
        <div class="h5" id="end">
          <c:forEach var="point" items="${points}" varStatus="status">
            <c:if test="${!point.isLeft()}">

              <div class="checkbox">
                <label>
                  <input type="checkbox" name="passengerNodePoints[${status.index}].id"
                         value="${point.id}"/>
                  <c:out value="${point.description}"/><br>
                </label>
              </div>

            </c:if>
          </c:forEach>
        </div>
      </div>
    </div>
  </div>
  </form:form>
</div>

<%--Таблица для вывода--%>
<div class="bs-docs-section">
  <div class="row">
    <div class="col-lg-12">
      <h1 id="tables">Расписание поездок</h1>
      <br>


      <div class="bs-component">
        <table class="table table-striped table-hover ">
          <thead class="customTableHead">
          <tr>
            <th class="customTableHead">№</th>
            <th class="customTableHead">Время старта поездки</th>
            <th class="customTableHead">Количество свободных мест</th>
            <th class="customTableHead">Стоимость проезда</th>
            <th class="customTableHead">Маршрут</th>
            <th class="customTableHead">Совпадения по маршруту</th>
            <th class="customTableHead">Занять место</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="trip" varStatus="count" items="${dto.trips}">
            <tr>
              <td><c:out value="${count.index+1}"/></td>
              <td><fmt:formatDate value="${trip.startTime}" pattern="dd MMMM HH:mm"/></td>
              <td><c:out value="${trip.carCapacity}"/></td>
              <td><c:out value="${trip.price}"/></td>
              <td><c:forEach var="tripPoint" items="${trip.passengerNodePoints}">
                <div><c:out value="${tripPoint.description}"/></div>
              </c:forEach>
              </td>
              <td>
                <c:forEach var="tripPoint" items="${trip.passengerNodePoints}">
                  <c:forEach var="userPoint" items="${dto.passengerNodePoints}">
                    <c:if test="${tripPoint.id == userPoint.id}">
                      <div><c:out value="${tripPoint.description}"/></div>
                    </c:if>
                  </c:forEach>
                </c:forEach>
              </td>
              <td>
                <a href="/user/findTrip/saveTrip?id=${trip.id}">
                  <button type="button" class="btn btn-info btn-sm">Занять место</button>
                </a>
              </td>
            </tr>
          </c:forEach>
          </tbody>
        </table>
        <c:if test="${not empty fail}">
          <div class="alert alert-info text-center" role="alert">
            <p><strong>К сожалению, по Вашему запросу не найдено ни одной поездки со свободными местами.</strong></p>
          </div>
        </c:if>
      </div>
    </div>
  </div>

</div>


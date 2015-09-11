<%@ page language="java" pageEncoding="utf-8" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<title>Создать поездку</title>

<c:set var="js" value="/assets/js/"/>
<c:set var="css" value="/assets/css/"/>
<script type="text/javascript" src="${js}moment-with-locales.min.js"></script>
<script type="text/javascript" src="${js}bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="${js}validateTrip.js"></script>
<script type="text/javascript" src="${js}bootstrap-tour.min.js"></script>
<link rel="stylesheet" type="text/css" href="${css}bootstrap-datetimepicker.min.css"/>
<link rel="stylesheet" type="text/css" href="${css}custom.css"/>

<%----%>
<script src="https://maps.googleapis.com/maps/api/js?v=AIzaSyAlMDftXoxe0Ig9Dpip_Y0TCuLRWA_TVqg&sensor=false"></script>

<script>

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
          minDate: new Date()
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
          // Removes the markers from the map, but keeps them in the array.
          setAllMap(null);
          $("input:checkbox:checked").removeAttr('checked');
          $('#capacity').val(1);
          $('#cena').val(1);
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


</script>

<%----%>

<div class="bs-docs-section">
  <div class="row">
    <div class="col-lg-12">
        <h3 id="type">Создание поездки</h3>
      <br>
    </div>
  </div>

  <div class="row">
    <%--Google Map--%>
    <div class="col-lg-12">
      <div class="bs-component">
        <div class="panel panel-default">
          <div class="panel-body">
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
      <%--form--%>
      <form:form id="form" method="post" modelAttribute="trip">
      <div class="bs-component">
        <div class="panel panel-default">
          <div class="panel-body">
            <div class="form-group" id="carCapacity">
              <label class="col-lg-10 control-label  ">Выберите количество мест:</label>
              <form:select path="carCapacity" items="${list}" id="capacity"/>
            </div>
            <div class="form-group" id="cost">
              <label class="col-lg-10 control-label ">Укажите стоимость проезда, грн.:</label>
              <form:select path="price" items="${list}" id="cena"/>
            </div>
            <hr/>

            <p>Установите дату и время старта поездки:</p>

             <div class="form-group" id="timePicker">
              <!-- Элемент HTML с id равным datetimepicker1 -->
               <div class="input-group date" id="datetimepicker1">
                <input type="text" class="form-control" name="startDate"/>
                                        <span class="input-group-addon">
                                          <span class="glyphicon glyphicon-calendar"></span>
                                        </span>
              </div>
            </div>
              <input id="hidemarkers" class="btn btn-primary btn-sm" type=button value="Очистить">
              <button type="submit" class="btn btn-primary btn-sm pull-right" onclick="return checkTrip();" id="saveTrip">
                  Сохранить
              </button>
          </div>
        </div>
      </div>
    </div>


    <div class="col-lg-4">
      <div class="bs-component">
        Установите контрольные точки!<br/>
        Левый берег:
        <hr/>
        <div class="h5" id="leftCoast">
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
          <div id="errorLeftBank" class="label label-warning"></div>
        </div>
      </div>
    </div>
    <div class="col-lg-4">
      <div class="bs-component">
        Установите контрольные точки!<br/>
        Правый берег:
        <hr/>
        <div class="h5" id="rightCoast">
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
          <div id="errorRightBank" class="label label-warning"></div>
        </div>
      </div>

      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
    </div>
    </form:form>
  </div>
</div>


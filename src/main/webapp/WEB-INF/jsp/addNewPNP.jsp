<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@page session="true" %>

<title>Add PNP</title>
<link rel="stylesheet" href="/assets/css/adminStyle.css">
<c:set var="js" value="/assets/js/"/>
<script src="${js}validatePoint.js"></script>
<script src="https://maps.googleapis.com/maps/api/js?v=AIzaSyAlMDftXoxe0Ig9Dpip_Y0TCuLRWA_TVqg&sensor=false"></script>
<script>
  var map;
  var markers = [];

  function initialize() {
    var myLatlng = new google.maps.LatLng(48.536764, 34.571143);
    var mapOptions = {
      zoom: 11,
      center: myLatlng
    };
    map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    google.maps.event.addListener(map, 'click', function (e) {
      placeMarker(e.latLng, map);
    });
  }

  function placeMarker(position, map) {

    deleteMarkers();

    var marker = new google.maps.Marker({
      position: position,
      map: map
    });
    markers.push(marker);

    $('#latitude').val(marker.getPosition().lat());
    $('#longitude').val(marker.getPosition().lng());
    map.panTo(position);
  }

  // Sets the map on all markers in the array.
  function setAllMap(map) {
    for (var i = 0; i < markers.length; i++) {
      markers[i].setMap(map);
    }
  }

  function deleteMarkers() {
    setAllMap(null);
    markers = [];
  }

  google.maps.event.addDomListener(window, 'load', initialize);

</script>

<div class="row">
  <div class="col-lg-12">
    <div class="well bs-component">
      <div class="row">
        <div class="col-lg-6">
          <sf:form class="form-horizontal" method="post" modelAttribute="point" role="form" id="form">
            <fieldset>
              <div class="form-group" id="enterlatitude">
                <label class="control-label col-sm-2" for="latitude">Широта</label>
                <div class="col-sm-10">
                  <sf:input path="latitude" id="latitude" class="form-control" value="" placeholder="Введите"/>
                  <span id="latitudeError" class="center-block hidden text-danger">Широта должна быть числом 12.345678</span>
                </div>
              </div>
              <div class="form-group" id="enterlongitude">
                <label class="control-label col-sm-2" for="longitude">Долгота</label>
                <div class="col-sm-10">
                  <sf:input path="longitude" id="longitude" class="form-control" placeholder="Введите"/>
                  <span id="longitudeError" class="center-block hidden text-danger">Долгота должна быть числом 12.345678</span>
                </div>
              </div>
              <div class="form-group">
                <label class="control-label col-sm-2" for="left">Берег Днепра</label>
                <div class="col-sm-10" id="left">
                  <sf:radiobutton path="left" value="true"/>Левый
                  <sf:radiobutton path="left" value="false"/>Правый
                </div>
              </div>
              <div class="form-group" id="enterdescription">
                <label class="control-label col-sm-2" for="description">Описание точки</label>
                <div class="col-sm-10">
                  <sf:input path="description" id="description" class="form-control" placeholder="Введите описание точки"/>
                  <span id="descriptionError" class="center-block hidden text-danger">Должно быть не менее 5 символов</span>
                </div>
              </div>
            </fieldset>
            <div class="form-group pull-right">
              <div class="col-sm-12">
                <a class="btn btn-warning" href="/admin/usersList">Отмена</a>
                <button type="submit" id="send" class="btn btn-primary">Сохранить</button>
              </div>
            </div>
          </sf:form>
        </div>
        <div class="col-lg-6">
          <div id="map-canvas" style="height:400px; width:100%"></div>
        </div>
      </div>
    </div>
  </div>
</div>
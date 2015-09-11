<%@page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<script src="assets/js/jquery.googlemap.js"></script>
<script src="//www.google.fr/jsapi"></script>
<script>
  google.load("maps", "3.4", {
    other_params: "v=AIzaSyAlMDftXoxe0Ig9Dpip_Y0TCuLRWA_TVqg&sensor=false"
  });
</script>

<div class="col-lg-12">
  <div class="bs-component">
    <div class="panel panel-default">
      <div class="panel-body">
        <%--Подключение карты--%>
        <script
            src="https://maps.googleapis.com/maps/api/js?v=AIzaSyAlMDftXoxe0Ig9Dpip_Y0TCuLRWA_TVqg&sensor=false"></script>
        <%--Отображение карыт--%>
        <script>
          var map;
          function initialize() {
            var mapOptions = {
              zoom: 8,
              center: new google.maps.LatLng(48.466169, 35.014089),
              mapTypeId: google.maps.MapTypeId.ROADMAP
            };
            map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
          }
          google.maps.event.addDomListener(window, 'load', initialize);

          <%--Добавление маркеров на карте--%>


          //                        function placeMarker(position, map) {
          //                            var marker = new google.maps.Marker({
          //                                position: position,
          //                                map: map
          //                            });
          //                            map.panTo(position);
          //                        }
          //                        google.maps.event.addListener(map, 'click', function(e) {
          //                            placeMarker(e.latLng, map);
          //                        });


          <%--Добавление маркеров кнопкой--%>

          $(function () {
            var latlng = new google.maps.LatLng(48.466169, 35.014089);

            function addmarker(latilongi) {
              var marker = new google.maps.Marker({
                position: latilongi,
                title: 'new marker',
                draggable: true,
                map: map
              });
              map.setCenter(marker.getPosition())
            }

            $('#btnaddmarker').on('click', function () {
              addmarker(latlng)
            })
          });


        </script>

        <div id="map-canvas" style="height:500px; width:100%; margin-bottom: 10px;"></div>
        <div class="panel panel-default">
          <div class="panel-body">
            <a class="btn btn-primary" id="btnaddmarker">Add mark</a>
            <%--<a class="btn btn-primary" id="btnaddmarker2">Add mark jquery</a>--%>
          </div>
        </div>

        <%--=========================================================--%>
        <%--Форма для добавления точки на карту--%>
        <form id="add-point" action="" method="post">
          <fieldset>
            <legend>Добавить точку на карту</legend>
            <div class="input">
              <label for="name">Широта</label>
              <input type="text" name="name" id="name" value="">
            </div>
            <div class="input">
              <label for="address">Долгота</label>
              <input type="text" name="address" id="address" value="">
            </div>
            <%--<button type="submit">Добавить точку</button>--%>
          </fieldset>
        </form>
      </div>
    </div>
  </div>
</div>
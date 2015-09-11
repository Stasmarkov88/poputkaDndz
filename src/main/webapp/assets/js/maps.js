$(document).ready(function () {

    alert('привет! А документ-то готов!');

    var map;
    var markers = [];
    var x = [];

    function initialize() {
        var myLatlng = new google.maps.LatLng(48.536764, 34.571143);
        var mapOptions = {
            zoom: 11,
            center: myLatlng,
            mapTypeId: google.maps.MapTypeId.ROADMAP
        };
        map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
    }

    google.maps.event.addDomListener(window, 'load', initialize);

    //получение и отображение все точек из объекта
    //$(function() {
    //    x = [
    //        <c:forEach var="point" items="${points}" varStatus="status">
    //        {lat: "${point.latitude}", lng: "${point.longitude}", des:"${point.description}"},
    //        </c:forEach>
    //    ];
    //
    //    function setMarkers(map, locations) {
    //        for (var i = 0; i < locations.length; i++) {
    //            var pleace = locations[i];
    //            var latt = pleace.lat;
    //            var long = pleace.lng;
    //            var dess = pleace.des;
    //            var myLatLng = new google.maps.LatLng(latt, long);
    //            var marker = new google.maps.Marker({
    //                position: myLatLng,
    //                map: map,
    //                title: dess
    //            });
    //            markers.push(marker);
    //        }
    //    }
    //
    //    google.maps.event.addDomListener(window, 'load', function(){setMarkers(map, x)});
    //});

    //    для календаря
    $(function () {
        $('#datetimepicker1').datetimepicker(
                {
                    language: 'ru',
                    defaultDate: new Date(),
                    minDate: new Date()
                }
        );
    });

    $(function () {
        $('#datetimepicker2').datetimepicker(
                {
                    language: 'ru',
                    defaultDate: new Date(),
                    minDate: new Date()
                }
        );
    });


});
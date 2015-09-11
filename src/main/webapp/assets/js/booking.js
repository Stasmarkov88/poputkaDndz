$(document).ready(function () {
    var animationFadeIn = 'animated fadeIn';
    var animationFadeOut = 'animated fadeOut';
    var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
    var previous;
    var comment;
    $("input:radio").on("change", function () {
        var descr = $(this).val();
        $("#description").val(descr);
        previous = $("#description").val();
    });

    $("#comment").on("change", function (){
        comment = $(this).val();
    });

    $('#pointForm').bind('submit', function (event) {
        if (!$('#description').val().length) {
            event.preventDefault();
            $("#confirmError").removeClass("displayNone").addClass(animationFadeIn).one(animationEnd, function () {
                $(this).removeClass(animationFadeIn);
                $(this).addClass("displayNone");
            });
        }
        previous = previous.concat(" ");
        previous = previous.concat(comment);
        $("#description").val(previous);
    });
});
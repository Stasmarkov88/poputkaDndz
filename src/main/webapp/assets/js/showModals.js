$(document).ready(function () {
    var animationFadeInDown = 'animated fadeInDown';
    var animationFadeOutUp = 'animated fadeOutUp';
    var animationFadeIn = 'animated fadeIn';
    var animationFadeOut = 'animated fadeOut';
    var animationEnd = 'webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend';
    $('#deleteProfile').on('click', function (event) {
        if ($('#deleteContent').hasClass('displayNone')) {
            $('#deleteContent').toggleClass('displayNone', false);
            $('#deleteContent').addClass(animationFadeInDown).one(animationEnd, function () {
                $(this).removeClass(animationFadeInDown);
            });
        }else{
            $('#deleteContent').addClass(animationFadeOutUp).one(animationEnd, function () {
                $(this).removeClass(animationFadeOutUp);
                $('#deleteContent').toggleClass('displayNone', true);
            });
        }
            event.preventDefault();

    });
    $('#continue').on('click', function (event) {
        $('#deleteContent').addClass(animationFadeOutUp).one(animationEnd, function () {
            $(this).removeClass(animationFadeOutUp);
            $('#deleteContent').toggleClass('displayNone', true);
        });
        event.preventDefault();
    });
    $('#changePassword').on('click', function (event) {
        if ($('#changeContent').hasClass('displayNone')) {
            $('#changeContent').toggleClass('displayNone', false);
            $('#changeContent').addClass(animationFadeInDown).one(animationEnd, function () {
                $(this).removeClass(animationFadeInDown);
            });
        }else{
            $(this).removeClass(animationFadeOutUp);
            $('#changeContent').toggleClass('displayNone', true);
            $('#newPassword').val("");
            $('#newPassword').removeClass('has-success').removeClass('has-error');
            $('#passwordError').removeClass('show').addClass('hidden');
            $('.glyphicon').remove();
            $('#currentPassword').val("");
            $('#currentPassword').removeClass('has-success').removeClass('has-error');
            $('#currentPassError').removeClass('show').addClass('hidden');
            $('#repeatPassword').val("");
            $('#repeatPassword').removeClass('has-success').removeClass('has-error');
            $('#comError').removeClass('show').addClass('hidden');
        }
        event.preventDefault();
    });
    $('#cancel').on('click', function (event) {
        $('#changeContent').addClass(animationFadeOutUp).one(animationEnd, function () {
            $(this).removeClass(animationFadeOutUp);
            $('#changeContent').toggleClass('displayNone', true);
            $('#newPassword').val("");
            $('#newPassword').removeClass('has-success').removeClass('has-error');
            $('#passwordError').removeClass('show').addClass('hidden');
            $('.glyphicon').remove();
            $('#currentPassword').val("");
            $('#currentPassword').removeClass('has-success').removeClass('has-error');
            $('#currentPassError').removeClass('show').addClass('hidden');
            $('#repeatPassword').val("");
            $('#repeatPassword').removeClass('has-success').removeClass('has-error');
            $('#comError').removeClass('show').addClass('hidden');

        });
        event.preventDefault();
    });

    var message = $('#successMessage');
    if (!message.hasClass("displayNone")) {
        message.addClass(animationFadeInDown).one(animationEnd, function () {
            $(this).removeClass(animationFadeIn);
            $(this).addClass(animationFadeOut).one(animationEnd, function () {
                $(this).removeClass(animationFadeOut);
            });
            $(this).addClass("displayNone");
        });
    }
    var message = $('#errorMessage');
    if (!message.hasClass("displayNone")) {
        message.addClass(animationFadeInDown).one(animationEnd, function () {
            $(this).removeClass(animationFadeIn);
            $(this).addClass(animationFadeOut).one(animationEnd, function () {
                $(this).removeClass(animationFadeOut);
            });
            $(this).addClass("displayNone");
        });
    }
    function cancelChangePassword(event){
        $('#changeContent').addClass(animationFadeOutUp).one(animationEnd, function () {
            $(this).removeClass(animationFadeOutUp);
            $('#changeContent').toggleClass('displayNone', true);
            $('#newPassword').val("");
            $('#newPassword').removeClass('has-success').removeClass('has-error');
            $('#passwordError').removeClass('show').addClass('hidden');
            $('.glyphicon').remove()
            $('#currentPassword').val("");
            $('#currentPassword').removeClass('has-success').removeClass('has-error');
            $('#currentPassError').removeClass('show').addClass('hidden');
            $('#repeatPassword').val("");
            $('#repeatPassword').removeClass('has-success').removeClass('has-error');
            $('#comError').removeClass('show').addClass('hidden');

        });
    }
});

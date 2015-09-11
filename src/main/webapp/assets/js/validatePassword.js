/**
 * Created by SCJP on 13.03.2015.
 */
$(document).ready(function () {
    var validate = {
        'password': function () {
            var name = $('#newPasswordBlock');
            var ele = $('#newPassword');
            if (ele.val().length < 3) {
                validate.errors = true;
                name.removeClass('has-success').addClass('has-error has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
                $('#passwordError').removeClass('hidden').addClass('show');
            } else {
                name.removeClass('has-error').addClass('has-success has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
                $('#passwordError').removeClass('show').addClass('hidden');
            }
        },

        'compare': function () {
            var name = $('#repeatPasswordBlock');
            var pas = $('#newPassword');
            var ele = $('#repeatPassword');
            if (ele.val() === "" || (ele.val() != pas.val())) {
                validate.errors = true;
                name.removeClass('has-success').addClass('has-error has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
                $('#comError').removeClass('hidden').addClass('show');
            } else {
                name.removeClass('has-error').addClass('has-success has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
                $('#comError').removeClass('show').addClass('hidden');
            }
        },

        'current': function () {
            var name = $('#currentPasswordBlock');
            var ele = $('#currentPassword');
            if (ele.val().length < 3) {
                validate.errors = true;
                name.removeClass('has-success').addClass('has-error has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
                $('#currentPassError').removeClass('hidden').addClass('show');
            } else {
                name.removeClass('has-error').addClass('has-success has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
                $('#currentPassError').removeClass('show').addClass('hidden');
            }
        },


        'sendPassword': function () {
            if (!validate.errors) {
                $('#passForm').submit();
            }
        }
    };

    $('#sendPass').click(function () {
        validate.errors = false;
        validate.password();
        validate.compare();
        validate.current();
        validate.sendPassword();
        return false;
    });

    $('#newPassword').change(validate.password);
    $('#repeatPassword').change(validate.compare);
    $('#currentPassword').change(validate.current);

});
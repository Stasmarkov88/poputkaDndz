$(document).ready(function () {
    var validate = {
        'latitude': function () {
            var latitude = $('#enterlatitude');
            var ele = $('#latitude');
            var isCoordinate = /-?\d{1,3}\.\d+/;
            if (!ele.val().match(isCoordinate)) {
                validate.errors = true;
                latitude.removeClass('has-success').addClass('has-error has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
                $('#latitudeError').removeClass('hidden').addClass('show');
            } else {
                latitude.removeClass('has-error').addClass('has-success has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
                $('#latitudeError').removeClass('show').addClass('hidden');
            }
        },
        'longitude': function () {
            var longitude = $('#enterlongitude');
            var ele = $('#longitude');
            var isCoordinate = /-?\d{1,3}\.\d+/;
            if (!ele.val().match(isCoordinate)) {
                validate.errors = true;
                longitude.removeClass('has-success').addClass('has-error has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
                $('#longitudeError').removeClass('hidden').addClass('show');
            } else {
                longitude.removeClass('has-error').addClass('has-success has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
                $('#longitudeError').removeClass('show').addClass('hidden');
            }
        },
        'left': function () {
            if ($('input[name="left"]:checked').length === 0) {
                validate.errors = true;
                $('#left').removeClass('has-success').addClass('has-error');
                $('#leftError').removeClass('hidden').addClass('show');
            } else {
                $('#left').removeClass('has-error').addClass('has-success');
                $('#leftError').removeClass('show').addClass('hidden');
            }
        },
        'description': function () {
            var description = $('#enterdescription');
            var ele = $('#description');
            if (ele.val().length < 3) {
                validate.errors = true;
                description.removeClass('has-success').addClass('has-error has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-remove form-control-feedback"></span>');
                $('#descriptionError').removeClass('hidden').addClass('show');
            } else {
                description.removeClass('has-error').addClass('has-success has-feedback');
                $('.glyphicon').remove();
                ele.after('<span class="glyphicon glyphicon-ok form-control-feedback"></span>');
                $('#descriptionError').removeClass('show').addClass('hidden');
            }
        },

        'sendIt': function () {
            if (!validate.errors) {
                $('#form').submit();
            }
        }
    };

    $('#send').click(function () {
        validate.errors = false;
        validate.latitude();
        validate.longitude();
        validate.left();
        validate.description();
        validate.sendIt();
        return false;
    });


    $('#latitude').change(validate.latitude);
    $('#longitude').change(validate.longitude);
    $('input[name="isleft"]').change(validate.left);
    $('#description').change(validate.description);
});
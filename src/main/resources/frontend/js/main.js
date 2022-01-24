function employeeDelete(id) {
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "DELETE", ("/employee/" + id), false );
    xmlHttp.send( null );
    location.reload();
}

function updateEmployee(data) {
    const xmlHttp = new XMLHttpRequest();
    const body = JSON.stringify(data);
    xmlHttp.open("PATCH", ('/employee/' + data.id), false);
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.send(body);
    location.reload();
}

$('saveEmp').addEventListener("click", function () {
    const data = {
        id: $('#id').attr("value"),
        firstName: $('#firstName').html(),
        lastName: $('#lastName').html(),
        surName: $('#surName').html(),
        address: $('#address').html(),
        age: $('#age').html(),
        start: $('#start').html(),
        finish: $('#finish').html(),
        region: $('#region').html(),
        district: $('#district').html()
    }
    console.log(data)
    updateEmployee(data)})

(function() {
    'use strict';
    window.addEventListener('load', function() {
        var forms = document.getElementsByClassName('needs-validation');
        var validation = Array.prototype.filter.call(forms, function(form) {
            form.addEventListener('submit', function(event) {
                if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                }
                form.classList.add('was-validated');
            }, false);
        });
    }, false);
})();

$(function() {

    $('form').validate({
        highlight: function(element, errorClass) {
            $(element).add($(element).parent()).addClass("invalidElem");
        },
        unhighlight: function(element, errorClass) {
            $(element).add($(element).parent()).removeClass("invalidElem");
        },
        errorElement: "div",
        errorClass: "errorMsg"
    });

});

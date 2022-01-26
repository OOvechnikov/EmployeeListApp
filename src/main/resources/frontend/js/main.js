function deleteEmployee(id) {
    const xhr = new XMLHttpRequest();
    xhr.open( "DELETE", ("/employee/" + id), true );
    xhr.send( null );
    xhr.onreadystatechange = function () {
        returnToHome(xhr);
    }
}

function updateEmployee(data) {
    const xhr = new XMLHttpRequest();
    const body = JSON.stringify(data);
    xhr.open("PATCH", ('/employee/' + data.id), true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(body);
    xhr.onreadystatechange = function () {
        window.location.reload();
    }
}

function createEmployee(data) {
    const xhr = new XMLHttpRequest();
    const body = JSON.stringify(data);
    xhr.open("POST", '/employee', true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(body);
    xhr.onreadystatechange = function () {
        const response = JSON.parse(xhr.response);
        if (xhr.status === 200 && response.result === true) {
            window.location = response.createdId;
        } else {
            returnToHome(xhr);
        }
    }
}

function returnToHome(xhr) {
    if (xhr.status === 200) {
        window.location = "/home";
    }
}
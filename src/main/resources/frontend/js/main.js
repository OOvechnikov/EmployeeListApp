function deleteEmployee(id) {
    const xhr = new XMLHttpRequest();
    xhr.open( "DELETE", ("/employee/" + id), true );
    xhr.send( null );
    console.log('delete');
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
        returnToHome(xhr);
    }
}

function createEmployee(data) {
    const xhr = new XMLHttpRequest();
    const body = JSON.stringify(data);
    xhr.open("POST", '/employee', true);
    xhr.setRequestHeader("Content-Type", "application/json");
    console.log('body');
    xhr.send(body);
    xhr.onreadystatechange = function () {
        returnToHome(xhr);
    }
}

function returnToHome(xhr) {
    if (xhr.status === 200) {
        console.log(200)
        window.location = "/home";
    }
}
function employeeDelete(id) {
    const xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "DELETE", ("/employee/" + id), false ); // false for synchronous request
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
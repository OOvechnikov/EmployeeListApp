function employeeDelete(id) {
    var xmlHttp = new XMLHttpRequest();
    console.log(xmlHttp);
    xmlHttp.open( "DELETE", ("/employee/" + id), false ); // false for synchronous request
    xmlHttp.send( null );
    location.reload();
}
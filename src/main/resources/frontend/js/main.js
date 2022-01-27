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

function exportToExcel(data) {
    const xhr = new XMLHttpRequest();
    const body = JSON.stringify(data);
    xhr.open("POST", '/home/export', true);
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.responseType = 'blob';
    xhr.send(body);
    xhr.onreadystatechange = function () {
        if (this.status === 200) {
            let blob = this.response;
            if (blob != null) {
                let downloadLink = window.document.createElement('a');
                let contentTypeHeader = xhr.getResponseHeader("Content-Type");
                downloadLink.href = window.URL.createObjectURL(new Blob([blob], { type: contentTypeHeader }));
                downloadLink.download = xhr.getResponseHeader('Content-Disposition');
                document.body.appendChild(downloadLink);
                downloadLink.click();
                document.body.removeChild(downloadLink);
            }
        }
    }
}

function returnToHome(xhr) {
    if (xhr.status === 200) {
        window.location = "/home";
    }
}
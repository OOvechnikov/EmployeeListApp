window.addEventListener('load', function() {
    const queryString = window.location.search;
    const urlParams = new URLSearchParams(window.location.search);
    if (queryString !== '') {
        document.getElementById('nameSearch').value = urlParams.get('name');
        document.getElementById('regionSearch').value = urlParams.get('region');
        document.getElementById('districtSearch').value = urlParams.get('district');
    }

    const searchButton = document.getElementById('searchButton');
    searchButton.addEventListener("click", (event)=> {
        event.preventDefault();
        event.stopPropagation();
        const nameValue = document.getElementById('nameSearch').value;
        let regionValue = document.getElementById('regionSearch').value;
        let districtValue = document.getElementById('districtSearch').value;
        if (regionValue === 'Region') {
            regionValue = '';
        }
        if (districtValue === 'District') {
            districtValue = '';
        }
        window.location = ('/home?name=' + nameValue + '&region=' + regionValue + '&district=' +districtValue);
    })
}, false);

document.addEventListener('DOMContentLoaded', function () {
    const table = document.getElementById('sortable');
    const headers = table.querySelectorAll('.sort');
    const tableBody = table.querySelector('tbody');
    const rows = tableBody.querySelectorAll('tr');

    const directions = Array.from(headers).map(function(header) {
        return '';
    });

    const transform = function (index, content) {
        const type = headers[index].getAttribute('data-type');
        switch (type) {
            case 'number': return parseFloat(content);
            case 'string':
            default: return content;
        }
    };

    const sortColumn = function (index) {
        const direction = directions[index] || 'asc';
        const multiplier = (direction === 'asc') ? 1 : -1;
        const newRows = Array.from(rows);

        newRows.sort(function (rowA, rowB) {
            const cellA = rowA.querySelectorAll('.sortingValue')[index].innerHTML;
            const cellB = rowB.querySelectorAll('.sortingValue')[index].innerHTML;

            const a = transform(index, cellA);
            const b = transform(index, cellB);

            switch (true) {
                case a > b: return 1 * multiplier;
                case a < b: return -1 * multiplier;
                case a === b: return 0;
            }
        });

        [].forEach.call(rows, function (row) {
            tableBody.removeChild(row);
        });

        directions[index] = direction === 'asc' ? 'desc' : 'asc';

        newRows.forEach(function (newRow) {
            tableBody.appendChild(newRow);
        });
    };

    [].forEach.call(headers, function (header, index) {
        header.addEventListener('click', function () {
            sortColumn(index);
        });
    });
});

const exportButton = document.getElementById('exportButton');
exportButton.addEventListener('click', function() {
    const headRow = document.getElementsByClassName('head-row');
    const bodyRows = document.getElementsByClassName('body-row');
    let head = [];
    const ths = headRow[0].getElementsByTagName('th');
    for (let i = 0; i < ths.length - 1; i++) {
        head[i] = ths[i].innerText;
    }
    let body = [];
    for (let i = 0; i < bodyRows.length; i++) {
        let thd = bodyRows[i].getElementsByTagName('td');
        let row = [];
        for (let j = 0; j < thd.length - 1; j++) {
            row[j] = thd[j].innerText;
        }
        body[i] = row;
    }
    let data = {
        head: head,
        body: body
    }
    exportToExcel(data);
}, false);
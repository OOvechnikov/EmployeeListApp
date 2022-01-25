window.addEventListener('load', function() {
    const searchButton = document.getElementById('searchButton');
    searchButton.addEventListener("click", (event)=> {
        console.log('searchButton');
        event.preventDefault();
        event.stopPropagation();
        const searchValue = document.getElementById('searchValue').value;
        console.log(searchValue);
        window.location = ('/home?search=' + searchValue);
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
    console.log(directions);

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
            console.log('a=' + a);
            console.log('b=' + b);

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
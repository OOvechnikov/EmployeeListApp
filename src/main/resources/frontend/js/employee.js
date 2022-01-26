const editButton = document.getElementById("saveEmp")
const deleteButton = document.getElementById("deleteEmp")

const id = document.getElementById("id")
const firstName = document.getElementById("firstName")
const lastName = document.getElementById("lastName")
const secondName = document.getElementById("secondName")
const address = document.getElementById("address")
const age = document.getElementById("age")
const start = document.getElementById("start")
const finish = document.getElementById("finish")
const region = document.getElementById("region")
const district = document.getElementById("district")

window.addEventListener('load', function() {
    const forms = document.getElementsByClassName('needs-validation');
    Array.prototype.filter.call(forms, function(form) {
        editButton.addEventListener("click", (event)=> {
            event.preventDefault();
            event.stopPropagation();
            if (form.checkValidity() === true) {
                const data = {
                    id: id.getAttribute("value"),
                    firstName: firstName.value,
                    lastName: lastName.value,
                    secondName: secondName.value,
                    address: address.value,
                    age: age.value,
                    start: start.value,
                    finish: finish.value,
                    region: region.value,
                    district: district.value
                }
                console.log(data);
                if (data.id === '-1') {
                    console.log('create');
                    createEmployee(data);
                } else {
                    console.log('update');
                    updateEmployee(data);
                }
            }
            form.classList.add('was-validated');
        })
        deleteButton.addEventListener('click', (event) => {
            console.log('deleteButton');
            event.preventDefault();
            event.stopPropagation();
            deleteEmployee(id.getAttribute('value'));
            console.log('deleteButtonEnd');
        })
    });
}, false);
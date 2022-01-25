const button = document.getElementById("saveEmp")

const id = document.getElementById("id")
const firstName = document.getElementById("firstName")
const lastName = document.getElementById("lastName")
const secName = document.getElementById("secName")
const address = document.getElementById("address")
const age = document.getElementById("age")
const start = document.getElementById("start")
const finish = document.getElementById("finish")
const region = document.getElementById("region")
const district = document.getElementById("district")

window.addEventListener('load', function() {
    console.log('enter');
    const forms = document.getElementsByClassName('needs-validation');
    console.log('needs-validation');
    Array.prototype.filter.call(forms, function(form) {
        console.log('validation');
        button.addEventListener("click", (event)=> {
            console.log('listner');
            event.preventDefault();
            event.stopPropagation();
            if (form.checkValidity() === false) {
                console.log('if');
            } else {
                console.log('else');
                const data = {
                    id: id.getAttribute("value"),
                    firstName: firstName.value,
                    lastName: lastName.value,
                    secName: secName.value,
                    address: address.value,
                    age: age.value,
                    start: start.value,
                    finish: finish.value,
                    region: region.value,
                    district: district.value
                }
                console.log(data)
                if (data.id === '0') {
                    console.log('create');
                    createEmployee(data);
                } else {
                    console.log('update');
                    updateEmployee(data);
                }
            }
            form.classList.add('was-validated');
        })
        console.log('listner');
    });
}, false);
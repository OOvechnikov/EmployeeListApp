const button = document.getElementById("saveEmp")

const id = document.getElementById("id")
const firstName = document.getElementById("firstName")
const lastName = document.getElementById("lastName")
const surName = document.getElementById("surName")
const address = document.getElementById("address")
const age = document.getElementById("age")
const start = document.getElementById("start")
const finish = document.getElementById("finish")
const reg = document.getElementById("reg")
const dist = document.getElementById("dist")

button.addEventListener("click", (event)=> {
    event.preventDefault();
    onSubmit()})

function onSubmit() {
    const data = {
        id: id.getAttribute("value"),
        firstName: firstName.value,
        lastName: lastName.value,
        surName: surName.value,
        address: address.value,
        age: age.value,
        start: start.value,
        finish: finish.value,
        region: reg.value,
        district: dist.value
    }
    console.log(data)
    updateEmployee(data)
}



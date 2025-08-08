let refreshButton           = document.getElementById("refreshButton");
let registeredEmployeeList  = document.getElementById("registeredEmployeeList");

function getEmployeeList() {
    while (registeredEmployeeList.hasChildNodes()) {
        registeredEmployeeList.removeChild(registeredEmployeeList.firstChild)
    }
    fetch("http://localhost:8070/EmployeeList")
    .then(Response => {
        if (!Response.ok) {
            alert("An error has occurred!");
            throw new error("An unknown error has occurred");
        }
        return Response.json();
    })
    .then(Data => {
        for (i = 0; i < Data.length; i++) {
            let entry = document.createElement("li");
            entry.textContent = Data[i].name + " " + "ID: " + Data[i].id;
            registeredEmployeeList.appendChild(entry);
        }
    })
}
window.onload = () => getEmployeeList();
refreshButton.addEventListener("click", () => getEmployeeList());
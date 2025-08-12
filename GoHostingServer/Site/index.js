let refreshButton           = document.getElementById("refreshButton");
let registeredEmployeeList  = document.getElementById("registeredEmployeeList");

function getEmployeeList() {
    while (registeredEmployeeList.hasChildNodes()) {
        registeredEmployeeList.removeChild(registeredEmployeeList.firstChild)
    }
    fetch("http://localhost:8070/EmployeeList")
    .then(response => {
        if (!response.ok) {
            throw new Error("An unknown error has occurred");
        }
        return response.json();
    })
    .then(parsedJson => {
        for (i = 0; i < parsedJson.length; i++) {
            let entry = document.createElement("li");
            entry.textContent = parsedJson[i].name + " " + "ID: " + parsedJson[i].id;
            registeredEmployeeList.appendChild(entry);
        }
    })
    .catch(error => {
        alert(`The following error has occurred: ${error}`);
    });
}
window.onload = () => getEmployeeList();
refreshButton.addEventListener("click", () => getEmployeeList());
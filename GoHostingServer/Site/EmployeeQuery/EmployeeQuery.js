const EmployeeInfoButton    = document.getElementById("GetEmployeeInfoButton");
let nameHeader              = document.getElementById("nameHeader");
let positionHeader          = document.getElementById("positionHeader");
let idHeader                = document.getElementById("idHeader");
let weeklySalaryHeader      = document.getElementById("weeklySalaryHeader");
let monthlySalaryHeader     = document.getElementById("monthlySalaryHeader");
let yearlySalaryHeader      = document.getElementById("yearlySalaryHeader");
let queryIdInput            = document.getElementById("queryIdInput");

EmployeeInfoButton.addEventListener("click", () => {
    if (queryIdInput.value.trim() === "") {
        alert("Do not leave the input field blank!");
    }
    const path = `http://localhost:8070/EmployeeQuery/${queryIdInput.value}`;
    fetch(path)
    .then(response => {
        if (response.status === 404) {
            throw Error("ID not found");
        }
        return response.json();
    })
    .then(data => {
        console.log(data);
        nameHeader.textContent          = `Name: ${data["name"]}`;
        positionHeader.textContent      = `Position: ${data["position"]}`;
        idHeader.textContent            = `ID: ${data["id"]}`;
        weeklySalaryHeader.textContent  = `Weekly Salary: ${data["weeklySalary"]}`;
        monthlySalaryHeader.textContent = `Monthly Salary: ${data["monthlySalary"]}`;
        yearlySalaryHeader.textContent  = `Yearly Salary: ${data["yearlySalary"]}`;
    })
    .catch(Error => {
        alert(`The following error has occurred: ${Error}`);
    });
});

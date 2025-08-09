let firstNameTextArea       = document.getElementById("firstNameTextArea");
let lastNameTextArea        = document.getElementById("lastNameTextArea");        
let positionTextArea        = document.getElementById("positionTextArea");
let idTextArea              = document.getElementById("idTextArea");
let monthlySalaryTextArea   = document.getElementById("monthlySalaryTextArea");
let submitButton            = document.getElementById("submitButton");

submitButton.addEventListener("click", () => {
    let path = `/EmployeeCreate/${firstNameTextArea.value}/${lastNameTextArea.value}/${positionTextArea.value}
    /${idTextArea.value}/${monthlySalaryTextArea.value}`;

    fetch("http://localhost:8070" + path, {method: "POST"})
    
    .then(response => {
        if (!response.ok) {
            if (response.status === 400) {
                alert("Invalid input! Monthly salary must be of numeric value and may not be negative!");
                throw new error("Bad Request error");
            } else if (response.status === 409) {
                alert("This ID is already taken! Try another one.");
                throw new error("Conflict error");
            } else {
                alert("An unknown error has occurred!");
                throw new error("Unknown error");
            }
        }
        if (response.status === 201) {
            alert("Successfully created employee!");
        }
    })
});
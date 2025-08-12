let FireIdTextArea  = document.getElementById("FireIdTextArea");
let FireButton      = document.getElementById("FireButton");

FireButton.addEventListener("click", () => {
    fetch(`http://localhost:8070/EmployeeFire/${FireIdTextArea.value}`, {method: "POST"})
    .then(response => {
        if (!response.ok) {
            if (response.status === 404) {
                throw Error("Could not find specified ID");
            }
        alert("Successfully fired employee.");
        }
    })
    .catch(error => {
        alert(`The following error has occurred: ${error}`);
    });
});
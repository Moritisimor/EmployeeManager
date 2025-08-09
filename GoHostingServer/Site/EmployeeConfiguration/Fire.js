let FireIdTextArea  = document.getElementById("FireIdTextArea");
let FireButton      = document.getElementById("FireButton");

FireButton.addEventListener("click", () => {
    fetch(`http://localhost:8070/EmployeeFire/${FireIdTextArea.value}`, {method: "POST"})
    .then(Response => {
        if (!Response.ok) {
            if (Response.status === 404) {
                throw Error("Could not find specified ID");
            }
        alert("Successfully fired employee.");
        }
    })
    .catch(Error => {
        console.log(Error);
        alert("The following error has occurred: " + Error);
    });
});
let FireIdTextArea  = document.getElementById("FireIdTextArea");
let FireButton      = document.getElementById("FireButton");

FireButton.addEventListener("click", () => {
    fetch(`http://localhost:8070/EmployeeFire/${FireIdTextArea.value}`)
    .then(Response => {
        if (!Response.ok) {
            if (Response.status === 404) {
                throw "Could not find specified ID";
            }
        alert("Succesfully fired employee.");
        }
    })
    .catch(Error => {
        console.log(Error);
        alert("The following error has occurred: " + Error);
    });
});
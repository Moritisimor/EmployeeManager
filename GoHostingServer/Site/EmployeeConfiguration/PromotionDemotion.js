let AmountTextArea  = document.getElementById("AmountTextArea");
let IdTextArea      = document.getElementById("IdTextArea");
let DemoteButton    = document.getElementById("DemoteButton");
let PromoteButton   = document.getElementById("PromoteButton");

function promoteOrDemote(operator) {
    fetch(`http://localhost:8070/EmployeeConfig/${operator}/${IdTextArea.value}/${AmountTextArea.value}`, {method: "POST"})
    .then(Response => {
        if (!Response.ok) {
            if (Response.status === 404) {
                throw Error("Wrong ID or maybe wrong operator.");
            }
            if (Response.status === 400) {
                throw Error("Wrong number argument.");
            }
        }
        alert("Successfully promoted employee");
    })
    .catch(Error => {
        alert("The following Error has occurred: " + Error)
    }); 
}

DemoteButton.addEventListener("click", () => promoteOrDemote("demote"));
PromoteButton.addEventListener("click", () => promoteOrDemote("promote"));
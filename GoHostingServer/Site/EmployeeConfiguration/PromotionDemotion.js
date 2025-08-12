let AmountTextArea  = document.getElementById("AmountTextArea");
let IdTextArea      = document.getElementById("IdTextArea");
let DemoteButton    = document.getElementById("DemoteButton");
let PromoteButton   = document.getElementById("PromoteButton");

function promoteOrDemote(operator) {
    fetch(`http://localhost:8070/EmployeeConfig/${operator}/${IdTextArea.value}/${AmountTextArea.value}`, {method: "POST"})
    .then(response => {
        if (!response.ok) {
            if (response.status === 404) {
                throw Error("Wrong ID or maybe wrong operator.");
            }
            if (response.status === 400) {
                throw Error("Wrong number argument.");
            }
        }
        alert(`Succesfully ${operator}d employee`);
    })
    .catch(error => {
        alert(`The following Error has occurred: ${error}`)
    }); 
}

DemoteButton.addEventListener("click", () => promoteOrDemote("demote"));
PromoteButton.addEventListener("click", () => promoteOrDemote("promote"));
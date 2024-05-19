// function showEditBank(id) {
//     document.location.replace("/bank.do?id=" + id);
// }

async function removeBank(id) {
    alert(id);
    const response = await fetch("/api/bank/" + id, {
        method: "DELETE"
    });
    document.location.replace("/bank.do")
}

// function editBank(event) {
//     event.preventDefault()
//     const BankEditForm = document.getElementById("bankEditForm");
//     const formData=new FormData(BankEditForm)
//     console.log(formData.get("id"))
//     fetch("/bank.do", {
//         method: "PUT",
//         body:formData
//     }).then(() => {
//         document.location.replace("/bankDisplay.do?id=" + formData.get("id"));
//     });
// }

// function edit(id) {
//     fetch(`/api/bank/`+ id)
//         .then(response => response.json())
//         .then(data => {
//             document.getElementById("edit-id").value = data.id;
//             document.getElementById("edit-name").value = data.name;
//             document.getElementById("edit-accountNumber").value = data.accountNumber;
//             document.getElementById("edit-branchCode").value = data.branchCode;
//             document.getElementById("edit-branchName").value = data.branchName;
//             document.getElementById("edit-accountType").value = data.accountType;
//             document.getElementById("edit-accountBalance").value = data.accountBalance;
//             document.getElementById("edit-form").style.display = "block";
//         })
//         .catch(error => console.error('Error:', error));
// }

// async function updateBank() {
//     const form = document.getElementById("edit-bank-form");
//     const formData = new FormData(form);
//
//     const response = await fetch('/bank.do', {
//         method: 'POST',
//         body: formData
//     })
//     document.location.replace("/bank.do")
// }

// function updateBank() {
//     const form = document.getElementById("edit-bank-form");
//     const formData = new FormData(form);
//
//     fetch('/bank.do', {
//         method: 'PUT',
//         body: formData
//     })
//     document.location.replace("/bank.do")
// }

function edit(id) {
    fetch(`/api/bank/`+id, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => response.json())
        .then(data => {
            // Populate form with the data
            document.getElementById('edit-id').value = id;
            document.getElementById('edit-name').value = data.name;
            document.getElementById('edit-accountNumber').value = data.accountNumber;
            document.getElementById('edit-branchCode').value = data.branchCode;
            document.getElementById('edit-branchName').value = data.branchName;
            document.getElementById('edit-accountType').value = data.accountType;
            document.getElementById('edit-accountBalance').value = data.accountBalance;

            // Show the form
            document.getElementById('edit-form').style.display = 'block';
        })
        .catch(error => console.error('Error:', error));
}

function submitEditForm(event) {
    event.preventDefault();

    const formData = new FormData(event.target);

    fetch('/api/bank', {
        method: 'PUT',
        body: new URLSearchParams(formData)
    })
        .then(response => {
            if (response.ok) {
                window.location.reload();
            } else {
                console.error('Failed to update the bank');
            }
        })
        .catch(error => console.error('Error:', error));
}




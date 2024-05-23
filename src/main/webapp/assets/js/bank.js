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

// function edit(id) {
//     fetch(`/api/bank/`+id, {
//         method: 'GET',
//         headers: {
//             'Content-Type': 'application/json'
//         }
//     })
//         .then(response => response.json())
//         .then(data => {
//             // Populate form with the data
//             document.getElementById('edit-id').value = id;
//             document.getElementById('edit-name').value = data.name;
//             document.getElementById('edit-accountNumber').value = data.accountNumber;
//             document.getElementById('edit-branchCode').value = data.branchCode;
//             document.getElementById('edit-branchName').value = data.branchName;
//             document.getElementById('edit-accountType').value = data.accountType;
//             document.getElementById('edit-accountBalance').value = data.accountBalance;
//
//             // Show the form
//             document.getElementById('edit-form').style.display = 'block';
//         })
//         .catch(error => console.error('Error:', error));
// }
//
// function editBank(event) {
//     event.preventDefault()
//     const bankEditForm = document.getElementById("bankEditForm");
//     const formData=new FormData(bankEditForm)
//     console.log(formData.get("id"))
//     const queryString = new URLSearchParams(new FormData(myForm)).toString();
//     fetch("/bankEdit.do", {
//         method: "PUT",
//         body:formData
//     }).then(() => {
//         document.location.replace("/bankEdit.do?id=" + formData.get("id"));
//     });
// }





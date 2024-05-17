function showEditBank(id) {
    document.location.replace("/bankEdit.do?id=" + id);
}

async function removeBank(id) {
    alert(id);
    const response = await fetch("/api/bank/" + id, {
        method: "DELETE"
    });
    document.location.replace("/bankBox.do")
}

function editBank(event) {
    event.preventDefault()
    const BankEditForm = document.getElementById("bankEditForm");
    const formData=new FormData(BankEditForm)
    console.log(formData.get("id"))
    fetch("/bankEdit.do", {
        method: "PUT",
        body:formData
    }).then(() => {
        document.location.replace("/bankDisplay.do?id=" + formData.get("id"));
    });
}
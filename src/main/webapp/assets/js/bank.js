async function removeBank(id) {
    if (confirm("آیا از حذف بانک " + id + " اطمینان دارید؟")) {
        const response = await fetch("/api/bank/" + id, {
            method: "DELETE"
        });
        document.location.replace("/bank.do")
    }
}

function showEditBank(id) {
    document.location.replace("/bankEdit.do?id=" + id);
}

function editBank(event) {
    event.preventDefault()
    const bankEditForm = document.getElementById("bankEditForm");
    const formData=new FormData(bankEditForm)
    console.log(formData.get("id"))
    fetch("/bankEdit.do", {
        method: "PUT",
        body:formData
    }).then(() => {
        document.location.replace("/bankDisplay.do?id=" + formData.get("id"));
    });
}

function selectBank(id) {
    document.location.replace("/bankDisplay.do?id=" + id);
}
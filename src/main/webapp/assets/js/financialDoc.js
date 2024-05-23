function editFinancialDoc(id) {
    document.location.replace("/financialDocEdit.do?id=" + id);
}

function edit() {
    const myForm = document.getElementById("myForm");
    const queryString = new URLSearchParams(new FormData(myForm)).toString();
    fetch("/financialDocEdit.do?" + queryString, {
        method: "PUT"
    }).then(() => {
        document.location.replace("/financialDoc.do");
    });
}

async function removeFinancialDoc(id) {
    alert(id);
    const response = await fetch("/api/financialDoc/" + id, {
        method: "DELETE"
    });
    document.location.replace("/financialDoc.do")
}

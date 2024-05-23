function editFinancialTransaction(id) {
    document.location.replace("/financialTransactionEdit.do?id=" + id);
}

function edit() {
    const myForm = document.getElementById("myForm");
    const queryString = new URLSearchParams(new FormData(myForm)).toString();
    fetch("/financialTransactionEdit.do?" + queryString, {
        method: "PUT"
    }).then(() => {
        document.location.replace("/financialTransaction.do");
    });
}

async function removeFinancialTransaction(id) {
    alert(id);
    const response = await fetch("/api/financialTransaction/" + id, {
        method: "DELETE"
    });
    document.location.replace("/financialTransaction.do")
}
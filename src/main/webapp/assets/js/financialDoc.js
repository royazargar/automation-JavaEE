function edit(id) {
    alert(id);
}

async function removeFinancialDoc(id) {
    alert(id);
    const response = await fetch("/api/financialDoc/" + id, {
        method: "DELETE"
    });
    document.location.replace("/financialDoc.do")
}
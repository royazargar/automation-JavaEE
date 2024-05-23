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




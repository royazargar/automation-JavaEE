async function removeCashDesk(id) {
    alert(id);
    const response = await fetch("/api/cashDesk/" + id, {
        method: "DELETE"
    });
    document.location.replace("/cashDesk.do")
}
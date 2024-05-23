<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Cash Desk</title>
</head>
<body>

<div id="data-form">
    <form id="myForm">
        <h1>Edit Bank</h1>
        <input class="form-control" type="text" name="id" value="${sessionScope.cashDeskEdit.id}" hidden="hidden">

        <input id="name" class="col form-control" type="text" name="name" value="${sessionScope.cashDeskEdit.name}">

        <input id="cashDeskNumber" class="col form-control" type="number" name="cashDeskNumber"
               value="${sessionScope.cashDeskEdit.cashDeskNumber}">

        <input id="cashBalance" class="col form-control" type="number" name="cashBalance"
               value="${sessionScope.cashDeskEdit.cashBalance}">



        <select name="username" id="user">
            <c:forEach items="${sessionScope.userList}" var="user">
                <option>${user.username}</option>
            </c:forEach>
        </select>

    </form>
    <button id="submit" class="btn btn-warning" onclick="edit()"><i class="fa fa-edit"></i> Edit</button>
</div>

<script>
    function edit() {
        const myForm = document.getElementById("myForm");
        const queryString = new URLSearchParams(new FormData(myForm)).toString();
        fetch("/cashDeskEdit.do?" + queryString, {
            method: "PUT"
        }).then(() => {
            document.location.replace("/cashDesk.do");
        });
    }
</script>

</body>
</html>

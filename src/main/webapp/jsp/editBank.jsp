<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/bank.css">
</head>
<body>
<div id="data-form">
    <form id="myForm">
        <h1>Edit Bank</h1>
        <input class="form-control" type="text" name="id" value="${sessionScope.bankEdit.id}" hidden="hidden">

            <input id="name" class="col form-control" type="text" name="name" value="${sessionScope.bankEdit.name}">


            <input id="s_accountNumber" class="col form-control" type="text" name="accountNumber" value="${sessionScope.bankEdit.accountNumber}">


            <input id="s_branchCode" class="col form-control" type="number" name="branchCode" value="${sessionScope.bankEdit.branchCode}">


            <input id="s_branchName" class="col form-control" type="text" name="branchName" value="${sessionScope.bankEdit.branchName}">


            <input id="s_accountType" class="col form-control" type="text" name="accountType" value="${sessionScope.bankEdit.accountType}">


            <input id="s_accountBalance" class="col form-control" type="number" name="accountBalance" value="${sessionScope.bankEdit.accountBalance}">


    </form>
    <button id="submit" class="btn btn-warning" onclick="edit()"><i class="fa fa-edit"></i> Edit</button>
</div>

<script>
    function edit() {
        const myForm = document.getElementById("myForm");
        const queryString = new URLSearchParams(new FormData(myForm)).toString();
        fetch("/bankEdit.do?" + queryString, {
            method: "PUT"
        }).then(() => {
            document.location.replace("/bank.do");
        });
    }
</script>

</body>
</html>

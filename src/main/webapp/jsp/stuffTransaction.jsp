<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>StuffTransaction</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/stuffTransaction.css">

    <style>
        .error {
            display: none;
            color: red;
        }
    </style>
</head>
<body>
<div class="container-fluid">
    <div id="org-form">
    <form action="stuffTransaction.do" method="post">

        <div class="row mb-3">
            <lable for="id">StuffTransactionId</lable>
            <input type="text" id="id" name="id" placeholder="StuffTransactionId">
        </div>

        <div class="row mb-3">
            <label for="user">stuffTransactionUser</label>
            <input type="text" name="user" id="user" placeholder="StuffTransactionUser">
        </div>

        <div class="row mb-3">
            <label for="section">stuffTransactionSection</label>
            <input type="text" name="section" id="section" placeholder="stuffTransactionSection">
        </div>

        <div class="row mb-3">
            <label for="stuff">stuffTransactionStuff</label>
            <input type="text" name="stuff" id="stuff" placeholder="stuffTransactionStuff">
        </div>

        <div class="row mb-3">
            <label for="type">stuffTransactionType</label>
            <input type="text" name="type" id="type" placeholder="stuffTransactionType">
            <div id="file-msg error">${sessionScope.error}</div>
        </div>
        <div>
            <input type="submit" value="Save">
        </div>
    </form>
</div>

<div id="org-table">
    <table class="table table-hover table-dark">
        <tr>
            <th>id</th>
            <th>user</th>
            <th>section</th>
            <th>stuff</th>
            <th>dateTime</th>
            <th>type</th>
        </tr>
        <thead>
        <tbody>
        <c:forEach var="stuffTransaction" items="${sessionScope.stuffTransactionList}">

            <tr>
                <td>${stuffTransaction.id}</td>
                <td>${stuffTransaction.user}</td>
                <td>${stuffTransaction.section}</td>
                <td>${stuffTransaction.stuf}</td>
                <td>${stuffTransaction.type}</td>

                <td>
                    <button class="btn btn-warning" onclick="edit(stuffTransaction.id)"><i class="fa fa-edit"></i> Edit</button>
                    <button class="btn btn-danger" onclick="remove(stufftransaction.id)"><i class="fa fa-delete"></i>Remove</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>

<div>
    <div>
        <select name="Type" >
            <option value="cash">cash</option>
            <option value="bankDeposit">bankDeposit</option>
            <option value="check">check</option>
            id="type">
        </select>
        <br><br>
        <input type="submit" value="Save">
    </div>
</div>

<script src="../assets/js/stuffTransaction.js"></script>

<form action="servlet/StuffTransaction.do" method="get"></form>
<%--//todo: method get stuff transaction.--%>
</body>
</html>

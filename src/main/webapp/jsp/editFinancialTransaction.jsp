<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div id="data-form">
    <form id="myForm">
        <h1>Edit Financial Transaction</h1>
        <input class="form-control" type="text" name="id" value="${sessionScope.financialTransactionEdit.id}"
               hidden="hidden">

        <select name="username" id="user">
            <c:forEach items="${userList}" var="user">
                <option>${user.username}</option>
            </c:forEach>
        </select>

        <select name="dId" id="department">
            <c:forEach items="${departmentList}" var="department">
                <option value="${department.id}">${department.title}</option>
            </c:forEach>
        </select>

        <select name="paymentType" id="paymentType">
            <c:forEach var="paymentType" items="${sessionScope.paymentTypes}">
                <option value="${paymentType}">${paymentType}</option>
            </c:forEach>
        </select>

        <input id="amount" class="col form-control" type="text" name="amount"
               value="${sessionScope.financialTransactionEdit.amount}">

        <input id="trackingCode" class="col form-control" type="text" name="trackingCode"
               value="${sessionScope.financialTransactionEdit.trackingCode}">

        <select name="transactionType" id="transactionType">
            <c:forEach var="transactionType" items="${sessionScope.transactionTypes}">
                <option value="${transactionType}">${transactionType}</option>
            </c:forEach>
        </select>

        <input type="text" name="date" id="date" placeholder="تاریخ  را وارد کنید" class="formbold-form-input" value="${sessionScope.financialTransactionEdit.faDate}" required/>

    </form>
    <button id="submit" class="btn btn-warning" onclick="edit()"><i class="fa fa-edit"></i> Edit</button>
</div>

<script src="../assets/js/financialTransaction.js"></script>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>جدول بانک ها</title>
    <link rel="stylesheet" href="../../assets/css/myTable.css">
    <link rel="stylesheet" href="../../assets/css/all.css">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../jsp/dashboard.jsp"></jsp:include>

<!--table-->
<div id="bank-table">
    <table class="table table-hover table-primary">
        <thead>
        <tr>
            <th><i class="fa fa-window-minimize"></i></th>
            <th>نام بانک</th>
            <th>شماره حساب</th>
            <th>شماره شعبه</th>
            <th>نام شعبه</th>
            <th>نوع حساب</th>
            <th>موجودی حساب</th>
            <th class="btn"><i class="fa fa-gear"></i></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="bank" items="${sessionScope.bankList}">
            <tr>
                <td>${bank.id}</td>
                <td>${bank.name}</td>
                <td>${bank.accountNumber}</td>
                <td>${bank.branchCode}</td>
                <td>${bank.branchName}</td>
                <td>${bank.accountType.title}</td>
                <td>${bank.accountBalance}</td>
                <td>
                    <a href="#" onclick="selectBank(${bank.id})"><i class="fas fa-eye show"></i></a>
                    <a href="#" onclick="showEditBank(${bank.id})"><i class="fa fa-edit edit"></i></a>
                    <a href="#" onclick="removeBank(${bank.id})"><i class="fa fa-remove remove"></i></a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script src="../../assets/js/bank.js"></script>
</body>
</html>
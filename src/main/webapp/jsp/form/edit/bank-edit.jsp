<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ویرایش حساب بانکی</title>
    <jsp:include page="../../css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../../../assets/css/form.css">
    <link rel="stylesheet" href="../../../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>

<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">

        <div class="formbold-form-title">
            <h2>ویرایش حساب بانکی</h2>
        </div>

        <!--start form-->
        <form id="bankEditForm" enctype="multipart/form-data">

            <input class="form-control" type="text" name="id" value="${sessionScope.bank.id}" hidden="hidden">

            <div class="formbold-input-group">
                    <label for="name" class="formbold-form-label"> نام بانک : </label>
                    <input type="text" name="name" id="name" value="${sessionScope.bank.name}" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="account_number" class="formbold-form-label">شماره حساب : </label>
                <input type="text" name="account_number" id="account_number" value="${sessionScope.bank.accountNumber}" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="branch_code" class="formbold-form-label"> کد شعبه : </label>
                <input type="number" name="branch_code" id="branch_code" value="${sessionScope.bank.branchCode}" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="branch_name" class="formbold-form-label"> نام شعبه : </label>
                <input type="text" name="branch_name" id="branch_name" value="${sessionScope.bank.branchName}" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="account_type" class="formbold-form-label"> نوع حساب : </label>
                <input type="number" name="account_type" id="account_type" value="${sessionScope.bank.accountType}" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="b_account_balance" class="formbold-form-label"> موجودی حساب : </label>
                <input type="number" name="b_account_balance" id="b_account_balance" class="formbold-form-input" value="${sessionScope.bank.accountBalance}"/>
            </div>

            <button id="submit" class="a-btn" onclick="editBank(event)">ویرایش</button>
        </form>
        <!--end form-->
    </div>
</div>

<script src="../../../assets/js/jquery-3.7.1.min.js"></script>
<script src="../../../assets/js/kamadatepicker.holidays.js"></script>
<script src="../../../assets/js/kamadatepicker.min.js"></script>
<script src="../../../assets/js/bank.js"></script>

</body>
</html>

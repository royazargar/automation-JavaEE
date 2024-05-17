<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>مشاهده حساب بانکی</title>
    <link rel="stylesheet" href="../../../assets/css/displayForm.css">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">

        <div class="formbold-form-title">
            <h2 class="">بانک</h2>
            <a class="formbold-btn primary" href="#" onclick="reference(${sessionScope.bank.id})">مشاهده</a>
            <a class="formbold-btn warning" href="#" onclick="showEditBank(${sessionScope.bank.id})">ویرایش</a>
            <a class="formbold-btn danger" href="#" onclick="removeBank(${sessionScope.bank.id})">حذف</a>
        </div>

        <!--start form-->
        <form id="bank-display" inert action="" method="POST">
            <div class="formbold-input-flex">
                <input class="form-control" type="text" name="id" value="${sessionScope.bank.id}" hidden="hidden">

                <div class="row mb-4">
                    <label for="b_name" class="formbold-form-label"> نام بانک : </label>
                    <input type="text" name="b_name" id="b_name" class="formbold-form-input" value="${sessionScope.bank.name}"/>
                </div>
                <div class="row mb-4">
                    <label for="b_account_number" class="formbold-form-label">شماره حساب : </label>
                    <input type="text" name="b_account_number" id="b_account_number" class="formbold-form-input" value="${sessionScope.bank.accountNumber}"/>
                </div>

            </div>

            <div class="formbold-input-flex">

                <div class="row mb-4">
                    <label for="b_branch_code" class="formbold-form-label"> کد شعبه : </label>
                    <input type="number" name="b_branch_code" id="b_branch_code" class="formbold-form-input" value="${sessionScope.bank.branchCode}"/>
                </div>
                <div class="row mb-4">
                    <label for="b_branch_name" class="formbold-form-label"> نام شعبه : </label>
                    <input type="text" name="b_branch_name" id="b_branch_name" class="formbold-form-input" value="${sessionScope.bank.branchName}"/>
                </div>

            </div>

            <div class="formbold-input-flex">

                <div class="row mb-4">
                    <label for="b_account_type" class="formbold-form-label"> نوع حساب : </label>
                    <input type="number" name="b_account_type" id="b_account_type" class="formbold-form-input" value="${sessionScope.bank.accountType}"/>
                </div>
                <div class="row mb-4">
                    <label for="b_account_balance" class="formbold-form-label"> موجودی حساب : </label>
                    <input type="number" name="b_account_balance" id="b_account_balance" class="formbold-form-input" value="${sessionScope.bank.accountBalance}"/>
                </div>

            </div>
        </form>
    </div>
</div>

<script src="../../../assets/js/bank.js"></script>
</body>
</html>

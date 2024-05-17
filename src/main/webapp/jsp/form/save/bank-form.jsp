<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>بانک</title>
    <jsp:include page="../../css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../../../assets/css/kamadatepicker.min.css">
    <link rel="stylesheet" href="../../../assets/css/form.css">
    <meta charset="UTF-8" lang="fa">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
</head>
<body>

<jsp:include page="../../../jsp/dashboard.jsp"></jsp:include>

<div class="formbold-main-wrapper">
    <div class="formbold-form-wrapper">

        <div class="formbold-form-title">
            <h2>ایجاد حساب جدید</h2>
        </div>

        <!--start form-->
        <form action="bank.do" method="post" enctype="multipart/form-data">

            <div class="formbold-input-group">
                <label for="name" class="formbold-form-label"> نام بانک : </label>
                <input type="text" name="name" id="name" placeholder="نام بانک را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="account_number" class="formbold-form-label">شماره حساب : </label>
                <input type="text" name="account_number" id="account_number" placeholder="شماره حساب را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="branch_code" class="formbold-form-label"> کد شعبه : </label>
                <input type="number" name="branch_code" id="branch_code" placeholder="کد شعبه را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="branch_name" class="formbold-form-label"> نام شعبه : </label>
                <input type="text" name="branch_name" id="branch_name" placeholder="نام شعبه را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="account_type" class="formbold-form-label"> نوع حساب : </label>
                <input type="number" name="account_type" id="account_type" placeholder="نوع حساب را وارد کنید" class="formbold-form-input"/>
            </div>

            <div class="formbold-input-group">
                <label for="account_balance" class="formbold-form-label"> موجودی حساب : </label>
                <input type="number" name="account_balance" id="account_balance" placeholder="موجودی حساب را وارد کنید" class="formbold-form-input"/>
            </div>

            <button class="a-btn">ثبت</button>

        </form>
        <!--end form-->
    </div>
</div>

<script src="../../../assets/js/jquery-3.7.1.min.js"></script>
<script src="../../../assets/js/kamadatepicker.holidays.js"></script>
<script src="../../../assets/js/kamadatepicker.min.js"></script>

</body>
</html>

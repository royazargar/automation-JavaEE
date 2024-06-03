<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check Payment</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/user.css">
    <link rel="stylesheet" href="../assets/css/bootstrap.min.css">
    <link rel="stylesheet" href="../assets/css/all.css">
    <link rel="stylesheet" href="../assets/css/style.css">
    <link rel="stylesheet" href="../assets/css/sidebar.css">
</head>
<body>
<div class="container-fluid">
    <div id="check-form">
        <form action="/checkPayment.do" method="post">
            <div class="row mb-4">
                <label class="col form-label" for="checkNumber">Check Number</label>
                <input id="checkNumber" class="col form-control" type="text" name="checkNumber">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="amount">Amount</label>
                <input id="amount" class="col form-control" type="text" name="amount">
            </div>

            <div class="row mb-4">
                <label for="checkDueDate" class="formbold-form-label"> تاریخ </label>
                <input type="text" name="checkDueDate" id="checkDueDate" placeholder="تاریخ  را وارد کنید" class="formbold-form-input"
                       required/>
            </div>

            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>

        </form>
    </div>

    <div id="check-table">
        <table class="table table-hover table-primary">
            <thead>
            <tr>
                <th>id</th>
                <th>checkNumber</th>
                <th>amount</th>
                <th>faCheckDueDate</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="checkPayment" items="${sessionScope.checkPaymentList}">
                <tr>
                    <td>${checkPayment.id}</td>
                    <td>${checkPayment.checkNumber}</td>
                    <td>${checkPayment.faCheckDueDate}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit(${checkPayment.id})"><i class="fa fa-edit"></i> Edit</button>
                        <button class="btn btn-danger" onclick="remove(${checkPayment.id})"><i class="fa fa-remove"></i>Remove</button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="../assets/js/jquery-3.7.1.min.js"></script>
<script src="../assets/js/kamadatepicker.holidays.js"></script>
<script src="../assets/js/kamadatepicker.min.js"></script>
<script>
    let myElement = document.querySelector('#date');
    kamaDatepicker(myElement);

    kamaDatepicker('date', {buttonsColor: "red", forceFarsiDigits: true});
</script>

</body>
</html>

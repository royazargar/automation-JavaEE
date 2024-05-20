<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bank</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/bank.css">
</head>
<body>
<div class="container-fluid">
    <div id="bank-form">
        <form action="bank.do" method="post">
            <div class="row mb-4">
                <label class="col form-label" for="name">Name</label>
                <input id="name" class="col form-control" type="text" name="name">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="s_accountNumber">Account Number</label>
                <input id="s_accountNumber" class="col form-control" type="text" name="accountNumber">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="s_branchCode">Branch Code</label>
                <input id="s_branchCode" class="col form-control" type="number" name="branchCode">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="s_branchName">Branch Name</label>
                <input id="s_branchName" class="col form-control" type="text" name="branchName">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="s_accountType">Account Type</label>
                <input id="s_accountType" class="col form-control" type="text" name="accountType">
            </div>
            <div class="row mb-4">
                <label class="col form-label" for="s_accountBalance">Account Balance</label>
                <input id="s_accountBalance" class="col form-control" type="number" name="accountBalance">
            </div>
            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>
        </form>
    </div>

    <div id="bank-table">
        <table class="table table-hover table-primary">
            <thead>
            <tr>
                <th>id</th>
                <th>name</th>
                <th>accountNumber</th>
                <th>branchCode</th>
                <th>branchName</th>
                <th>accountType</th>
                <th>accountBalance</th>
                <th>operation</th>
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
                    <td>${bank.accountType}</td>
                    <td>${bank.accountBalance}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit('${bank.id}')"><i class="fa fa-edit"></i>
                            Edit
                        </button>
                        <button class="btn btn-danger" onclick="removeBank('${bank.id}')"><i class="fa fa-remove"></i>Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <div>
        <form id="bankEditForm" enctype="multipart/form-data">
            <input  id="edit-id" name="id">
            <div>
                <label for="edit-name">Name:</label>
                <input type="text" id="edit-name" name="name">
            </div>
            <div>
                <label for="edit-accountNumber">Account Number:</label>
                <input type="text" id="edit-accountNumber" name="accountNumber">
            </div>
            <div>
                <label for="edit-branchCode">Branch Code:</label>
                <input type="number" id="edit-branchCode" name="branchCode">
            </div>
            <div>
                <label for="edit-branchName">Branch Name:</label>
                <input type="text" id="edit-branchName" name="branchName">
            </div>
            <div>
                <label for="edit-accountType">Account Type:</label>
                <input type="text" id="edit-accountType" name="accountType">
            </div>
            <div>
                <label for="edit-accountBalance">Account Balance:</label>
                <input type="number" id="edit-accountBalance" name="accountBalance">
            </div>
                <button id="submit" class="a-btn" onclick="editBank(event)">ویرایش</button>
        </form>
    </div>


</div>

<jsp:include page="js-import.jsp"></jsp:include>

<script src="../assets/js/jquery-3.7.1.min.js"></script>
<script src="../assets/js/kamadatepicker.holidays.js"></script>
<script src="../assets/js/kamadatepicker.min.js"></script>
<script src="../assets/js/bank.js"></script>
<%--<script src="../assets/js"></script>--%>

</body>
</html>

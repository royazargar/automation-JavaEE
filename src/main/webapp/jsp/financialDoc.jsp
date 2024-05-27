<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Financial Doc</title>
    <link rel="stylesheet" href="../assets/css/kamadatepicker.min.css">
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/financialDoc.css">
</head>
<body>
<div class="content">
    <div id="org-form">
        <form id="financialDoc_form" action="financialDoc.do" method="post">

            <div class="row mb-4">
                <label class="col form-label" for="docNumber">Doc Number</label>
                <input id="docNumber" class="col form-control" type="text" name="docNumber">
            </div>

            <div class="row mb-4">
                <label for="date" class="formbold-form-label"> تاریخ </label>
                <input type="text" name="date" id="date" placeholder="تاریخ  را وارد کنید"
                       class="formbold-form-input" required/>
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="description">description</label>
                <input id="description" class="col form-control" type="text" name="description">
            </div>

            <div class="row mb-4">
                <label for="fId">Financial Transaction</label>
                <select name="fId" id="fId">
                    <c:forEach items="${financialTransaction}" var="fId">
                        <option value="${fId.id}">${fId.trackingCode}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>
        </form>
    </div>

    <div id="financialDoc-table">
        <table class="table table-hover table-primary">
            <thead>
            <tr>
                <th>id</th>
                <th>docNumber</th>
                <th>faDate</th>
                <th>description</th>
                <th>financialTransaction</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="financialDoc" items="${sessionScope.financialDocList}">
                <tr>
                    <td>${financialDoc.id}</td>
                    <td>${financialDoc.docNumber}</td>
                    <td>${financialDoc.faDate}</td>
                    <td>${financialDoc.description}</td>
                    <td>${financialDoc.financialTransaction.trackingCode}</td>
                    <td>
                        <button class="btn btn-warning" onclick="editFinancialDoc(${financialDoc.id})"><i class="fa fa-edit"></i>
                            Edit
                        </button>
                        <button class="btn btn-danger" onclick="removeFinancialDoc(${financialDoc.id})"><i
                                class="fa fa-remove"></i>Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<script src="../assets/js/financialDoc.js"></script>
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

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div id="data-form">
    <form id="myForm">
        <h1>Edit Bank</h1>
        <input class="form-control" type="text" name="id" value="${sessionScope.financialDocEdit.id}" hidden="hidden">

        <input id="docNumber" class="col form-control" type="text" name="docNumber"
               value="${sessionScope.financialDocEdit.docNumber}">

        <input type="text" name="date" id="date" placeholder="تاریخ  را وارد کنید"
               class="formbold-form-input" value="${sessionScope.financialDocEdit.faDate}" required/>

        <input id="description" class="col form-control" type="text" name="description"
               value="${sessionScope.financialDocEdit.description}">

        <select name="fId" id="fId">
            <c:forEach items="${financialTransaction}" var="fId">
                <option value="${fId.id}">${fId.trackingCode}</option>
            </c:forEach>
        </select>

    </form>
    <button id="submit" class="btn btn-warning" onclick="edit()"><i class="fa fa-edit"></i> Edit</button>
</div>

<script src="../../../assets/js/financialDoc.js"></script>

</body>
</html>

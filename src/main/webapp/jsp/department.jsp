<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Department</title>
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/department.css">
</head>
<body>
<div class="container-fluid">
    <div id="cash-form">

        <form action="/department.do" method="post">

            <div class="row mb-4">
                <label class="col form-label" for="title">Title</label>
                <input id="title" class="col form-control" type="text" name="title">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="duty">Duty</label>
                <input id="duty" class="col form-control" type="text" name="duty">
            </div>

            <div class="row mb-4">
                <label class="col form-label" for="phoneNumber">PhoneNumber</label>
                <input id="phoneNumber" class="col form-control" type="text" name="phoneNumber">
            </div>

            <div class="row mb-4">
                <label for="organisation">Select user: </label>
                <select name="organisation" id="organisation">
                    <c:forEach items="${organisationList}" var="organisation">
                        <option>${organisation.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="row mb-4">
                <input type="submit" class="btn btn-primary" value="Save">
            </div>

        </form>
    </div>

    <div id="cash-table">
        <table class="table table-hover table-dark">
            <thead>
            <tr>
                <th>id</th>
                <th>title</th>
                <th>duty</th>
                <th>phoneNumber</th>
                <th>Organisation</th>
                <th>operation</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="department" items="${sessionScope.departmentList}">
                <tr>
                    <td>${department.id}</td>
                    <td>${department.title}</td>
                    <td>${department.duty}</td>
                    <td>${department.phoneNumber}</td>
                    <td>${department.organisation.name}</td>
                    <td>
                        <button class="btn btn-warning" onclick="edit(${department.id})"><i class="fa fa-edit"></i> Edit
                        </button>
                        <button class="btn btn-danger" onclick="remove(${department.id})"><i class="fa fa-remove"></i>Remove
                        </button>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
</body>
</html>

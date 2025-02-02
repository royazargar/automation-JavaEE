<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>stuffStorage</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <jsp:include page="css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../assets/css/stuffStorage.css">


    <style>
    .error {
        display: none;
        color: red;
    }
</style>
</head>
<body>
<div class="container-fluid">
    <div id="org-form">
    <form  action="stuffStorage.do" method="post" >
        <div class="row mb-4">
            <label for="name">Stuff Storage Name</label>
            <input  type="text" id="name" name="name" class="col form-control" placeholder="Stuff Storage Name">
        </div>
        <div class="row mb-4">
            <label for="count">Stuff Storage Count</label>
            <input type="text" id="count" name="count" class="col form-control" placeholder="Stuff Storage Count ">
        </div>
        <div>
            <input type="submit" class="btn btn-primary" value="save">
        </div>
    </form>
</div>

<div id="org-table">
    <table class="table table-hover table-primary">
        <thead>
        <tr>
            <th>id</th>
            <th>name</th>
            <th>count</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="stuffStorage" items="${sessionScope.StuffStorageList}">

            <tr>
                <td>${stuffStorage.id}</td>
                <td>${stuffStorage.name}</td>
                <td>${stuffStorage.count}</td>

                <td>
                    <button class="btn btn-warning" onclick="edit(${stuffStorage.id})"><i class="fa fa-edit"></i>
                        Edit
                    </button>
                    <button  class="btn btn-danger" onclick="remove(${stuffStorage.id})"><i class="fa fa-remove"></i>
                        Remove
                    </button>
                </td>
            </tr>

        </c:forEach>

        </tbody>
    </table>
  </div>
</div>

<jsp:include page="js-import.jsp"></jsp:include>
<script src="../assets/js/stuffStorage.js"></script>

</body>
</html>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../../jsp/css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../../../assets/css/profile.css">
    <link rel="stylesheet" href="../../../assets/css/profile-form.css">
    <meta charset="UTF-8" lang="fa">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<div class="container">
    <div class="main-body">

        <!-- Breadcrumb -->
        <nav aria-label="breadcrumb" class="main-breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index.html">خانه</a></li>
                <li class="breadcrumb-item"><a href="javascript:void(0)">حساب کاربری</a></li>
                <li class="breadcrumb-item active" aria-current="page">پروفایل</li>
            </ol>
        </nav>
        <!-- /Breadcrumb -->

        <div class="row">
            <div class="col-lg-4">
                <div class="card">
                    <div class="card-body">
                        <div class="d-flex flex-column align-items-center text-center">
                            <img src="https://bootdey.com/img/Content/avatar/avatar6.png" alt="" class="rounded-circle p-1 bg-primary" width="110">
                            <div class="mt-3">
                                <h4>نام کابری</h4>
                                <p class="text-secondary mb-1">نام فامیلی</p>
                                <p class="text-muted font-size-sm">نقش</p>
                                <button class="btn btn-outline-primary">Message</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-lg-8">
                <div class="card">
                    <div class="card-body">
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">نام</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" value="John Doe">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">نام خانوادگی</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" value="john@example.com">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">کد ملی</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" value="(239) 816-9029">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">جنسیت</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" value="(320) 380-4539">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">نام کاربری</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="text" class="form-control" value="Bay Area, San Francisco, CA">
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-sm-3">
                                <h6 class="mb-0">رمز عبور</h6>
                            </div>
                            <div class="col-sm-9 text-secondary">
                                <input type="password" class="form-control" value="Bay Area, San Francisco, CA">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-sm-3"></div>
                            <div class="col-sm-9 text-secondary">
                                <input type="button" class="btn btn-primary px-4" value="Save Changes">
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>

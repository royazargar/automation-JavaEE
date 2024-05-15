<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="../../jsp/css-import.jsp"></jsp:include>
    <link rel="stylesheet" href="../../assets/css/letterBox.css">
    <meta charset="UTF-8" lang="fa">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<!--nav bar-->
<jsp:include page="../../jsp/dashboard.jsp"></jsp:include>

<div class="container">
    <div class="row">
        <div class="col-md-12">
            <div class="card">
                <div class="card-body card-color text-white mailbox-widget pb-0">
                    <!--header-->
<%--                    <div>--%>
<%--                        <a class="text-light" href="home.do"><span class="fa fa-home text-light home"></span></a>--%>
<%--                    </div>--%>

                    <h4 class="text-white pb-3">صندوق نامه و ارجاع</h4>
                    <!--navbar start-->
                    <ul class="nav nav-tabs custom-tab border-bottom-0 mt-4" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="inbox-tab" data-bs-toggle="tab"  aria-controls="letter" href="#letter" role="tab" aria-selected="true">
                                <span class="d-block d-md-none"><i class="ti-email"></i></span>
                                <span class="d-none d-md-block"> نامه</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="sent-tab" data-bs-toggle="tab"  aria-controls="reference" href="#reference" role="tab" aria-selected="false">
                                <span class="d-block d-md-none"><i class="ti-export"></i></span>
                                <span class="d-none d-md-block">ارجاع ارسالی</span>
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="receive-tab" data-bs-toggle="tab"  aria-controls="reference" href="#referenceReceived" role="tab" aria-selected="false">
                                <span class="d-block d-md-none"><i class="ti-export"></i></span>
                                <span class="d-none d-md-block">ارجاع دریافتی</span>
                            </a>
                        </li>
                    </ul>
                </div>
                <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade active show" id="letter" aria-labelledby="inbox-tab" role="tabpanel">
                        <div>
                            <div class="row p-4 no-gutters align-items-center">
                                <div class="col-sm-12 col-md-6">
                                    <h4 class="font-light mb-0"><i class="ti-email mr-2"></i>نامه های شما</h4>
                                </div>
                                <!--add new and delete-->
                                <div class="col-sm-12 col-md-6">
                                    <ul class="list-inline dl mb-0 d-flex flex-row-reverse">
                                        <li class="list-inline-item text-danger">
                                            <a href="#">
                                                <button class="btn btn-circle btn-danger text-white" href="javascript:void(0)">
                                                    <i class="fa fa-trash"></i>
                                                </button>
                                                <span class="ml-2 font-normal text-dark">حذف</span>
                                            </a>
                                        </li>
                                        <li class="list-inline-item text-info mr-3">
                                            <a href="letter.do">
                                                <button class="btn btn-circle btn-success text-white" href="javascript:void(0)">
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                                <span class="ml-2 font-normal text-dark">افزودن</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <!--add new and delete end-->

                            <div class="table-responsive">
                                <table class="table email-table no-wrap table-hover v-middle mb-0 font-14">
                                    <tbody>
                                    <c:forEach var="letter" items="${sessionScope.letterListByUser}">
                                    <tr>

                                        <td class="pl-3">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="cst1" />
                                                <label class="custom-control-label" for="cst1">&nbsp;</label>
                                            </div>
                                        </td>

                                        <td><i class="fa fa-star text-warning"></i></td>
                                        <td>
                                            <span class="mb-0 text-muted">${letter.title}</span>
                                        </td>

                                        <td>
                                            <span class="text-dark">${letter.receiverName}</span>
                                        </td>

                                        <td class="text-muted">${letter.getFaDate()}</td>

                                        <td class="d-flex justify-content-end">
                                            <a href="#" class="text-dark p-2 bg-warning rounded" onclick="selectLetter(${letter.id})">مشاهده نامه </a>
                                            <a href="#" class="text-dark p-2 bg-warning rounded" onclick="showEditLetter(${letter.id})">ویرایش نامه </a>
                                        </td>

                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!--reference sent box-->
                    <div class="tab-pane fade" id="reference" aria-labelledby="sent-tab" role="tabpanel">

                        <div>
                            <div class="row p-4 no-gutters align-items-center">
                                <div class="col-sm-12 col-md-6">
                                    <h4 class="font-light mb-0"><i class="ti-email mr-2"></i>ارجاع های ارسالی شما</h4>
                                </div>
                                <div class="col-sm-12 col-md-6">
                                    <ul class="list-inline dl mb-0 d-flex flex-row-reverse">
                                        <li class="list-inline-item text-danger">
                                            <a href="#">
                                                <button class="btn btn-circle btn-danger text-white" href="javascript:void(0)">
                                                    <i class="fa fa-trash"></i>
                                                </button>
                                                <span class="ml-2 font-normal text-dark">حذف</span>
                                            </a>
                                        </li>
                                        <li class="list-inline-item text-info mr-3">
                                            <a href="reference.do">
                                                <button class="btn btn-circle btn-success text-white" href="javascript:void(0)">
                                                    <i class="fa fa-plus"></i>
                                                </button>
                                                <span class="ml-2 font-normal text-dark">افزودن</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="table-responsive">
                                <table class="table email-table no-wrap table-hover v-middle mb-0 font-14">
                                    <tbody>
                                    <c:forEach var="reference" items="${sessionScope.referenceListBySender}">
                                    <tr>
                                        <td class="pl-3">
                                            <div class="custom-control custom-checkbox">
                                                <input type="checkbox" class="custom-control-input" id="cst2" />
                                                <label class="custom-control-label" for="cst2">&nbsp;</label>
                                            </div>
                                        </td>

                                        <td><i class="fa fa-star text-warning"></i></td>
                                        <td>
                                            <span class="mb-0 text-muted">${reference.referenceReceiverId.username}</span>
                                        </td>

                                        <td>
                                            <span class="text-dark">${reference.paraph}</span>
                                        </td>

                                        <td class="text-muted">${reference.refDateAndTime}</td>

                                        <td class="d-flex justify-content-end">
                                            <a href="#" class="text-dark p-2 bg-warning rounded" onclick="selectReference(${reference.id})">مشاهده ارجاع </a>
                                            <a href="#" class="text-dark p-2 bg-warning rounded" onclick="showEditReference(${reference.id})">ویرایش ارجاع </a>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                    <!--reference received box-->
                    <div class="tab-pane fade" id="referenceReceived" aria-labelledby="receive-tab" role="tabpanel">

                        <div>
                            <div class="row p-4 no-gutters align-items-center">
                                <div class="col-sm-12 col-md-6">
                                    <h4 class="font-light mb-0"><i class="ti-email mr-2"></i>ارجاع های دریافتی شما</h4>
                                </div>
                                <div class="col-sm-12 col-md-6">
                                    <ul class="list-inline dl mb-0 d-flex flex-row-reverse">
                                        <li class="list-inline-item text-danger">
                                            <a href="#">
                                                <button class="btn btn-circle btn-danger text-white" href="javascript:void(0)">
                                                    <i class="fa fa-trash"></i>
                                                </button>
                                                <span class="ml-2 font-normal text-dark">حذف</span>
                                            </a>
                                        </li>
                                    </ul>
                                </div>
                            </div>

                            <div class="table-responsive">
                                <table class="table email-table no-wrap table-hover v-middle mb-0 font-14">
                                    <tbody>
                                    <c:forEach var="reference" items="${sessionScope.referenceListByReceiver}">
                                        <tr>
                                            <td class="pl-3">
                                                <div class="custom-control custom-checkbox">
                                                    <input type="checkbox" class="custom-control-input" id="cst3" />
                                                    <label class="custom-control-label" for="cst3">&nbsp;</label>
                                                </div>
                                            </td>

                                            <td><i class="fa fa-star text-warning"></i></td>
                                            <td>
                                                <span class="mb-0 text-muted">${reference.referenceSenderId.username}</span>
                                            </td>

                                            <td>
                                                <span class="text-dark">${reference.paraph}</span>
                                            </td>

                                            <td class="text-muted">${reference.refDateAndTime}</td>

                                            <td class="d-flex justify-content-end">
                                                <a href="#" class="text-dark p-2 bg-warning rounded" onclick="selectReference(${reference.id})">مشاهده ارجاع </a>


                                                <c:if test="${reference.seen == false}">
                                                    <a href="#" class="text-dark p-2 rounded" onclick="selectReference(${reference.id})">
                                                        <span class="new-msg">new</span>
                                                    </a>
                                                </c:if>

                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="../../jsp/js-import.jsp"></jsp:include>

<script src="../../assets/js/letter.js"></script>
<script src="../../assets/js/reference.js"></script>
<script type="text/javascript"></script>

<script>


</script>
</body>
</html>

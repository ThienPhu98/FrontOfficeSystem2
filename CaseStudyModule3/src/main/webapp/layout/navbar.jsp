<div class="navbar-custom">
    <ul class="list-unstyled topnav-menu float-right mb-0">

        <li class="dropdown notification-list">
            <a class="nav-link dropdown-toggle waves-effect waves-light" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                <i class="fe-bell noti-icon"></i>
                <span class="badge badge-danger rounded-circle noti-icon-badge">1</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right dropdown-lg">

                <div class="dropdown-item noti-title">
                    <h5 class="m-0">
                                    <span class="float-right">
                                        <a href="" class="text-dark">
                                            <small>Clear All</small>
                                        </a>
                                    </span>Notification
                    </h5>
                </div>

                <div class="slimscroll noti-scroll">

                    <a href="javascript:void(0);" class="dropdown-item notify-item">
                        <div class="notify-icon">
                            <img src="${pageContext.request.contextPath}/asset/simulor-admin-dashboard/assets/images/users/avatar-2.jpg" class="img-fluid rounded-circle" alt=""> </div>
                        <p class="notify-details">Manager 11111</p>
                        <p class="text-muted mb-0 user-msg">
                            <small>Room 201 has trouble with window! please check that situation</small>
                        </p>
                    </a>


                </div>

                <a href="javascript:void(0);" class="dropdown-item text-center text-primary notify-item notify-all">
                    View all
                    <i class="fi-arrow-right"></i>
                </a>

            </div>
        </li>

        <li class="dropdown notification-list">
            <a class="nav-link dropdown-toggle nav-user mr-0 waves-effect waves-light" data-toggle="dropdown" href="#" role="button" aria-haspopup="false" aria-expanded="false">
                <img src="${pageContext.request.contextPath}/asset/simulor-admin-dashboard/assets/images/users/avatar-4.jpg" alt="user-image" class="rounded-circle">
                <span class="pro-user-name ml-1">Nguyen Phan Thien Phu</span>
            </a>
            <div class="dropdown-menu dropdown-menu-right profile-dropdown ">
                <div class="dropdown-item noti-title">
                    <h4 class="m-0">
                        Welcome !
                    </h4>
                </div>

                <a href="${pageContext.request.contextPath}/system?action=staffHistory" class="dropdown-item notify-item">
                    <i class="bi bi-person-lines-fill"></i>
                    <span>Your History</span>
                </a>

                <a href="${pageContext.request.contextPath}/system?action=staffChangePassword" class="dropdown-item notify-item">
                    <i class="bi bi-credit-card-2-front"></i>
                    <span>Update Password</span>
                </a>

                <a href="${pageContext.request.contextPath}/system" class="dropdown-item notify-item">
                    <i class="bi bi-box-arrow-right"></i>
                    <span>Logout</span>
                </a>
            </div>
        </li>
    </ul>

    <ul class="list-unstyled menu-left mb-0">
        <li class="float-left" style="margin-top: 10px">
            <div class="alert alert-info" role="alert">
                <span>SPARTA-PHU 5-STAR HOTEL</span>
            </div>
        </li>
    </ul>
</div>

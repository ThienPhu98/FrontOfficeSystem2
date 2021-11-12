<div class="left-side-menu">
    <div class="slimscroll-menu">

        <a class="logo text-center mb-4">
            <span class="logo-lg">
                <img src="${pageContext.request.contextPath}/asset/picture/Logo.png" alt="" height="150" width="150">
            </span>
        </a>

        <div id="sidebar-menu">

            <ul class="meantime" id="side-menu">

                <li class="menu-title">Front-Office Service</li>

                <li>
                    <a href="${pageContext.request.contextPath}/system?action=booking">
                        <i class="bi bi-person-plus-fill"></i>
                        <span> Booking </span>
                    </a>
                </li>

                <li>
                    <a>
                        <i class="bi bi-person-check-fill"></i>
                        <span> Check-In </span>
                        <span class="menu-arrow"></span>
                    </a>
                    <ul class="nav-second-level" aria-expanded="false">
                        <li>
                            <a href="${pageContext.request.contextPath}/system?action=checkInWithReservation">Booking Guest</a>
                        </li>
                        <li>
                            <a href="${pageContext.request.contextPath}/system?action=checkInWithOutReservation">Walk-In Guest</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/system?action=checkOut">
                        <i class="bi bi-person-x-fill"></i>
                        <span> Check-Out </span>
                    </a>
                </li>

                <li class="menu-title">More Service</li>

                <li>
                    <a href="javascript: void(0);">
                        <i class="bi bi-house-fill"></i>
                        <span> Room Service </span>
                        <span class="menu-arrow"></span>
                    </a>
                    <ul class="nav-second-level" aria-expanded="false">
                        <li>
                            <a>Food And Beverage</a>
                        </li>
                        <li>
                            <a>House Keeping</a>
                        </li>
                        <li>
                            <a>Laundry</a>
                        </li>
                    </ul>
                </li>

                <li>
                    <a>
                        <i class="bi bi-chat-quote-fill"></i>
                        <span> Guest Request </span>
                        <span class="menu-arrow"></span>
                    </a>
                    <ul class="nav-second-level" aria-expanded="false">
                        <li>
                            <a>Change Room</a>
                        </li>
                        <li>
                            <a>Booking transportation</a>
                        </li>
                        <li>
                            <a>Baby Sitter Service</a>
                        </li>
                        <li>
                            <a>Change Money</a>
                        </li>
                    </ul>
                </li>
            </ul>

        </div>
        <div class="clearfix"></div>
    </div>
</div>

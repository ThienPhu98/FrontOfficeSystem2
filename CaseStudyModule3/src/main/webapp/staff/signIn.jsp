<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.88.1">
    <title>Signin Template · Bootstrap v5.1</title>
    <link rel="canonical" href="https://getbootstrap.com/docs/5.1/examples/sign-in/">
    <link href="${pageContext.request.contextPath}/asset/bootstrap-5.1.3-examples/assets/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .bd-placeholder-img {
            font-size: 1.125rem;
            text-anchor: middle;
            -webkit-user-select: none;
            -moz-user-select: none;
            user-select: none;
        }

        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }
    </style>
    <link href="${pageContext.request.contextPath}/asset/bootstrap-5.1.3-examples/sign-in/signin.css" rel="stylesheet">
</head>
<body class="text-center">

<main class="form-signin">
        <form method="post">
            <img class="mb-4" src="${pageContext.request.contextPath}/asset/picture/Logo.png" alt="" width="240" height="240">
            <h1 class="h3 mb-3 fw-normal">Please sign in</h1>

            <div class="form-floating">
                <input type="text" class="form-control" id="floatingInput" placeholder="12345" name="userName">
                <label for="floatingInput">Staff-ID</label>
            </div>
            <div class="form-floating">
                <input type="password" class="form-control" id="floatingPassword" placeholder="Password" name = "password">
                <label for="floatingPassword">Password</label>
            </div>

            <div class="checkbox mb-3">
                <label>
                    <input type="checkbox" value="remember-me"> Remember me
                </label>
            </div>
            <button class="w-100 btn btn-lg btn-primary" type="submit">Sign in</button>
        </form>
    <c:if test='${requestScope["message"] == "false" && requestScope["message"] != null}'>
        <div class="alert alert-danger" role="alert" style="margin-top: 30px">
            <span>Sign In Fail! Staff-Id or Password is wrong! please try again!</span>
        </div>
    </c:if>
</main>
</body>
</html>
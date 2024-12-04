<%--
  Created by IntelliJ IDEA.
  User: fouad
  Date: 03/12/2024
  Time: 15:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Signin</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!--===============================================================================================-->
    <link rel="icon" type="image/png" href="AuthTemplate/images/icons/favicon.ico"/>
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="AuthTemplate/vendor/bootstrap/css/bootstrap.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="AuthTemplate/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="AuthTemplate/vendor/animate/animate.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="AuthTemplate/vendor/css-hamburgers/hamburgers.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="AuthTemplate/vendor/select2/select2.min.css">
    <!--===============================================================================================-->
    <link rel="stylesheet" type="text/css" href="AuthTemplate/css/util.css">
    <link rel="stylesheet" type="text/css" href="AuthTemplate/css/main.css">
    <!--===============================================================================================-->
</head>
<body>
<div class="limiter">
    <div class="container-login100">
        <div class="wrap-login100">
            <div class="login100-pic js-tilt" data-tilt>
                <img src="AuthTemplate/images/img-01.png" alt="IMG">
            </div>
                <form action="auth" method="post">
                    <input type="hidden" name="action" value="register">
                    <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                        <label>
                            <input class="input100" type="text" name="nom" id="nom" placeholder="Name">
                        </label>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate = "Valid email is required: ex@abc.xyz">
                        <label>
                            <input class="input100" type="text" id="email" name="email" placeholder="Email" required>
                        </label>
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
                    </div>

                    <div class="wrap-input100 validate-input" data-validate = "Password is required">
                        <input class="input100" type="password" name="password" placeholder="Password" required>
                        <span class="focus-input100"></span>
                        <span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
                    </div>

                    <div class="container-login100-form-btn">
                        <button class="login100-form-btn">
                            Sigin
                        </button>
                    </div>

                    <div class="text-center p-t-136">
                        <a class="txt2" href="login.jsp">
                            Login
                            <i class="fa fa-long-arrow-right m-l-5" aria-hidden="true"></i>
                        </a>
                    </div>
                </form>
            <!-- Affichage du message d'erreur -->
            <c:if test="${not empty errorMessage}">
                <p style="color: red;">${errorMessage}</p>
            </c:if>
        </div>
    </div>
</div>

<!--===============================================================================================-->
<script src="AuthTemplate/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
<script src="AuthTemplate/vendor/bootstrap/js/popper.js"></script>
<script src="AuthTemplate/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
<script src="AuthTemplate/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
<script src="AuthTemplate/vendor/tilt/tilt.jquery.min.js"></script>
<script >
    $('.js-tilt').tilt({
        scale: 1.1
    })
</script>
<!--===============================================================================================-->
<script src="AuthTemplate/js/main.js"></script>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Main page</title>
<jsp:include page="head.jsp" />
<head>
<body>
    <jsp:include page="common.jsp" />

    <div class="jumbotron">
        <div class="container">
            <h1>Hello, Naumen!</h1>
            <p>Please register</p>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <form id="signup" novalidate="novalidate" method="post" action="/complete-register/">
                <div class="form-group">
                    <label for="email">Email address</label> 
                    <input type="email" class="form-control" name="email" id="email" placeholder="Введите email">
                </div>
                <div class="form-group">
                    <label for="text">ФИО</label> 
                    <input type="text" class="form-control" name="fio" id="fio" placeholder="Введите ФИО">
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label> 
                    <input type="password" class="form-control" name="password" id="password" placeholder="Введите пароль">
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Повторите пароль</label> 
                    <input type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="Повторите ввод пароля">
                </div>
                <div class="form-group">
                    <label class="form-label"></label>
                    <div class="controls">
                        <button type="submit" class="btn btn-success">Зарегистрироваться</button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <script type="text/javascript">
		$(document).ready(function() {

			$("#signup").validate({
				rules : {
					email : "required",
					fio : "required",
					email : {
						required : true,
						email : true
					},
					password : {
						required : true,
						minlength : 4
					},
					confirmPassword : {
						required : true,
						equalTo : "#password"
					},
				},

				errorClass : "help-inline"
			});
		});
	</script>
</body>
</html>

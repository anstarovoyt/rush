<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
<title>Main page</title>
<jsp:include page="head.jsp" />
<head>
<body>
    <jsp:include page="common.jsp" />

    <div class="jumbotron">
        <div class="container">
            <div class="row" style="text-align: center;">
                <h1>Привет, Naumen!</h1>
                <p>Для начала зарегистрируйтесь</p>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row col-md-8 col-md-offset-2">
            <form:form id="signup" novalidate="novalidate" method="post" action="/complete-register/" modelAttribute="registrationForm">
                <form:errors path="*" cssClass="errorUserMessage" />
                <div class="form-group">
                    <label for="email">Email</label> 
                    <form:input path="email" type="email" class="form-control" name="email" id="email" placeholder="Введите email"/>
                </div>
                <div class="form-group">
                    <label for="text">Название команды</label> 
                    <form:input path="fio" type="text" class="form-control" name="fio" id="fio" placeholder="Введите ФИО"/>
                </div>
                <div class="form-group">
                    <label for="password">Пароль</label> 
                    <form:input path="password" type="password" class="form-control" name="password" id="password" placeholder="Введите пароль"/>
                </div>
                <div class="form-group">
                    <label for="confirmPassword">Повторите пароль</label> 
                    <form:input path="confirmPassword" type="password" class="form-control" name="confirmPassword" id="confirmPassword" placeholder="Повторите ввод пароля"/>
                </div>
                <div class="form-group">
                    <label class="form-label"></label>
                    <div class="controls">
                        <button type="submit" class="btn btn-success btn-orange">Зарегистрироваться</button>
                    </div>
                </div>
            </form:form>
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

				errorClass : "has-error-label",
                highlight: function(element) {
                    $(element).closest('.form-group').addClass('has-error');
                },
                unhighlight: function(element) {
                    $(element).closest('.form-group').removeClass('has-error');
                }
			});
		});
	</script>
</body>
</html>

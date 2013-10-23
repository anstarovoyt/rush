<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<html>
<head>
<title>Main page</title>
<jsp:include page="head.jsp" />
<style type="text/css">
/* Move down content because we have a fixed navbar that is 50px tall */
body {
	padding-top: 50px;
	padding-bottom: 20px;
}
</style>
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
<!--             <div class="col-lg-4"> -->
<!--                 <h2>Doom</h2> -->
<!--                 <p>Что такое дум</p> -->
<!--                 <p> -->
<!--                     <a class="btn btn-default" href="/game?gid=dm">Go game &raquo;</a> -->
<!--                 </p> -->
<!--             </div> -->
<!--             <div class="col-lg-4"> -->
<!--                 <h2>Tic tac toe</h2> -->
<!--                 <p>Крестики, да нолики</p> -->
<!--                 <p> -->
<!--                     <a class="btn btn-default" href="/game?gid=xo">Doom &raquo;</a> -->
<!--                 </p> -->
<!--             </div> -->
<!--             <div class="col-lg-4"> -->
<!--                 <h2>One more game</h2> -->
<!--                 <p>Еще одна игра</p> -->
<!--                 <p> -->
<!--                     <a class="btn btn-default" href="/game?">Go game &raquo;</a> -->
<!--                 </p> -->
<!--             </div> -->
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

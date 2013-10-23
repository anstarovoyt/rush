<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

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
            <p>This is a games</p>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <h2>Doom</h2>
                <p>Что такое дум</p>
                <p>
                    <a class="btn btn-default" href="/doom/">Go game &raquo;</a>
                </p>
            </div>
            <div class="col-lg-4">
                <h2>Tic tac toe</h2>
                <p>Крестики, да нолики</p>
                <p>
                    <a class="btn btn-default" href="/xo/">Go game &raquo;</a>
                </p>
            </div>
            <div class="col-lg-4">
                <h2>One more game</h2>
                <p>Еще одна игра</p>
                <p>
                    <a class="btn btn-default" href="/onemore">Go game &raquo;</a>
                </p>
            </div>
        </div>
    </div>
</body>
</html>

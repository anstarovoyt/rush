<%@page import="ru.naumen.core.auth.Authenticator"%>
<%@page import="ru.naumen.core.SpringContext"%>
<%@page import="ru.naumen.model.User"%>
<%@page import="ru.naumen.core.utils.UrlUtils"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%!
    User getCurrentUser() {
        return SpringContext.<Authenticator>getBean("authenticator").getCurrentUser();
    }
%>

<html>
<head>
<title>Game page</title>
<jsp:include page="head.jsp" />
<head>
<body>
    <jsp:include page="common.jsp" />

    <div class="jumbotron">
        <div class="container">
            <h1>Hello, Naumen!</h1>
            <p>Выбери игру</p>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="col-lg-4">
                <h2>Doom</h2>
                <p>Что такое дум</p>
                <p>
                    <a class="btn btn-default" href="/game?gid=dm&<%= UrlUtils.createAKParam(getCurrentUser())%>">Go game &raquo;</a>
                </p>
            </div>
            <div class="col-lg-4">
                <h2>Tic tac toe</h2>
                <p>Крестики, да нолики</p>
                <p>
                    <a class="btn btn-default" href="/game?gid=xo&<%= UrlUtils.createAKParam(getCurrentUser()) %>">Doom &raquo;</a>
                </p>
            </div>
            <div class="col-lg-4">
                <h2>One more game</h2>
                <p>Еще одна игра</p>
                <p>
                    <a class="btn btn-default" href="/game?gid=go&<%= UrlUtils.createAKParam(getCurrentUser())%>">Go game &raquo;</a>
                </p>
            </div>
        </div>
    </div>
</body>
</html>

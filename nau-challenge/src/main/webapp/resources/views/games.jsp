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
            <h1>Выбери игру!</h1>
        </div>
    </div>

    <div class="container">
        <div class="row" >
            <div class="col-lg-05"></div>
            <div class="game_card col-lg-3">
                <h2 class="game_title">Doom</h2>
                <p>Что такое дум</p>
                <p class="game_button">
                    <a class="btn btn-orange btn-primary" href="/game?gid=dm&<%= UrlUtils.createAKParam(getCurrentUser())%>">Играть! &raquo;</a>
                </p>
            </div>
            <div class="col-lg-1"></div>
            <div class="game_card col-lg-3">
                <h2 class="game_title">Tic tac toe</h2>
                <p>Крестики, да нолики</p>
                <p class="game_button">
                    <a class="btn btn-orange btn-primary" href="/game?gid=xo&<%= UrlUtils.createAKParam(getCurrentUser()) %>">Играть! &raquo;</a>
                </p>
            </div>
            <div class="col-lg-1"></div>
            <div class="game_card col-lg-3">
                <h2 class="game_title">Rock, paper, scissors</h2>
                <p>Камень, ножницы, бумага</p>
                <p class="game_button">
                    <a class="btn btn-orange btn-primary" href="/game?gid=rps&<%= UrlUtils.createAKParam(getCurrentUser())%>">Играть! &raquo;</a>
                </p>
            </div>
            <div class="col-lg-05"></div>
        </div>
    </div>
</body>
</html>

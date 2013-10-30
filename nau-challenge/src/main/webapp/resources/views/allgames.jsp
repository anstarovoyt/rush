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
    
        <div class="col-lg-05"></div>
        <div class="container">
            <div class="row" >
             <c:forEach var="game" items="${gameIds}">
                <div class="game_card col-lg-3">
                    <h2 class="game_title">${game.shortName}</h2>
                    <p>${game.shortDescription}</p>
                    <p class="game_button">
                        <a class="btn btn-orange btn-primary" href="/game?gid=${game.id}&<%= UrlUtils.createAKParam(getCurrentUser())%>">Играть! &raquo;</a>
                    </p>
                </div>
                <div class="col-lg-1"></div>
            </c:forEach>
            </div>
        </div>
        <div class="col-lg-05"></div>
</body>
</html>

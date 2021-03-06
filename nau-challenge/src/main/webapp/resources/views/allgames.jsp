<%@page import="java.util.List"%>
<%@page import="ru.naumen.core.game.GameSeries"%>
<%@page import="java.util.Collection"%>
<%@page import="ru.naumen.core.game.GameSeriesState"%>
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
             <%--
                    Если размещаем bootstrap сеткой, то <c:forEach нам не подходит, пришлось запилить цикл в скриптлете,
                    на каждые 3 элемента создаем div-строку
             --%>
             
             <% 
                 List<GameSeries> games = (List<GameSeries>) request.getAttribute("gameIds");
                 for(int i = 0; i < games.size(); i++) {
                     GameSeries series = games.get(i);
                     pageContext.setAttribute("game", series.getGame());                     
             %>
                   
                <% if(i % 3 == 0) { %> <%-- Каждые 3 элемента новая строка --%>
                    <div class="row game_row">
                        <div class="col-lg-05"></div>
                <% } %>
                <% if (series.getState() ==  GameSeriesState.OPEN) {%>
                        <%-- Сама карточка игры --%>
                        <div class="game_card col-lg-3">
                            <h2 class="game_title">${game.shortName}</h2>
                            <p>${game.shortDescription}</p>
                            <p class="game_button">
                                <a class="btn btn-orange btn-primary" href="/game?gid=${game.id}&<%= UrlUtils.createAKParam(getCurrentUser())%>">Играть! &raquo;</a>
                            </p>
                        </div>
                 <% } else if (series.getState() ==  GameSeriesState.CLOSED){ %>
                        <%-- Карточка disabled игры --%>
                        <div class="game_card col-lg-3 game-disabled">
                            <h2 class="game_title">${game.shortName}</h2>
                            <p>${game.shortDescription}</p>
                        </div>
                 <% } else { %>
                 		<%-- Карточка пройденной игры --%>
                        <div class="game_card col-lg-3">
                            <div class="game-mark"><img src="/resources/img/mark.png" width="50px"></div>
                            <h2 class="game_title">${game.shortName}</h2>
                            <p>${game.shortDescription}</p>
                        </div>
                 <% } %>
                
                <% if(i % 3 != 2)  {%>  <%-- После последнего элемента отступ не нужен, иначе криво --%>
                    <div class="col-lg-1"></div>
                <% } %>
                 
                <% if(i % 3 == 2) { %> <%-- Каждые 3 элемента новая строка --%>
                        <div class="col-lg-05"></div>
                    </div>
                <% } %>                
            <% } %>
        </div>
</body>
</html>

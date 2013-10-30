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

    <!--
                    КТО-НИБУДЬ!!!!!!!
          Сделайте нормальную вёрстку, табличкой со строками и столбцами.
          А не этот дурацкий один столбик
    -->
    <c:forEach var="game" items="${gameIds}">
        <div class="container">
            <div class="row">
                <div class="col-lg-4">
                    <h2>${game.shortName}</h2>
                    <p>${game.shortDescription}</p>
                    <p>
                        <a class="btn btn-default" href="/game?gid=${game.id}&<%= UrlUtils.createAKParam(getCurrentUser())%>">Go game &raquo;</a>
                    </p>
                </div>
            </div>
        </div>
    </c:forEach>
</body>
</html>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.naumen.core.info.Params" %>
<%@ page import="ru.naumen.core.auth.Authenticator"%>
<%@ page import="ru.naumen.core.SpringContext"%>
<%@ page import="ru.naumen.model.User"%>
<%@ page import="ru.naumen.core.utils.UrlUtils"%>

<%!
    User getCurrentUser() {
        return SpringContext.<Authenticator>getBean("authenticator").getCurrentUser();
    }
%>

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
            <h1>Победа!</h1>
        </div>
    </div>

    <div class="container">
        <span>Поздравляем! Вы одержали нужное число побед в этой игре (${wins})
        <br>
                <% if(getCurrentUser() != null) { %>
                   <h1> <a href="/games/?<%= UrlUtils.createAKParam(getCurrentUser()) %>">Вернуться к играм</a> </h1>
                <% } %>
        </span>
    </div>
</body>
</html>

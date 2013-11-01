<%@page import="ru.naumen.core.utils.DateUtils"%>
<%@page import="ru.naumen.core.rating.RatingRow"%>
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
            <h1>Секретный рейтинг</h1>
        </div>
    </div>
    
    <div class="container">
        <div class="row">
            <table class="table table-striped" style="width: 100%;">
                <tr>
                    <th>Команда</th>
                    <th>Рейтинг</th>
                    <th>Дата последнего решения</th>
                </tr>
                <%
                    @SuppressWarnings("unchecked")
                    List<RatingRow> rows = (List<RatingRow>)request.getAttribute("rows");
                    for(int i = 0; i < rows.size(); i++ ) {
                        RatingRow row = rows.get(i);
                %>
                      <tr>
                        <td><%= row.getCommandName() %></td>
                        <td><%= row.getScore() %></td>
                        <td><%= DateUtils.formatAsDate(row.getLastSolved()) %></td>
                      </tr>
                <%  }  %>
            </table>
        </div>
    </div>
</body>
</html>

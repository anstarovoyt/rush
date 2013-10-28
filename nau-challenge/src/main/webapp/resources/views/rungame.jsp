<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.naumen.core.info.Params" %>
<html>
    <head>
        <title>Main page</title>
        <jsp:include page="head.jsp" />
    <head>
    <style type="text/css">
    p {
        font-size: 20px;
    }
    </style>
<body>
    <jsp:include page="common.jsp" />

    <div class="jumbotron">
        <div class="container">
            <h3>Правила</h3>
            <p>${description} </p>
        </div>
    </div>
    

    <div class="container">
        <form class="form-horizontal" method="post">
            <p>Количество побед: ${wins} из ${maxwins}</p>
            <p style="margin-top: 20px">Состояние игры: ${state}</p>
            <div class="row" style="margin-top: 20px">
                <div class="control-group col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Вывод компьютера</h3>
                        </div>
                        <div class="panel-body" style="font-size: 16px;">
                            ${result}
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="control-group col-md-8">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">Ваш ход</h3>
                        </div>
                        <div class="panel-body">
                            <textarea class="form-control" style="width: 100%;" name=<%= "\"" + Params.ANSWER_ID + "\"" %> rows="5" cols="100"></textarea>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 0px;">
                <div class="form-actions col-md-8">
                    <button type="submit" style="float: right;" class="btn btn-primary">Отправить</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>

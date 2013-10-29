<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.naumen.core.info.Params" %>
<html>
    <head>
        <title>Main page</title>
        <jsp:include page="head.jsp" />
        <style type="text/css">
        p {
            font-size: 20px;
        }
        hr { 
            display: block; 
            height: 1px;
            border: 0; 
            border-top: 1px solid #d66000;
            margin: 1em 0; 
            padding: 0; 
        }
        </style>
    <head>
<body>
    <jsp:include page="common.jsp" />

    <div class="jumbotron">
        <div class="container">
            <div class="col-md-8 col-md-offset-2">
                <hr>
                <h2>Правила игры</h2>
                <hr>
                <p>${description} </p>
            </div>
        </div>
    </div>
    

    <div class="container">
        <form class="form-horizontal" method="post">
            <div class="col-md-8 col-md-offset-2">
                <p >Игра пройдена</p>
                <p style="margin-top: 20px">Количество побед: ${wins} из ${maxwins}</p>
                <div class="row" style="margin-top: 20px">
                    <div class="control-group">
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
                <p style="margin-top: 20px">Состояние игры: ${state}</p>
                <p style="margin-top: 20px">Текущее состояние:</p>
                <div class="row">
                    <div class="control-group">
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
                <div class="form-actions">
                    <button type="submit" style="float: right;" class="btn btn-orange btn-primary">Отправить</button>
                </div>
            </div>
        </form>
    </div>
</body>
</html>

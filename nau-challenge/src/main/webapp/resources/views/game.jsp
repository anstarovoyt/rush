<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="ru.naumen.core.info.Params" %>
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
            <p>${description} </p>
        </div>
    </div>

    <div class="container">
    <form class="form-horizontal">
              <div class="control-group">

              <textarea class="input-xlarge" id=<%= "\"" + Params.ANSWER_ID + "\"" %> rows="5" cols="100"></textarea>
              </div>
              <br>
              <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Send answer</button>
              </div>
        </form>
    </div>
</body>
</html>

<%@page import="ru.naumen.core.auth.Authenticator"%>
<%@page import="ru.naumen.core.SpringContext"%>
<%@page import="ru.naumen.model.User"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%! 
    User getCurrentUser() {
        return SpringContext.<Authenticator>getBean("authenticator").getCurrentUser();
    }
%>

<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="icon-bar"></span> 
                <span class="icon-bar"></span> 
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Nau challenge</a>
        </div>
        <div class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <% if(getCurrentUser() != null) { %>
                    <li><a href="/games/?ak=<%= getCurrentUser().getAccessKey() %>">Games</a></li>
                <% } %>
            </ul>
            <% if(getCurrentUser() == null) { %>
                <form id="login" class="navbar-form navbar-right" novalidate="novalidate" method="post" action="/login/">
                    <div class="form-group">
                        <input name="email" type="text" placeholder="Email" class="form-control">
                    </div>
                    <div class="form-group">
                        <input name="password" type="password" placeholder="Пароль" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-success">Войти</button>
                </form>
                <script>
                $(document).ready(function() {
                    $("#login").validate({
                        rules : {
                            email : "required",
                            password : {
                                required : true,
                                minlength : 4
                            }
                        },
                        errorClass : "help-inline"
                    });
                });
                </script>
            <% } else { %>
                <ul class="nav navbar-nav" style="float: right;">
                <li class="dropdown" style="float: right;">
                    <a href="#" id="drop2" role="button" class="dropdown-toggle" data-toggle="dropdown"><%= getCurrentUser().getEmail() %><b class="caret"></b></a>
                    <ul class="dropdown-menu" role="menu" aria-labelledby="drop2" style="text-align: center;">
                        <button id="logoutButton" onclick="logout();" class="btn btn-primary loginform">Logout</button>
                    </ul>
                </li>
                </ul>
                
                <script>
                $('.dropdown-menu').find('form').click(function (e) {
                    e.stopPropagation();
                });
                var logout = function() {
                	$.post("/logout/", function(data) {
                		window.location = "/"
                	})
                }
                </script>
            <% } %>
        </div>
    </div>
</div>

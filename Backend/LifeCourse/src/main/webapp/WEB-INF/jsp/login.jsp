<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8" />
	<title>Life Course</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="/resources/style.css" />
</head>

<body>
	<!--
		<sec:authorize access="isAuthenticated()">
			<% response.sendRedirect("/"); %>
		</sec:authorize>
		<div>
			<form method="POST" action="/login">
				<h2>Вход в систему</h2>
				<div>
					<input name="username" type="text" placeholder="Username"
								autofocus="true"/>
					<input name="password" type="password" placeholder="Password"/>
					<button type="submit">Log In</button>
					<h4><a href="/registration">Зарегистрироваться</a></h4>
				</div>
			</form>
		</div>
	-->

	<sec:authorize access="isAuthenticated()">
		<% response.sendRedirect("/"); %>
	</sec:authorize>

	<header>
		<img src="/resources/logo.svg" />
		<div class="header-links">
			<a href="/">На главную</a>
		</div>
	</header>

	<main>
		<form method="POST" action="/login" class="article-panel login-box">
			<h1>Вход / Регистрация</h1>

			<label>
				Логин
				<input type="text"  name="username" class="block" />
			</label>

			<label>
				Пароль
				<input type="password" name="password" class="block" />
			</label>

			<button type="submit">Войти</button>

			<a href="/registration">Еще нет учетной записи?</a>
		</div>
	</main>
</body>

</html>
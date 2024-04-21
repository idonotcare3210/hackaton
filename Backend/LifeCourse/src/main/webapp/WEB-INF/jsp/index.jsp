<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<title>Life Course</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="/resources/style.css" />
</head>

<body>
	<!--
		<h3>${pageContext.request.userPrincipal.name}</h3>
		<sec:authorize access="!isAuthenticated()">
			<h4><a href="/login">Войти</a></h4>
			<h4><a href="/registration">Зарегистрироваться</a></h4>
		</sec:authorize>
		<sec:authorize access="isAuthenticated()">
			<h4><a href="/logout">Выйти</a></h4>
		</sec:authorize>
		<h4><a href="/news">Новости (только пользователь)</a></h4>
		<h4><a href="/admin">Пользователи (только админ)</a></h4>
	-->

	<header>
		<img src="/resources/logo.svg" />
		<div class="header-links">
			<a href="/faculty">Предприятиям</a>
			<div class="separator"></div>
			<a href="/student">Студентам</a>
			<div class="separator"></div>
			<a href="/university">ВУЗам</a>
		</div>
	</header>

	<main>
		<p class="the-main-paragraph">
			Life Course - Lorem, ipsum dolor sit amet consectetur adipisicing elit. Veritatis ullam maiores perferendis est beatae sint illo soluta exercitationem dolores ratione, unde et natus magni, corrupti amet assumenda commodi repellendus eum.
		</p>

		<div class="the-main-buttons">
			<sec:authorize access="!isAuthenticated()">
				<a class="pretty-button" href="/login">Вход / Регистрация</a>
			</sec:authorize>
			<sec:authorize access="isAuthenticated()">
				<a class="pretty-button" href="/logout">Выйти</a>
			</sec:authorize>
			<a class="pretty-button" href="/news">Лента новостей</a>
			<a class="pretty-button" href="">Оренбург</a>
		</div>
	</main>

	<footer></footer>
</body>

</html>
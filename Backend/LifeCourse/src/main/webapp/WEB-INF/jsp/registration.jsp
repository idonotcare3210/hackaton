<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<header>
		<img src="/resources/logo.svg" />
		<div class="header-links">
			<a href="/">На главную</a>
		</div>
	</header>

	<main>
		<%--@elvariable id="userForm" type=""--%>
		<form:form method="POST" modelAttribute="userForm" class="article-panel login-box">
			<h1>Регистрация</h1>

			<label>
				Имя:
				<form:input type="text" path="firstname" class="block" required=""></form:input>
			</label>
			<label>
				Отчество:
				<form:input type="text" path="middlename" class="block"></form:input>
			</label>
			<label>
				Фамилия:
				<form:input type="text" path="lastname" class="block" required=""></form:input>
			</label>
			
			<label>
				Имя пользователя
				<form:input type="text" path="username" class="block" required=""></form:input>
				<form:errors path="username"></form:errors>
				${usernameError}
			</label>

			<label>
				Электронная почта
				<form:input type="email" path="email" class="block" required=""></form:input>
				<form:errors path="email"></form:errors>
				${emailError}
				<form:errors path="email"></form:errors>
				${emailValidError}
			</label>

			<label>
				Пароль
				<form:input type="password" path="password" class="block" required=""></form:input>
			</label>

			<label>
				Подтвердите пароль
				<form:input type="password" path="passwordConfirm" class="block" required=""></form:input>
				<form:errors path="password"></form:errors>
				${passwordError}
			</label>

			<label>
				<input type="checkbox" required="" />
				Нажимая на галочку вы принимаете <a href="/resources/Согласие_на_обработку_и_распространение_персональных_данных.docx">согласие на обработку и распространение персональных данных</a>
			</label>

			<button type="submit">Зарегистрироваться</button>
		</form:form>
	</main>
</body>

</html>
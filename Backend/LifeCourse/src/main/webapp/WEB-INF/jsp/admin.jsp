<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8" />
	<title>Life Course</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<link rel="stylesheet" href="/resources/style.css" />
	<script defer src="/resources/scripts/admin.js"></script>
</head>

<body>
	<header>
		<img src="/resources/logo.svg" />
		<div class="header-links">
			<a href="/">На главную</a>
		</div>
	</header>
	
	<nav>
		<div>
			<a href="">Лента</a>
			<a href="">Профиль</a>
			<a href="">Чаты</a>
			<a href="">Вакансии</a>
			<a href="">Вакансии</a>
		</div>
		<div>
			<a href="">Настройка</a>
			<a href="">Управление пользователями</a>
		</div>
	</nav>

	<main>
		<h1>Управление пользователями</h1>
		<table id="usersTable">
			<thead>
				<th>Номер</th>
				<th>Имя пользователя</th>
				<th>Почта</th>
				<th>Роли</th>
				<th></th>
			</thead>
			<tbody>
				<c:forEach items="${allUsers}" var="user">
					<tr class="row" data-id="${user.id}" data-username="${user.username}">
						<td>${user.id}</td>
						<td>${user.username}</td>
						<td>${user.email}</td>
						<td>
							<c:forEach items="${user.roles}" var="role">
								<data class="role" data-id="${role.id}" data-name="${role.name}">${role.name}</data>;
							</c:forEach>
						</td>
						<td>
							<button class="buttonAddRole block">Добавить роль</button>
							<button class="buttonRemoveRole block">Удалить роль</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<data hidden id="mainData" data-context-path="${pageContext.request.contextPath}">
			<data hidden id="allRolesData">
				<c:forEach items="${allRoles}" var="role">
					<data data-id="${role.id}" data-name="${role.name}">${role.readableName}</data>
				</c:forEach>
			</data>
		</data>

		<!--
			<form action="{pageContext.request.contextPath}/admin" method="post">
				<input type="hidden" name="userId" value="{user.id}"/>
				<input type="hidden" name="action" value="delete"/>
				<button type="submit">Drop</button>
			</form>
		-->
		<form hidden id="addRoleDialog" class="dialog article-panel" action="${con}/admin/addRole/${user.id}" method="post">
			<button id="addRoleDialogClose">Закрыть</button>
			<p>Добавление роли пользователю <span class="username"></span></p>
			<select name="roleId" class="block">
				<option value="">--Выберите роль--</option>
			</select>
			<input type="hidden" name="userId" value="${user.id}"/>
			<input type="hidden" name="action" value="addRole"/>
			<button type="submit" class="block">Добавить роль</button>
		</form>
		<form hidden id="removeRoleDialog" class="dialog article-panel" action="${pageContext.request.contextPath}/admin/removeRole/${user.id}" method="post">
			<button id="removeRoleDialogClose">Закрыть</button>
			<p>Удаление роли пользователю <span class="username"></span></p>
			<select name="roleId" class="block">
				<option value="">--Выберите роль--</option>
			</select>
			<input type="hidden" name="userId" value="${user.id}"/>
			<input type="hidden" name="action" value="removeRole"/>
			<button type="submit" class="block">Удалить роль</button>
		</form>

		<!--
		<div>
			<form action="${pageContext.request.contextPath}/admin/addRole/${user.id}" method="post">
				<select name="roleId">
					<option value="">--Choose role--</option>
					<c:forEach items="${allRoles}" var="role">
						<c:if test="${!user.roles.contains(role) && !role.name.equals('ROLE_GUEST') && !role.name.equals('ROLE_ADMIN')}">
							<option value="${role.id}">${role.name}</option>
						</c:if>
					</c:forEach>
				</select>
				<input type="hidden" name="userId" value="${user.id}"/>
				<input type="hidden" name="action" value="addRole"/>
				<button type="submit">Confirm</button>
			</form>
			<form action="${pageContext.request.contextPath}/admin/removeRole/${user.id}" method="post">
				<select name="roleId">
					<option value="">--Choose role to remove--</option>
					<c:forEach items="${user.roles}" var="role">
						<c:if test="${!role.name.equals('ROLE_GUEST') && !role.name.equals('ROLE_ADMIN')}">
							<option value="${role.id}">${role.name}</option>
						</c:if>
					</c:forEach>
				</select>
				<input type="hidden" name="userId" value="${user.id}"/>
				<input type="hidden" name="action" value="removeRole"/>
				<button type="submit">Remove Role</button>
			</form>
		</div>
		-->
	</main>
</body>

</html>
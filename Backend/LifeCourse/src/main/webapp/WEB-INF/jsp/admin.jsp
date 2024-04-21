<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Log in with your account</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/style.css">
</head>

<body>
<div>
    <table>
        <thead>
        <th>ID</th>
        <th>UserName</th>
        <th>EMail</th>
        <th>Roles</th>
        </thead>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.username}</td>
                <td>${user.email}</td>
                <td>
                    <c:forEach items="${user.roles}" var="role">${role.readableName}; </c:forEach>
                </td>
                <td>
                    <form action="${pageContext.request.contextPath}/admin" method="post">
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action" value="delete"/>
                        <button type="submit">Drop</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/admin/addRole/${user.id}" method="post">
                        <select name="roleId">
                            <option value="">--Choose role--</option>
                            <c:forEach items="${allRoles}" var="role">
                                <c:if test="${!user.roles.contains(role) && !role.name.equals('ROLE_GUEST') && !role.name.equals('ROLE_ADMIN')}">
                                    <option value="${role.id}">${role.readableName}</option>
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
                                    <option value="${role.id}">${role.readableName}</option>
                                </c:if>
                            </c:forEach>
                        </select>
                        <input type="hidden" name="userId" value="${user.id}"/>
                        <input type="hidden" name="action" value="removeRole"/>
                        <button type="submit">Remove Role</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <a href="/">Главная</a>
</div>
</body>
</html>
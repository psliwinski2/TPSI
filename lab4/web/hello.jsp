<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Web - laboratorium 4</title>
    </head>
    <body>
        <h1>Cześć, <c:out value="${p1.firstName}"/>!</h1>
        <h1>Witaj, ${p1.firstName} ${p1.lastName}!</h1><br>
        <a href="mailto:${p1.email}">${p1.email}</a>
        <c:forEach items="${dniTygodnia}" var="dzien">
            <p>
                ${dzien}
            </p>
        </c:forEach>
    </body>
</html>


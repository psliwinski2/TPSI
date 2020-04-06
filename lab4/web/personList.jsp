<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            th {
                border: 1px solid #dddddd;
                text-align: center;
                padding: 8px;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Web - laboratorium 4</title>
    </head>
    <body>
        <table>
            <tr>
                <th>Imię</th>
                <th>Nazwisko</th>
                <th>Email</th>
            </tr>
            <c:forEach items="${listaOsob}" var="osoba">
                <tr>
                    <th>${osoba.firstName}</th>
                    <th>${osoba.lastName}</th>
                    <th>${osoba.email}</th>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>

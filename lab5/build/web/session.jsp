<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page session="true" %>
<!DOCTYPE html>
<html>
    <head>
        <style>
            #tab {
                padding-bottom: 20px;
            }
            #tab th {
                border: 1px solid #dddddd;
                text-align: center;
                padding: 8px;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Java Web - laboratorium 5</title>
    </head>
    <body>
        <h1>Licznik wejść na stronę: ${sessionScope.wizyty}</h1>
        <c:catch var="exception">
            ${student.firstName}
        </c:catch>
            <table id="tab">
                <tr>
                    <th>Imię</th>
                    <th>Nazwisko</th>
                    <th>Email</th>
                </tr>
                <c:forEach items="${sessionScope.listaStudentow}" var="student">
                    <c:if test = "${student.firstName != '' && student.lastName != '' && student.email != ''}">
                        <tr>
                            <th>${student.firstName}</th>
                            <th>${student.lastName}</th>
                            <th>${student.email}</th>
                        </tr>
                    </c:if>
                </c:forEach>
            </table>
        <form action='/lab5/session' method='post'>
            <table style="text-align: center">
                <tr>
                    <th>Wpisz się na listę studentów:</th>
                </tr>
                <tr>
                    <th>
                        Imię:
                    </th>
                    <th>
                        <input type='text' name='firstName'>
                    </th>           
                </tr>
                <tr>
                    <th>
                        Nazwisko:
                    </th>
                    <th>
                        <input type='text' name='lastName'>
                    </th>  
                </tr>
                <tr>
                    <th>
                        Email:
                    </th>
                    <th>
                        <input type='text' name='email'>
                    </th>  
                    <th>
                        <input type='submit'>
                    </th>
                </tr>
            </table>
        </form>
    </body>
</html>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <body>
        <h2>Domains ${qqq}</h2>

        <c:forEach items="${users}" var="user">
            <div>
                ${user}
            </div>
            <br />
            <br />
        </c:forEach>


        <div>
            <c:url value="/resources/text.txt" var="url"/>
            <spring:url value="/resources/text.txt" htmlEscape="true" var="springUrl" />
            Spring URL: ${springUrl} at ${time}
            <br>
            JSTL URL: ${url}
            <br>
            Message: ${message}
        </div>

    </body>
</html>

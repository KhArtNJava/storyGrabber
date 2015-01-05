<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <body>
        <form action="" method="POST">
            <input type="text" name="urlField" />
            <br />
            <br />
            <input type="submit">
        </form>

        <c:if test="${not empty siteList}">

            <c:forEach var="listValue" items="${siteList}">

                <a href="${listValue.value.get("localSiteUrlsMainPage")} ">
                    ${listValue.value.get("mainSiteUrl")} 
                </a>
                <br />
                <br />

            </c:forEach>
        </c:if>
    </body>
</html>

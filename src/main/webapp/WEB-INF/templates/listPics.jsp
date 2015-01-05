<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <body>
        <c:if test="${not empty picsList}">

            <c:forEach var="listValue" items="${picsList}">

                <div class="blockContainer" style="margin-bottom: 20px; border: 1px solid black; width: 250px; display: inline-block; padding-bottom: 15px; padding-left: 10px; padding-right: 10px;">
                    <div class="imageContainer" style="margin-top: 5px; width: 100%">
                        <a target="_blank" href='${listValue.get("linkUrl")}'>
                            <img style="border: 1px solid black; max-height: 160px; width: 245px;" 
                                 src='${listValue.get("imgUrl")}' />
                        </a>
                    </div>
                    <div class="textContainer" style="text-align: center;">
                        <h2>
                            <a target="_blank" href='${listValue.get("linkUrl")}'>
                                ${listValue.get("linkName")}
                            </a>
                        </h2> 
                    </div>
                </div>

            </c:forEach>

        </c:if>
    </body>
</html>

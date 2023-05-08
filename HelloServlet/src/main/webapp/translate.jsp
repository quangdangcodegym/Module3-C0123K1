<%@ page import="javax.servlet.http.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 4/5/2023
  Time: 2:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/translate" method="post">
    <input name="txtSearch" placeholder="search..." value="${requestScope.vocabulary}">

    <c:if test="${requestScope.result != null}">
        <div>
            <label>Kết quả: </label><label>${requestScope.result}</label>
        </div>
    </c:if>
    <ul>
        <c:forEach items="${requestScope.vocabularies}" var="c">
            <a href="/translate?vocabolary=${c}"><li>${c}</li></a>
        </c:forEach>
    </ul>

    <button type="submit" >Translate</button>
</form>


</body>
</html>

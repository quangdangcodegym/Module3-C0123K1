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
        <input name="txtSearch" placeholder="search...">
        <%
            if(request.getAttribute("result")!=null) {%>
            <div>
                <label>Kết quả: </label><label><%= request.getAttribute("result")%></label>
            </div>
        <% }%>

        <button type="submit" >Translate</button>
    </form>
</body>
</html>

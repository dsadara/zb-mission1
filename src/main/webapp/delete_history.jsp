<%@ page import="com.example.zerobase_study22.historyServiceSQLite" %>
<%@ page import="com.example.zerobase_study22.history" %>
<%--
  Created by IntelliJ IDEA.
  User: lim97
  Date: 2022-11-11
  Time: 오후 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <%
        String id = request.getParameter("id");
        history his = new history();
        his.setId(Integer.valueOf(id));
        historyServiceSQLite.withdraw(his);
        response.sendRedirect("./history.jsp");
    %>
</body>
</html>

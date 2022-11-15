<%@ page import="com.example.zerobase_study22.history" %>
<%@ page import="com.example.zerobase_study22.historyServiceSQLite" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: lim97
  Date: 2022-11-10
  Time: 오후 4:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
    <style>
        table {
            width: 100%;
            border-collapse: collapse;
            border-color: grey;
            border: 1px solid dimgrey;
        }

        th {
            background-color: mediumseagreen;
            color: white;
            border: 1px solid dimgrey;
        }

        td {
            border: 1px solid dimgrey;
        }
    </style>
</head>
<body>
    <h1>위치 히스토리 목록</h1>
    <a href="index.jsp">홈</a>
    <a href="history.jsp">위치 히스토리 목록</a>
    <a href="loadwifi.jsp">Open API 와이파이 정보 가져오기</a>
    <table>
        <tr>
            <th>ID</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>조회일자</th>
            <th>비고</th>
        </tr>
        <%
            List<history> results = historyServiceSQLite.list();
            for (history result : results) {
        %>
        <tr>
            <td><%=result.getId()%></td>
            <td><%=result.getLat()%></td>
            <td><%=result.getLnt()%></td>
            <td><%=result.getDate()%></td>
            <td>
                <form action="delete_history.jsp" method="post">
                    <input type="hidden" name="id" value="<%=result.getId()%>">
                    <button type="submit">삭제</button>
                </form>
            </td>
        </tr>
        <%
                }
        %>
    </table>

</body>
</html>

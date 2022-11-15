<%@ page import="com.example.zerobase_study22.Wifiinfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static com.example.zerobase_study22.ApiExplorer.collectWifiInfos" %>
<%@ page import="com.example.zerobase_study22.WifinfoServiceSQLite" %>
<%--
  Created by IntelliJ IDEA.
  User: lim97
  Date: 2022-11-10
  Time: 오후 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <%
        WifinfoServiceSQLite.clearTable();
        ArrayList<Wifiinfo> results = collectWifiInfos();
        WifinfoServiceSQLite.insertAll(results);
    %>
    <h1><% out.write(String.valueOf(results.size())); %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    <a href="index.jsp">홈 으로 가기</a>
</body>
</html>

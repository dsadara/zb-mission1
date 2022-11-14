<%@ page import="com.example.zerobase_study22.Wifiinfo" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.zerobase_study22.WifiinfoServiceMariaDB" %>
<%@ page import="static com.example.zerobase_study22.ApiExplorer.collectWifiInfos" %><%--
  Created by IntelliJ IDEA.
  User: lim97
  Date: 2022-11-10
  Time: 오후 4:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <%
        WifiinfoServiceMariaDB.clearTable();
        ArrayList<Wifiinfo> results = collectWifiInfos();
        WifiinfoServiceMariaDB.insertAll(results);
        WifiinfoServiceMariaDB.deleteWrongLat();
    %>
    <h1><% out.write(String.valueOf(results.size())); %>개의 WIFI 정보를 정상적으로 저장하였습니다.</h1>
    <a href="index.jsp">홈 으로 가기</a>
</body>
</html>

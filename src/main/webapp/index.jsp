<%@ page import="com.example.zerobase_study22.Wifiinfo" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.zerobase_study22.WifiinfoServiceMariaDB" %>
<%@ page import="com.example.zerobase_study22.historyServiceMariaDB" %>
<%@ page import="com.example.zerobase_study22.history" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>
</head>
<body>
    <h1>와이파이 정보 구하기</h1>
    <a href="index.jsp">홈</a>
    <a href="history.jsp">위치 히스토리 목록</a>
    <a href="loadwifi.jsp">Open API 와이파이 정보 가져오기</a>
    <p>
        <form method="get" action="index.jsp" id="form1">
            LAT:<input type="text" name="LAT" id="lat" size="10">,
            LNT:<input type="text" name="LNT" id="lnt" size="10">
        </form>
        <button onclick="getGPS()">내 위치 가져오기</button>
        <input type="submit" value="근처 WIFI 정보 보기" form="form1">
    </p>
    <script type="text/javascript">
        function getGPS() {
            navigator.geolocation.getCurrentPosition(function(pos) {
                var latitude = pos.coords.latitude;
                var longitude = pos.coords.longitude;
                alert("현재 위치는 : " + latitude + ", "+ longitude);
                document.getElementById("lat").value = latitude;
                document.getElementById("lnt").value = longitude;
            });
        }
    </script>
    <table>
        <tr>
            <th>관리번호</th>
            <th>거리(Km)</th>
            <th>자치구</th>
            <th>와이파이명</th>
            <th>도로명주소</th>
            <th>상세주소</th>
            <th>설치위치(층)</th>
            <th>설치유형</th>
            <th>설치기관</th>
            <th>서비스구분</th>
            <th>망종류</th>
            <th>설치년도</th>
            <th>실내외구분</th>
            <th>WIFI접속환경</th>
            <th>X좌표</th>
            <th>Y좌표</th>
            <th>작업일자</th>
        </tr>
        <%
            String lat = request.getParameter("LAT");
            String lnt = request.getParameter("LNT");
            List<Wifiinfo> results = null;

            if (lat != null && lnt != null) {
                WifiinfoServiceMariaDB.deleteWrongLat();
                results = WifiinfoServiceMariaDB.listNear(lat, lnt);
                // history table에 저장
                historyServiceMariaDB.Insert(new history(lat, lnt));
            }

            if (results != null) {
                for (Wifiinfo result : results) {
        %>
            <tr>
                <td><%=result.getMgrNo()%></td>
                <td><%=String.format("%.4fKM", result.getDist() * 0.001)%></td>
                <td><%=result.getWrdofc()%></td>
                <td><%=result.getMainNm()%></td>
                <td><%=result.getAdres1()%></td>
                <td><%=result.getAdres2()%></td>
                <td><%=result.getInstlFloor()%></td>
                <td><%=result.getInstlTy()%></td>
                <td><%=result.getInstlMby()%></td>
                <td><%=result.getSvcSe()%></td>
                <td><%=result.getCmcwr()%></td>
                <td><%=result.getCnstcYear()%></td>
                <td><%=result.getInoutDoor()%></td>
                <td><%=result.getRemars3()%></td>
                <td><%=result.getLat()%></td>
                <td><%=result.getLnt()%></td>
                <td><%=result.getWorkDttm()%></td>
            </tr>
        <%
                }
            }
        %>
    </table>
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

    form {
        display: inline;
    }
</style>
</body>
</html>
package com.example.zerobase_study22;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WifinfoServiceSQLite {

    public static int insertAll(List<Wifiinfo> results) {
        int result = -1;

        String url = "jdbc:sqlite:C:\\dev\\PublicWIfi.db";


        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 커넥션 객체 생성
            connection = DriverManager.getConnection(url);

            for (int i = 0; i < results.size(); i++) {
                // 스테이트먼트 객체 생성
                // 쿼리 실행
                String sql = " insert into Wifitable " +
                        " (MGRNO, WRDOFC, MAINNM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM) " +
                        " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, results.get(i).getMgrNo());
                preparedStatement.setString(2, results.get(i).getWrdofc());
                preparedStatement.setString(3, results.get(i).getMainNm());
                preparedStatement.setString(4, results.get(i).getAdres1());
                preparedStatement.setString(5, results.get(i).getAdres2());
                preparedStatement.setString(6, results.get(i).getInstlFloor());
                preparedStatement.setString(7, results.get(i).getInstlTy());
                preparedStatement.setString(8, results.get(i).getInstlMby());
                preparedStatement.setString(9, results.get(i).getSvcSe());
                preparedStatement.setString(10, results.get(i).getCmcwr());
                preparedStatement.setString(11, results.get(i).getCnstcYear());
                preparedStatement.setString(12, results.get(i).getInoutDoor());
                preparedStatement.setString(13, results.get(i).getRemars3());
                preparedStatement.setString(14, results.get(i).getLat());
                preparedStatement.setString(15, results.get(i).getLnt());
                preparedStatement.setString(16, results.get(i).getWorkDttm());

                int affectedRows = preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 객체 연결 해제
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
    public static int clearTable() {
        int result = -1;

        String url = "jdbc:sqlite:C:\\dev\\PublicWIfi.db";

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 커넥션 객체 생성
            connection = DriverManager.getConnection(url);

            // 스테이트먼트 객체 생성
            // 쿼리 실행
            String sql = "delete from Wifitable;";

            preparedStatement = connection.prepareStatement(sql);

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("삭제 성공");
                result = 1;
            } else {
                System.out.println("삭제 실패");
                result = 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            // 객체 연결 해제
            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }
    public static List<Wifiinfo> listNear(String myLat, String myLnt) {
        List<Wifiinfo> wifiList = new ArrayList<>();
        // 5개
        //1. ip(domain)
        //2. port
        //3. 계정
        //4. 패스워드
        //5. 인스턴스


        // 1. 드라이버 로드
        // 2. 커넥션 객체 생성
        // 3. 스테이트먼트 객체 생성
        // 4. 쿼리 실행
        // 5. 결과 수행
        // 6. 객체 연결 해제(close)

        String url = "jdbc:sqlite:C:\\dev\\PublicWIfi.db";


        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // finally 블록에서 사용 가능하게 바깥에서 변수 선언
        Connection connection = null;
        PreparedStatement preparedStatement = null; // 보안의 이유로 preparedStatement 사용
        ResultSet rs = null;

        try {
            // 2. 커넥션 객체 생성
            connection = DriverManager.getConnection(url);

            // 3. 스테이트먼트 객체 생성
            // 4. 쿼리 실행

            String sql = " SELECT " +
                    " *, ( 6371 * acos( cos( radians(" + myLat +") ) * cos( radians( LAT ) ) " +
                    "           * cos( radians( LNT ) - radians(" + myLnt + ") ) " +
                    "+ sin( radians(" + myLat +") ) * sin( radians( LAT ) ) ) ) AS dist2 " +
                    " FROM Wifitable " +
                    " WHERE dist2 < 1 " +
                    " ORDER BY dist2 ";

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            // 5. 결과 수행
            while (rs.next()) {
                String mgrNo = rs.getString("MGRNO");
                String wrdofc = rs.getString("WRDOFC");
                String mainNm = rs.getString("MAINNM");
                String adres1 = rs.getString("ADRES1");
                String adres2 = rs.getString("ADRES2");
                String instlFloor = rs.getString("INSTL_FLOOR");
                String instlTy = rs.getString("INSTL_TY");
                String instlMby = rs.getString("INSTL_MBY");
                String svcSe = rs.getString("SVC_SE");
                String cmcwr = rs.getString("CMCWR");
                String cnstcYear = rs.getString("CNSTC_YEAR");
                String inoutDoor = rs.getString("INOUT_DOOR");
                String remars3 = rs.getString("REMARS3");
                String lat = rs.getString("LAT");
                String lnt = rs.getString("LNT");
                String workDttm = rs.getString("WORK_DTTM");
                String dist = rs.getString("dist2");

                Wifiinfo wifiinfo = new Wifiinfo();
                wifiinfo.setMgrNo(mgrNo);
                wifiinfo.setWrdofc(wrdofc);
                wifiinfo.setMainNm(mainNm);
                wifiinfo.setAdres1(adres1);
                wifiinfo.setAdres2(adres2);
                wifiinfo.setInstlFloor(instlFloor);
                wifiinfo.setInstlTy(instlTy);
                wifiinfo.setInstlMby(instlMby);
                wifiinfo.setSvcSe(svcSe);
                wifiinfo.setCmcwr(cmcwr);
                wifiinfo.setCnstcYear(cnstcYear);
                wifiinfo.setInoutDoor(inoutDoor);
                wifiinfo.setRemars3(remars3);
                wifiinfo.setLat(lat);
                wifiinfo.setLnt(lnt);
                wifiinfo.setWorkDttm(workDttm);
                wifiinfo.setDist(Double.valueOf(dist));

                wifiList.add(wifiinfo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // close 부분이 실행되지 않을 수도 있음

            // 6. 객체 연결 해제(close)
            try {
                if (rs != null && !rs.isClosed()) {
                    rs.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (preparedStatement != null && !preparedStatement.isClosed()) {
                    preparedStatement.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return wifiList;
    }

}

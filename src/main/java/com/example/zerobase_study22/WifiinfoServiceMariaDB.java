package com.example.zerobase_study22;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class WifiinfoServiceMariaDB {
    public static int insert(Wifiinfo wifiinfo) {
        int result = -1;

        String url = "jdbc:mariadb://54.180.47.56:3306/wifitable";
        String dbUserId = "chaehyun";
        String dbPassword = "8852";

        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 커넥션 객체 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            // 스테이트먼트 객체 생성
            // 쿼리 실행
            String sql = " insert into `TABLE` " +
                    " (MGRNO, WRDOFC, MAINNM, ADRES1, ADRES2, INSTL_FLOOR, INSTL_TY, INSTL_MBY, SVC_SE, CMCWR, CNSTC_YEAR, INOUT_DOOR, REMARS3, LAT, LNT, WORK_DTTM) " +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, wifiinfo.getMgrNo());
            preparedStatement.setString(2, wifiinfo.getWrdofc());
            preparedStatement.setString(3, wifiinfo.getMainNm());
            preparedStatement.setString(4, wifiinfo.getAdres1());
            preparedStatement.setString(5, wifiinfo.getAdres2());
            preparedStatement.setString(6, wifiinfo.getInstlFloor());
            preparedStatement.setString(7, wifiinfo.getInstlTy());
            preparedStatement.setString(8, wifiinfo.getInstlMby());
            preparedStatement.setString(9, wifiinfo.getSvcSe());
            preparedStatement.setString(10, wifiinfo.getCmcwr());
            preparedStatement.setString(11, wifiinfo.getCnstcYear());
            preparedStatement.setString(12, wifiinfo.getInoutDoor());
            preparedStatement.setString(13, wifiinfo.getRemars3());
            preparedStatement.setString(14, wifiinfo.getLat());
            preparedStatement.setString(15, wifiinfo.getLnt());
            preparedStatement.setString(16, wifiinfo.getWorkDttm());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
//                System.out.println("저장 성공");
                result = 1;
            } else {
//                System.out.println("저장 실패");
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

    public static int insertAll(ArrayList<Wifiinfo> results) {
        int result = -1;

        String url = "jdbc:mariadb://54.180.47.56:3306/wifitable";
        String dbUserId = "chaehyun";
        String dbPassword = "8852";

        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 커넥션 객체 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            for (int i = 0; i < results.size(); i++) {
                // 스테이트먼트 객체 생성
                // 쿼리 실행
                String sql = " insert into wifitable2 " +
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





//            if (affectedRows > 0) {
////                System.out.println("저장 성공");
//                result = 1;
//            } else {
////                System.out.println("저장 실패");
//                result = 0;
//            }

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
}

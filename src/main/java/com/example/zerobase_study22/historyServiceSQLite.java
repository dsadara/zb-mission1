package com.example.zerobase_study22;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class historyServiceSQLite {
    public static int Insert(history history) {
        int result = -1;

        String url = "jdbc:sqlite:C:\\dev\\PublicWIfi.db";

        // 1. 드라이버 로드
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 커넥션 객체 생성
            connection = DriverManager.getConnection(url);

            // 스테이트먼트 객체 생성
            // 쿼리 실행
            String sql = "insert into History (LAT, LNT, DATE) values (?, ?, datetime('now', 'localtime'));";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, history.getLat());
            preparedStatement.setString(2, history.getLnt());

            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("저장 성공");
                result = 1;
            } else {
                System.out.println("저장 실패");
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

    public static List<history> list() {
        List<history> historyList = new ArrayList<>();
        String url = "jdbc:sqlite:C:\\dev\\PublicWIfi.db";

        // 1. 드라이버 로드
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
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
            String sql = " select * " +
                    " from History ";

            preparedStatement = connection.prepareStatement(sql);

            rs = preparedStatement.executeQuery();

            // 5. 결과 수행
            while (rs.next()) {
                int id = rs.getInt(1);
                String lat = rs.getString(2);
                String lnt = rs.getString(3);
                String date = rs.getString(4);

                history history = new history(id, lat, lnt, date);


                historyList.add(history);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

        return historyList;
    }

    public static int withdraw(history history) {
        int result = -1;

        String url = "jdbc:sqlite:C:\\dev\\PublicWIfi.db";

        // 1. 드라이버 로드
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // 커넥션 객체 생성
            connection = DriverManager.getConnection(url);

            // 스테이트먼트 객체 생성
            // 쿼리 실행
            String sql = " delete from History where ID=? ";

            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, history.getId());

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

}

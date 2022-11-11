package com.example.zerobase_study22;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class historyServiceMariaDB {

    public static void main(String[] args) {
        Insert(new history("11.11", "12.11"));
    }

    public static int Insert(history history) {
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
            String sql = "insert into history2 (LAT, LNT, DATE) values (?, ?, now());";

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

        String url = "jdbc:mariadb://54.180.47.56:3306/wifitable";
        String dbUserId = "chaehyun";
        String dbPassword = "8852";

        // 1. 드라이버 로드
        try {
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // finally 블록에서 사용 가능하게 바깥에서 변수 선언
        Connection connection = null;
        PreparedStatement preparedStatement = null; // 보안의 이유로 preparedStatement 사용
        ResultSet rs = null;

        try {
            // 2. 커넥션 객체 생성
            connection = DriverManager.getConnection(url, dbUserId, dbPassword);

            // 3. 스테이트먼트 객체 생성
            // 4. 쿼리 실행
            String sql = " select * " +
                    " from history2 ";


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

        return historyList;
    }

    public static int withdraw(history history) {
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
            String sql = " delete from history2 where ID=? ";

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

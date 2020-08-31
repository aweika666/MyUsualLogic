package com.aweika.jdbc;

import java.sql.*;

/**
 * @author: Michael
 * @date: 2020/8/20
 * @description:
 */
public class JdbcTest {
    public static void main(String[] args) {
        Connection connection = DriverManager.getConnection();
        Statement statement = connection.createStatement();
        ResultSet sql = statement.executeQuery("sql");

    }
}

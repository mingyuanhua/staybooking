package com.hmy.staybooking;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class StaybookingApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(StaybookingApplication.class);
        Connection connection = null;
        Statement statement = null;
        String postgresUrl = "localhost";
        try {
            // 原生的JDBC
            logger.debug("Creating database if not exist...");
            connection = DriverManager.getConnection("jdbc:postgresql://" + postgresUrl + ":5432/", "postgres", "secret");
            statement = connection.createStatement();
            statement.executeQuery("SELECT count(*) FROM pg_database WHERE datname = 'staybooking'");
            ResultSet resultSet = statement.getResultSet();
            resultSet.next();
            int count = resultSet.getInt(1);

            if (count <= 0) {
                // 如果数据库不存在，要手动先创建一个默认的空的数据库
                statement.executeUpdate("CREATE DATABASE staybooking");
                logger.debug("Database created.");
            } else {
                logger.debug("Database already exist.");
            }
        } catch (SQLException e) {
            logger.error(e.toString());
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    logger.debug("Closed Statement.");
                }
                if (connection != null) {
                    logger.debug("Closed Connection.");
                    connection.close();
                }
            } catch (SQLException e) {
                logger.error(e.toString());
            }
        }



        SpringApplication.run(StaybookingApplication.class, args);
    }

}


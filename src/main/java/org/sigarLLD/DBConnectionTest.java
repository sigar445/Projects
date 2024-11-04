package org.sigarLLD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
public class DBConnectionTest  {
        public static void main(String[] args) {
            try {
                Connection connection = DriverManager.getConnection(
                        "jdbc:postgresql://localhost:5432/shortendb?sslmode=disable",
                        "sigar_101",
                        "qwerty123"
                );
                if (connection != null) {
                    System.out.println("Connection successful!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


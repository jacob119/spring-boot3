package com.example.spring3.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class TrinoQueryService {
    @Value(value = "${trino.datasource.url}")
    private String url;

    @Value(value = "${trino.datasource.username}")
    private String username;

    @Value(value = "${trino.datasource.password}")
    private String password;

    @Async
    public CompletableFuture<ResultSet> executeQueryAsync(String query) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(query);
            return CompletableFuture.completedFuture(resultSet);
        } catch (Exception e) {
            e.printStackTrace();
            return CompletableFuture.completedFuture(null);
        }
    }


//    public ResultSet executeQuerySync(String query) {
//        try (Connection connection = DriverManager.getConnection(url, username, password);
//             Statement statement = connection.createStatement()) {
//            return statement.executeQuery(query);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }

    public ResponseEntity<List<Map<String, String>>> executeQuerySync(String query) {
        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {
            System.out.println("### starting query ###");
            ResultSet resultSet = statement.executeQuery(query);
            List<Map<String, String>> resultRows = new ArrayList<>();
            System.out.println("### complete query ###");
            if (resultSet != null) {
                try {
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();

                    while (resultSet.next()) {
                        System.out.println("### next query query ###");
                        Map<String, String> row = new HashMap<>();
                        for (int i = 1; i <= columnCount; i++) {
                            String columnName = metaData.getColumnName(i);
//                            String columnType = metaData.getColumnTypeName(i);
                            String columnValue = resultSet.getString(i);
                            row.put(columnName, columnValue);
                        }
                        resultRows.add(row);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    return ResponseEntity.status(500).build();
                }
            }
            return ResponseEntity.ok(resultRows);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

}

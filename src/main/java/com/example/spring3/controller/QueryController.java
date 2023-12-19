package com.example.spring3.controller;

import com.example.spring3.service.TrinoQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/v1/queries")
public class QueryController {


    @Autowired
    public TrinoQueryService trinoQueryService;

    @GetMapping("/trino")
    public ResponseEntity<List<Map<String, String>>> executeTrinoQuery(@RequestParam String query, @RequestParam(required = false, defaultValue = "async") String mode) {

        return trinoQueryService.executeQuerySync(query);

//        if ("async".equalsIgnoreCase(mode)) {
//            return executeTrinoQueryAsync(query);
//        } else {
//            return trinoQueryService.executeQuerySync(query);
//        }
    }

    private CompletableFuture<List<Map<String, String>>> executeTrinoQueryAsync(String query) {
        return trinoQueryService.executeQueryAsync(query)
                .thenApplyAsync(this::processResultSet);
    }


    private List<Map<String, String>> processResultSet(ResultSet resultSet) {
        List<Map<String, String>> resultRows = new ArrayList<>();
        if (resultSet != null) {
            try {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                while (resultSet.next()) {
                    Map<String, String> row = new HashMap<>();
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = metaData.getColumnName(i);
                        String columnValue = resultSet.getString(i);
                        row.put(columnName, columnValue);
                    }
                    resultRows.add(row);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultRows;
    }
}

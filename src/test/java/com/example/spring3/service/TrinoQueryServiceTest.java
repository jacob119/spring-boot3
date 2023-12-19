package com.example.spring3.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.sql.ResultSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TrinoQueryServiceTest {
    @Autowired
    public TrinoQueryService trinoQueryService;


    @Test
    public void testTrinoConnection() {
//        ResultSet resultSet = trinoQueryService.executeQuerySync("select 10");
//        System.out.println(resultSet);

    }
}
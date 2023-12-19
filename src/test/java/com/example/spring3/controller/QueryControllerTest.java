package com.example.spring3.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class QueryControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void testExecuteTrinoQuery() throws Exception{
        String url =  "/api/v1/queries/trino?query={query}&mode=sync";
        String query = "select count(1) as cnt from tpch.sf1.customer";
        ResponseEntity<String> response = template.getForEntity(url, String.class, query);
//        System.out.println(response.getBody());
        System.out.println("end!!");

        // 나중에 알아보자 ??
//        UriComponents builder = UriComponentsBuilder.fromHttpUrl(url)
//                .queryParam("query","{query}").build();
//        HttpHeaders headers = new HttpHeaders() {{
//            setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//            setAccept(List.of(MediaType.APPLICATION_JSON));
//        }};
//        ResponseEntity<String> response = template.exchange(builder.toUriString(),HttpMethod.GET,new HttpEntity<>(headers), String.class);
//        System.out.println(response.getBody());
    }

    @Test
    public void testHeavyQueryExecuteTrinoQuery() throws Exception {
        String url = "/api/v1/queries/trino?query={query}&mode=sync";
        String query = "select * from tpch.sf1.customer";
        ResponseEntity<String> response = template.getForEntity(url, String.class, query);
        System.out.println(response.getBody());
    }

    @Test
    public void testShowCatalogs() throws Exception {
        String url = "/api/v1/queries/trino?query={query}&mode=sync";
        String query = "show catalogs";
        ResponseEntity<String> response = template.getForEntity(url, String.class, query);
        System.out.println(response.getBody());
    }

    @Test
    public void testSQLHistory() throws Exception {
        String url = "/api/v1/queries/trino?query={query}&mode=sync";
        String query = "select * from system.runtime.queries";
        ResponseEntity<String> response = template.getForEntity(url, String.class, query);
        System.out.println(response.getBody());
    }

    @Test
    public void testSQLColumns() throws Exception {
        String url = "/api/v1/queries/trino?query={query}&mode=sync";
        String query = "show columns from system.runtime.queries";
        ResponseEntity<String> response = template.getForEntity(url, String.class, query);
        System.out.println(response.getBody());
    }
}
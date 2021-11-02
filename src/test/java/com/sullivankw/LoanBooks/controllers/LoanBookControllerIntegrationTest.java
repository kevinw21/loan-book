package com.sullivankw.LoanBooks.controllers;

import com.sullivankw.LoanBooks.Application;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringJUnitConfig
@SpringBootTest(
        classes = Application.class,
        webEnvironment = RANDOM_PORT,
        properties = "spring.main.banner.mode=off"
)
public class LoanBookControllerIntegrationTest {

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    public void setup() {
        RestAssured.port = serverPort;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    //todo use RestAssured and assert stuff

    @Nested
    class happyPathTests {

        @Test
        public void testGetAssignmentsAndYields_() {
            //todo
        }

    }
}

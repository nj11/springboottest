package com.springboottest.controller;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

//  annotation tells Spring Boot to look for a main configuration class
//  (one with @SpringBootApplication, for instance) and use that
//  to start a Spring application context
@SpringBootTest
public class SmokeTest {

    @Test
    public void contextLoads() {
    }

}

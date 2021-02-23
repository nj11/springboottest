package com.springboottest.controller;

import com.springboottest.controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

// Note the use of webEnvironment=RANDOM_PORT to start the server with
// a random port (useful to avoid conflicts in test environments)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//Note this starts the server for testing.
@RunWith(SpringRunner.class)
public class HomeControllerTest {

    // And the injection of the port with @LocalServerPort
    @LocalServerPort
    private int port;

    @Autowired
    //Spring Boot has automatically provided a TestRestTemplate for you
    //All you have to do is add @Autowired to it.
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Hello, World");
    }
}

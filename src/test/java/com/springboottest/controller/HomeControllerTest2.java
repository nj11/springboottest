package com.springboottest.controller;


import com.springboottest.service.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
/**
 * Full context is loaded in this context but not the server.
 * Another useful approach is to not start the server at all but to test only the layer
 * below that, where Spring handles the incoming HTTP request and hands it
 * off to your controller. That way, almost of the full stack is used,
 * and your code will be called
 * in exactly the same way as if it were processing a
 * real HTTP request but without the cost of starting the server.
 *
 *  To do that, use Spring’s MockMvc and ask for that to be injected for you
 *  by using the @AutoConfigureMockMvc annotation on the test case.
 */
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class HomeControllerTest2 {
    @Autowired
    private MockMvc mockMvc;

    // Can be used to define a Mockito mock
    // for a bean inside your ApplicationContext
    @MockBean
    private GreetingService greetingService;

    /**
     * In this test, the full Spring application context is started but without the server
     * However, in this test, Spring Boot instantiates only the web layer rather
     * than the whole context.
     *
     * @throws Exception
     */
    @Test
    public void shouldReturnDefaultMessage() throws Exception {

           this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));

    }

    @Test
    public void testGreeting() throws Exception {

        given(this.greetingService.greeting()).willReturn("hello");

    }


}

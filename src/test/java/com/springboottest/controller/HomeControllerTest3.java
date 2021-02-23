package com.springboottest.controller;

import com.springboottest.controller.HomeController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)

//We can narrow the tests to only the web layer by using @WebMvcTest
//Spring Boot provides the @WebMvcTest annotation to fire up an application context
// that contains only the beans needed for testing a web controller:

//much faster since it loads only a portion of the application
//SpringBootTest loads complete application and injects all the beans which can be slow.
@WebMvcTest(controllers = HomeController.class)

/**
 * @WebMvcTest - for testing the controller layer
 * @JsonTest - for testing the JSON marshalling and unmarshalling
 * @DataJpaTest - for testing the repository layer
 * @RestClientTests - for testing REST clients
 */
public class HomeControllerTest3 {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }
}


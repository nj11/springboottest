package com.springboottest.service;

import org.springframework.stereotype.Component;

@Component
public class GreetingService {

    public String greeting(){
        return "Hello, World";
    }
}

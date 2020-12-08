package com.springframework.sfgdi.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Primary
@Service
public class PrimaryGreetingService implements GreetingService {

    private String name;

    @Override
    public String sayGreeting() {
        return "Hello World - Primary";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

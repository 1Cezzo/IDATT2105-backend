package com.IDATT2105.IDATT2105.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProtectedResourceController {

    @GetMapping("/protected")
    public String protectedResource() {
        return "This is a protected resource.";
    }
}

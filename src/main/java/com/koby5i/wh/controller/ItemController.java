package com.koby5i.wh.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @RequestMapping("/")
    public String home(){
        return "Hello from RESTController";
    }
}

package com.example.eurekaclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/query")
public class QueryController {

    @Value("${eureka.instance.metadata-map.zone}")
    private String zone;

    @Value("${eureka.client.region}")
    private String region;


    @GetMapping(value = "/zone")
    public String zone() {
        return "{\"zone\"=\"" + zone + "\"}";
    }

    @GetMapping(value = "/region")
    public String region(){
        return "{\"region\"=\"" + region + "\"}";
    }
}

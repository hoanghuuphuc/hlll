package com.pts.Controller.site;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/return")
public class ReturnController {
    @GetMapping
    public ResponseEntity<Map<String,String>> showRespond(@RequestParam Map<String,String> allParam){
        return ResponseEntity.ok(allParam);
    }
}


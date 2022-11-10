package com.Ezenweb.controller;


import org.springframework.web.bind.annotation.*;

// p.75
@RestController
@RequestMapping("/api/v1/delete-api")
public class DleteController {

    @DeleteMapping("/{variable}")
    public String DeleteMapping(@PathVariable("variable") String variable ){
        return variable;
    }
    // 2. p.76
    @DeleteMapping("/request1")
    public String getRequstParam1(@RequestParam("variable") String variable ){
        return  variable;
    }
}

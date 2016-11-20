package org.colin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mvc")
public class IndexController {

    @RequestMapping("/init")
    public String init(){
 
            return "index";
 
    }
}

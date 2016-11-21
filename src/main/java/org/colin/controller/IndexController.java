package org.colin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping("/generate/init.do")
    public String init(){
 
            return "generate";
 
    }
}

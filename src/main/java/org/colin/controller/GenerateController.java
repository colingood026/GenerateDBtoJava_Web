package org.colin.controller;


import org.colin.common.enumClass.OrMappingEnum;
import org.colin.generate.mybatis.toLocal.MybatisGeneratorToLocal;
import org.colin.vo.ConnDeatilVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/generateController")
public class GenerateController {

    

    
    
    @RequestMapping("/generate.do")
    @ResponseBody
    public void generate(@RequestParam(required=true) String dbDriverClassNm,
                         @RequestParam(required=true) String url,
                         @RequestParam(required=true) String userNm,
                         @RequestParam(required=true) String psd,
                         @RequestParam(required=true) String tables,
                         @RequestParam(required=true) String orMappingType,
                         @RequestParam(required=true) String savedLocation){
        
        ConnDeatilVo connDeatilVo = 
                new ConnDeatilVo(url, userNm, psd, tables, dbDriverClassNm,orMappingType,savedLocation);
        
        if(orMappingType.equals(OrMappingEnum.Mybatis.getOrMappingName())){
            MybatisGeneratorToLocal.generate(connDeatilVo);
        }else{
            System.out.println("hibernate");
        }
        
        
        
    }
    
    
}

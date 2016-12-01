package org.colin.controller;


import javax.annotation.Resource;

import org.colin.common.Exception.JavaTypeNotFoundException;
import org.colin.common.enumClass.OrMappingEnum;
import org.colin.common.util.ExceptionRecoedUtil;
import org.colin.generate.mybatis.toLocal.MybatisGeneratorToLocal;
import org.colin.vo.ConnDeatilVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/generateController")
public class GenerateController {

    
    @Resource MybatisGeneratorToLocal mybatisGeneratorToLocal;
    
    
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
            try {
				mybatisGeneratorToLocal.generate(connDeatilVo);
			} catch (JavaTypeNotFoundException e) {				
				System.out.println("Exception:"+e.getMessage());
			} catch(Exception e){
				System.out.println(ExceptionRecoedUtil.recordException(e));
			}
        }else{
            System.out.println("hibernate");
        }
        
        
        
    }

    
}

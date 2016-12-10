package org.colin.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.colin.common.Exception.OrgColinException;
import org.colin.common.enumClass.DriverClassEnum;
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
 
    
    @RequestMapping(value = "/generate.do", produces = "application/json")
    @ResponseBody
    public Map<String,String> generate(@RequestParam(required=true) String dbDriverClassNm,
                                       @RequestParam(required=true) String host,
                                       @RequestParam(required=true) Integer port,
                                       @RequestParam(required=true) String dbName,
                                       @RequestParam(required=true) String userNm,
                                       @RequestParam(required=true) String psd,
                                       @RequestParam(required=true) String tables,
                                       @RequestParam(required=true) String orMappingType,
                                       @RequestParam(required=true) String savedLocation,
                                       @RequestParam(required=true) String daoPackageRoot,
                                       @RequestParam(required=true) String modelPackageRoot){
        Map<String,String> result = new HashMap<>();;
        String message = "檔案匯出成功";
        
        ConnDeatilVo connDeatilVo = new ConnDeatilVo();
        connDeatilVo.setDbDriverClassNm(dbDriverClassNm);
        connDeatilVo.setOrMappingType(orMappingType);
        connDeatilVo.setPsd(psd);
        connDeatilVo.setSaveLocation(savedLocation);
        connDeatilVo.setTables(tables);
        connDeatilVo.setUrl(dbDriverClassNm, host, port, dbName);
        connDeatilVo.setUserNm(userNm);
        connDeatilVo.setDaoPackageRoot(daoPackageRoot);
        connDeatilVo.setModelPackageRoot(modelPackageRoot);
        
        if(orMappingType.equals(OrMappingEnum.Mybatis.getOrMappingName())){
            try {
				mybatisGeneratorToLocal.generate(connDeatilVo);
			} catch (OrgColinException e) {				
				System.out.println("Exception:"+e.getMessage());
				message = e.getMessage();
			} catch(Exception e){
				System.out.println(ExceptionRecoedUtil.recordException(e));
				message = "唉唷，出錯嘍~";
			}
        }else{
            System.out.println("hibernate");
        }        
        result.put("message", message);
        return result;
    }
    
    @RequestMapping(value = "/getJdbcUrlPrefix.do", produces = "application/json")
    @ResponseBody
    public List<Map<String,String>> getJdbcUrlPrefix(){
        
        List<Map<String,String>> list = new ArrayList<>();
        
        for(DriverClassEnum a:DriverClassEnum.values()){
            Map<String,String> map = new HashMap<>();
            map.put(a.getDbNm(), a.getUrlPrefix()+","+a.getDefaultPort());
            list.add(map);
        }
        
        return list;
    }
    

    
}

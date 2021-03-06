package org.colin.generate.mybatis.toLocal;
import java.util.List;

import org.colin.common.util.MethodUtils;
import org.colin.generate.XmlGenerator;
import org.colin.vo.TableFieldsVo;
import org.springframework.stereotype.Service;

@Service("mybatisXmlGenerator")
public class MybatisXmlGenerator implements XmlGenerator{


    @Override
	public String buildXml(List<TableFieldsVo> fields, 
			               String classNm,
						   String tableNm){		
		String daoPackageRoot = "";
        for(TableFieldsVo field:fields){
            daoPackageRoot = field.getDaoPackageRoot();
        }
        
        
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8' ?>").append(MethodUtils.N);;
		sb.append("<!DOCTYPE mapper PUBLIC '-//mybatis.org//DTD Mapper 3.0//EN'"
				+ " 'http://mybatis.org/dtd/mybatis-3-mapper.dtd'>").append(MethodUtils.N);
		sb.append("<mapper namespace='"+daoPackageRoot+"."+classNm+"Mapper'>").append(MethodUtils.N);
		
		sb.append(MethodUtils.CAPS+"<resultMap type='"+classNm+"' id='"+classNm+"'>")
															.append(MethodUtils.N);
		// resultMap
        for(TableFieldsVo field:fields){        	
        	String fieldNm = MethodUtils.removeSplitForField(field.getColumnNm());
        	String columnNm;
			try {
				columnNm = field.getColumnNm().toUpperCase();
			} catch (NullPointerException e) {
				System.out.println("can not find JavaType");
				throw e;
			}
        	if(fieldNm.equals("id")){
        		sb.append(MethodUtils.DOUBLE_CAPS+"<id property='"+fieldNm+"' column='"+columnNm+"'/>")
        																.append(MethodUtils.N);
        	}else{
        		sb.append(MethodUtils.DOUBLE_CAPS+"<result property='"+fieldNm+"' column='"+columnNm+"'/>")
        																.append(MethodUtils.N);
        	}        	
        }		
		sb.append(MethodUtils.CAPS+"</resultMap>").append(MethodUtils.N);
		// select
		sb.append(MethodUtils.CAPS+"<select id='getAll' resultMap='"+classNm+"'>").append(MethodUtils.N);
		sb.append(MethodUtils.DOUBLE_CAPS+"SELECT * FROM "+tableNm).append(MethodUtils.N);
        sb.append(MethodUtils.CAPS+"</select>").append(MethodUtils.N);
        //
        sb.append("</mapper>");
        
        return sb.toString();
		
	}
}

package org.colin.generate.mybatis.toLocal;
import java.util.List;
import java.util.Set;

import org.colin.common.enumClass.ImportJarEnum;
import org.colin.common.util.MethodUtils;
import org.colin.generate.DBJavaBeanGenerator;
import org.colin.vo.TableFieldsVo;
import org.springframework.stereotype.Service;

/**
 * 
 */

/**
 * @author Colin
 *
 */
@Service("mybatisJavaBeanGenerator")
public class MybatisJavaBeanGenerator implements DBJavaBeanGenerator{

	

	


    @Override
	public String buildBean(Set<String> importJars, 
    					    List<TableFieldsVo> fields, 
    					    String classNm){
        String modelPackageRoot = "";
        for(TableFieldsVo field:fields){
            modelPackageRoot = field.getModelPackageRoot();
        }
        
        
		StringBuffer sb = new StringBuffer();
		sb.append("package " + modelPackageRoot + ";").append(MethodUtils.N);
		for(String importJar:importJars){			
			sb.append(importJar).append(MethodUtils.N);
		}
		sb.append(ImportJarEnum.Annotation_Mybatis_Alias.getImportName()).append(MethodUtils.N);
		sb.append(MethodUtils.N);
		sb.append("@Alias(\""+classNm+"\")").append(MethodUtils.N);
		sb.append("public class "+classNm+" implements Serializable{").append(MethodUtils.N);
		sb.append(MethodUtils.N);
		sb.append(MethodUtils.CAPS+"private static final long serialVersionUID = 1L;").append(MethodUtils.N);
		sb.append(MethodUtils.N);
		// create field
		for(TableFieldsVo field:fields){
			String name = MethodUtils.removeSplitForField(field.getColumnNm());
			String type = field.getJavaType();
			sb.append(MethodUtils.createField(name,type,null)).append(MethodUtils.N);
		}		
		sb.append(MethodUtils.N);
		// create get
		for(TableFieldsVo field:fields){
			String name = MethodUtils.removeSplitForField(field.getColumnNm());
			String type = field.getJavaType();
			sb.append(MethodUtils.createGet(name,type)).append(MethodUtils.N);
		}
		sb.append(MethodUtils.N);
		// create set
		for(TableFieldsVo field:fields){
			String name = MethodUtils.removeSplitForField(field.getColumnNm());
			String type = field.getJavaType();
			sb.append(MethodUtils.createSet(name,type)).append(MethodUtils.N);
		}
		sb.append(MethodUtils.N);
		sb.append("}");
		
		return sb.toString();
	}
	

}

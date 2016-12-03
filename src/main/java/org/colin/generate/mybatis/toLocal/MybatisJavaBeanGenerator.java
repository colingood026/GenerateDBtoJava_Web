package org.colin.generate.mybatis.toLocal;
import java.util.List;
import java.util.Set;

import org.colin.common.enumClass.ImportJarEnum;
import org.colin.common.util.MethodUtils;
import org.colin.generate.JavaBeanGenerator;
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
public class MybatisJavaBeanGenerator implements JavaBeanGenerator{

	

	
//	private GenerateMybatisJavaBean(){
//		
//	}
	/**
	 * 
	 * @param importJars
	 * @param fields
	 * @param classNm
	 */
    @Override
	public String buildBean(String packageNm,
							   Set<String> importJars, 
							   List<TableFieldsVo> fields, 
							   String classNm){
		
		StringBuffer sb = new StringBuffer();
		sb.append("package "+packageNm+".model").append(MethodUtils.N);
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
			sb.append(createField(name,type)).append(MethodUtils.N);
		}		
		sb.append(MethodUtils.N);
		// create get
		for(TableFieldsVo field:fields){
			String name = MethodUtils.removeSplitForField(field.getColumnNm());
			String type = field.getJavaType();
			sb.append(createGet(name,type)).append(MethodUtils.N);
		}
		sb.append(MethodUtils.N);
		// create set
		for(TableFieldsVo field:fields){
			String name = MethodUtils.removeSplitForField(field.getColumnNm());
			String type = field.getJavaType();
			sb.append(createSet(name,type)).append(MethodUtils.N);
		}
		sb.append(MethodUtils.N);
		sb.append("}");
		
		return sb.toString();
	}
	
	/**
	 * 
	 * @param fieldNm
	 * @param type
	 * @return
	 */
	private String createField(String fieldNm,String type){
		return MethodUtils.CAPS+"private "+type+" "+fieldNm+";";
	}
	/**
	 * 
	 * @param fieldNm
	 * @param type
	 * @return
	 */
	private String createGet(String fieldNm,String type){
//		public String getJavaType() {
//			return javaType;
//		}
		String firstChar = fieldNm.substring(0, 1);
		String get = "get"+firstChar.toUpperCase()+fieldNm.substring(1);
		return MethodUtils.CAPS+"public "+type+" "+get+"(){ return "+fieldNm+";}";
	}
	/**
	 * 
	 * @param fieldNm
	 * @param type
	 * @return
	 */
	private String createSet(String fieldNm,String type){
//		public void setJavaType(String javaType) {
//			this.javaType = javaType;
//		}
		String firstChar = fieldNm.substring(0, 1);
		String set = "set"+firstChar.toUpperCase()+fieldNm.substring(1);
		return MethodUtils.CAPS+"public void "+set+"("+type+" "+fieldNm+"){"+MethodUtils.N+MethodUtils.DOUBLE_CAPS+
										"this."+fieldNm+" = "+fieldNm+";"+MethodUtils.N+MethodUtils.CAPS+"}";
		
	}
	

}

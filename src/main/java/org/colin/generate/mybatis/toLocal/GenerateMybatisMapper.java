package org.colin.generate.mybatis.toLocal;
import java.util.List;
import java.util.Set;

import org.colin.common.enumClass.ImportJarEnum;
import org.colin.common.util.MethodUtils;
import org.colin.vo.TableFieldsVo;
import org.springframework.stereotype.Service;

@Service
public class GenerateMybatisMapper {
	/**
	 * 
	 * @param importJars
	 * @param fields
	 * @param classNm
	 */
	public String build(String packageNm,
			                   Set<String> importJars, 
							   List<TableFieldsVo> fields, 
							   String classNm){
		
		StringBuffer sb = new StringBuffer();
		sb.append("package "+packageNm+".mapper").append(MethodUtils.CHANGE_LINE);
		sb.append(ImportJarEnum.Annotation_Sprig_Repository.getImportName()).append(MethodUtils.CHANGE_LINE);
		sb.append(ImportJarEnum.Annotation_Mybatis_Param.getImportName()).append(MethodUtils.CHANGE_LINE);
		sb.append("@Repository").append(MethodUtils.CHANGE_LINE);
		sb.append("public interface "+classNm+"Mapper{").append(MethodUtils.CHANGE_LINE);
		

		sb.append(MethodUtils.CAPS+"public List<"+classNm+"> getAll();").append(MethodUtils.CHANGE_LINE);;
		
		
		sb.append("}");
		
		return sb.toString();
	}
}

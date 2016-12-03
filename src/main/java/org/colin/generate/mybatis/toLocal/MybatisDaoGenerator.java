package org.colin.generate.mybatis.toLocal;
import java.util.List;
import java.util.Set;

import org.colin.common.enumClass.ImportJarEnum;
import org.colin.common.util.MethodUtils;
import org.colin.generate.DaoGenerator;
import org.colin.vo.TableFieldsVo;
import org.springframework.stereotype.Service;

@Service("mybatisDaoGenerator")
public class MybatisDaoGenerator implements DaoGenerator{
	/**
	 * 
	 * @param importJars
	 * @param fields
	 * @param classNm
	 */
    @Override
	public String buildDao(String packageNm,
			                   Set<String> importJars, 
							   List<TableFieldsVo> fields, 
							   String classNm){
		
		StringBuffer sb = new StringBuffer();
		sb.append("package "+packageNm+".mapper").append(MethodUtils.N);
		sb.append(ImportJarEnum.Annotation_Sprig_Repository.getImportName()).append(MethodUtils.N);
		sb.append(ImportJarEnum.Annotation_Mybatis_Param.getImportName()).append(MethodUtils.N);
		sb.append("@Repository").append(MethodUtils.N);
		sb.append("public interface "+classNm+"Mapper{").append(MethodUtils.N);
		

		sb.append(MethodUtils.CAPS+"public List<"+classNm+"> getAll();").append(MethodUtils.N);;
		
		
		sb.append("}");
		
		return sb.toString();
	}
}

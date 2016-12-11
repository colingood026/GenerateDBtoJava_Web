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

    
    @Override
	public String buildDao(Set<String> importJars, 
						   List<TableFieldsVo> fields, 
						   String classNm){
		
        String daoPackageRoot = "";
        for(TableFieldsVo field:fields){
            daoPackageRoot = field.getDaoPackageRoot();
        }
        
        
		StringBuffer sb = new StringBuffer();
		sb.append("package "+daoPackageRoot+";").append(MethodUtils.N);
		sb.append(ImportJarEnum.Annotation_Sprig_Repository.getImportName()).append(MethodUtils.N);
		sb.append(ImportJarEnum.Annotation_Mybatis_Param.getImportName()).append(MethodUtils.N);
		sb.append(ImportJarEnum.Util_List.getImportName()).append(MethodUtils.N);
		sb.append(ImportJarEnum.Util_Array_List.getImportName()).append(MethodUtils.N);
		sb.append("@Repository").append(MethodUtils.N);
		sb.append("public interface "+classNm+"Mapper{").append(MethodUtils.N);
		

		sb.append(MethodUtils.CAPS+"public List<"+classNm+"> getAll();").append(MethodUtils.N);;
		
		
		sb.append("}");
		
		return sb.toString();
	}
}

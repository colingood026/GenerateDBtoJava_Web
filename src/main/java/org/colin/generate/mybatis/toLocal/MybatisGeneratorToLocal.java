package org.colin.generate.mybatis.toLocal;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;


import org.colin.common.util.ExportToLocalUtil;
import org.colin.common.util.GetJavaDataByDBInfoUtil;
import org.colin.vo.ConnDeatilVo;
import org.colin.vo.JavaDataVo;
import org.colin.vo.TableFieldsVo;
import org.springframework.stereotype.Service;

@Service
public class MybatisGeneratorToLocal {
    
    
    @Resource GenerateMybatisJavaBean myBatisBeanGenerator;
    @Resource GenerateMybatisMapper myBatisMapperGenerator;
    @Resource GenerateMybatisXml myBatisXmlGenerator;
    
    public void generate(ConnDeatilVo connDetail) throws Exception {
        

        //
        List<JavaDataVo> javaDataVos = GetJavaDataByDBInfoUtil.getJavaDatas(connDetail);
        //
        String location = connDetail.getSaveLocation();
        location = ExportToLocalUtil.createDirectory(location);
        location = location.replaceAll("\\\\", "/");
        String packageNm = location.split("/")[1];

        if (location != null) {
            for (JavaDataVo vo : javaDataVos) {
                Set<String> importJars = vo.getImportJars();
                String classNm = vo.getClassNm();
                String tableNm = vo.getTableNm();
                List<TableFieldsVo> fields = vo.getFields();
                //
                String bean = myBatisBeanGenerator.build(packageNm, importJars, fields, classNm);
                ExportToLocalUtil.exportBean(location, classNm, bean);
                //
                String mapper = myBatisMapperGenerator.build(packageNm, importJars, fields, classNm);
                ExportToLocalUtil.exportMapper(location, classNm, mapper);
                //
                String xml = myBatisXmlGenerator.build(packageNm, fields, classNm, tableNm);
                ExportToLocalUtil.exportXml(location, classNm, xml);
                //
                System.out.println("****************************************");
            }
        }

    }





}

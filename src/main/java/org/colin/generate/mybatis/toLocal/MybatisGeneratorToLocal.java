package org.colin.generate.mybatis.toLocal;


import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.colin.common.util.ExportToLocalUtil;
import org.colin.common.util.GetJavaDataByDBInfoUtil;
import org.colin.generate.DaoGenerator;
import org.colin.generate.JavaBeanGenerator;
import org.colin.generate.XmlGenerator;
import org.colin.vo.ConnDeatilVo;
import org.colin.vo.JavaDataVo;
import org.colin.vo.TableFieldsVo;
import org.springframework.stereotype.Service;

@Service
public class MybatisGeneratorToLocal {
    
    
    @Resource(name="mybatisJavaBeanGenerator") 
    JavaBeanGenerator mybatisJavaBeanGenerator;
    @Resource(name="mybatisDaoGenerator") 
    DaoGenerator mybatisDaoGenerator;
    @Resource(name="mybatisXmlGenerator") 
    XmlGenerator mybatisXmlGenerator;
    
    public void generate(ConnDeatilVo connDetail) throws Exception {
        

        //
        List<JavaDataVo> javaDataVos = GetJavaDataByDBInfoUtil.getJavaDatas(connDetail);
        //
        String location = connDetail.getSaveLocation();
        location = ExportToLocalUtil.createDirectory(location);
        location = location.replaceAll("\\\\", "/");

        if (location != null) {
            for (JavaDataVo vo : javaDataVos) {
                Set<String> importJars = vo.getImportJars();
                String classNm = vo.getClassNm();
                String tableNm = vo.getTableNm();
                List<TableFieldsVo> fields = vo.getFields();
                //
                String daoPackageRoot = "";
                String modelPackageRoot = "";
                for(TableFieldsVo field:fields){
                    daoPackageRoot = field.getDaoPackageRoot();
                    modelPackageRoot = field.getModelPackageRoot();
                }
                //
                String bean = mybatisJavaBeanGenerator.buildBean(importJars,fields,classNm);
                ExportToLocalUtil.exportBean(modelPackageRoot, location, classNm, bean);
                //
                String mapper = mybatisDaoGenerator.buildDao(importJars,fields,classNm);
                ExportToLocalUtil.exportDao(daoPackageRoot, location, classNm, mapper);
                //
                String xml = mybatisXmlGenerator.buildXml(fields,classNm,tableNm);                
                ExportToLocalUtil.exportXml(location, classNm, xml);
                //
                System.out.println("****************************************");
            }
        }

    }





}

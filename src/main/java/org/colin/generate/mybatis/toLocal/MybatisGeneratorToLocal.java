package org.colin.generate.mybatis.toLocal;


import java.util.List;
import java.util.Set;

import org.apache.ibatis.javassist.bytecode.stackmap.BasicBlock.Maker;
import org.apache.logging.log4j.Marker;
import org.colin.common.util.ExportToLocalUtil;
import org.colin.common.util.GetJavaDataByDBInfoUtil;
import org.colin.vo.ConnDeatilVo;
import org.colin.vo.JavaDataVo;

public class MybatisGeneratorToLocal {
    public static void generate(ConnDeatilVo connDetail) {
        

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
                List<String[]> fields = vo.getFields();
                //
                String bean = GenerateMybatisJavaBean.build(packageNm, importJars, fields, classNm);
                ExportToLocalUtil.exportBean(location, classNm, bean);
                //
                String mapper = GenerateMybatisMapper.build(packageNm, importJars, fields, classNm);
                ExportToLocalUtil.exportMapper(location, classNm, mapper);
                //
                String xml = GenerateMybatisXml.build(packageNm, fields, classNm, tableNm);
                ExportToLocalUtil.exportXml(location, classNm, xml);
                //
                System.out.println("****************************************");
            }
        }

    }





}

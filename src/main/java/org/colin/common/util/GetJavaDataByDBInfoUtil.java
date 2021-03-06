/**
 * 
 */
package org.colin.common.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.colin.common.Exception.DbConnectionException;
import org.colin.common.Exception.JavaTypeNotFoundException;
import org.colin.common.enumClass.ImportJarEnum;
import org.colin.common.enumClass.SqlTypeTransferEnum;
import org.colin.vo.ConnDeatilVo;
import org.colin.vo.DBJavaDataVo;
import org.colin.vo.TableFieldsVo;

/**
 * @author Colin
 *
 */
public class GetJavaDataByDBInfoUtil {
    public static List<DBJavaDataVo> getJavaDatas(ConnDeatilVo connDeatilVo) throws Exception {

        List<DBJavaDataVo> javaDataVos = new ArrayList<>();
        List<String> errorMsg = new ArrayList<>();
        Connection conn = null;
        ResultSet rs = null;
        DatabaseMetaData dma = null;
        String catalog = null;
        String schemaPattern = null;
        String columnNamePattern = null;
        String[] tables = connDeatilVo.getTables().split(";");
        for (String table : tables) {
            table  = table.replace(";", "");
            // tableNms
            String[] tableNms = table.split(":")[1].split(",");
            // packageRoots
            String[] packageRoots = table.split(":")[0].split(",");
            String daoPackageRoot = "";
            String modelPackageRoot = "";
            // daoPackageRoot
            if(packageRoots.length == 3){
                daoPackageRoot = packageRoots[1];
                modelPackageRoot = packageRoots[2];
            }else{
                daoPackageRoot = packageRoots[0];
                modelPackageRoot = packageRoots[1];
            }
            // getTableNm
            for(String tableNm:tableNms){
                // *
                Set<String> importJars = new HashSet<>();
                // *
                List<TableFieldsVo> fields = new ArrayList<>();
                try {
                    Class.forName(connDeatilVo.getDbDriverClassNm());
                    conn = DriverManager.getConnection(connDeatilVo.getUrl(), connDeatilVo.getUserNm(),
                            connDeatilVo.getPsd());

                    dma = conn.getMetaData();
                    rs = dma.getColumns(catalog, schemaPattern, tableNm, columnNamePattern);

                    while (rs.next()) {
                        String columnNm = rs.getString("COLUMN_NAME");
                        String sqlType = rs.getString("TYPE_NAME");
                        String javaType = SqlTypeTransferEnum.getJavaTypeBySqlType(sqlType);
                        if(javaType == null){
                            String javaTypeNotFound = "javaType is NULL when:"+MethodUtils.N+
                                                      "columnNm="+columnNm+MethodUtils.N+
                                                      "sqlType="+sqlType+MethodUtils.N;
                            
                            errorMsg.add(javaTypeNotFound);
                            continue;
                        }
                        String importJar = ImportJarEnum.getImportStrByTypeName(javaType);
                        if (importJar != null) {
                            importJars.add(importJar);
                        }
                        
                        if(errorMsg.size() > 0){
                            throw new JavaTypeNotFoundException(errorMsg.toString());
                        }
                        
                        TableFieldsVo tableFieldsVo = new TableFieldsVo.Builder(columnNm.toLowerCase(), 
                                                                                javaType,
                                                                                daoPackageRoot,
                                                                                modelPackageRoot).build();
                        fields.add(tableFieldsVo);
                    }
                    if(fields.size() == 0){
                        throw new DbConnectionException("no field set");
                    }
                    // *
                    String classNm = MethodUtils.removeSplitForClassNm(tableNm.toLowerCase());

                    DBJavaDataVo javaDataVo = new DBJavaDataVo.Builder(classNm, tableNm, importJars, fields).build();

                    javaDataVos.add(javaDataVo);

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    if (rs != null) {
                        try {
                            rs.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }                
            }

        }

        return javaDataVos;
    }
}

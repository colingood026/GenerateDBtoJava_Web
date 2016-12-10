/**
 * 
 */
package org.colin.vo;

/**
 * @author Colin
 * 用來裝欄位名稱以及它的型別
 */
public class TableFieldsVo {
    private final String columnNm;
    private final String javaType;
    private final String daoPackageRoot;
    private final String modelPackageRoot;
    


    public String getDaoPackageRoot() {
        return daoPackageRoot;
    }

    public String getModelPackageRoot() {
        return modelPackageRoot;
    }

    public String getColumnNm() {
        return columnNm;
    }

    public String getJavaType() {
        return javaType;
    }

    private TableFieldsVo(Builder builder){
        this.columnNm = builder.columnNm;
        this.javaType = builder.javaType;
        this.daoPackageRoot = builder.daoPackageRoot;
        this.modelPackageRoot = builder.modelPackageRoot;

    }
    
    public static class Builder{
        private final String columnNm;
        private final String javaType;
        private final String daoPackageRoot;
        private final String modelPackageRoot;
        
        public Builder(String columnNm,
                       String javaType,
                       String daoPackageRoot,
                       String modelPackageRoot){
            this.columnNm = columnNm;
            this.javaType = javaType;
            this.daoPackageRoot = daoPackageRoot;
            this.modelPackageRoot = modelPackageRoot;
        }
        
        public TableFieldsVo build(){
            return new TableFieldsVo(this);
        }        
    }
}

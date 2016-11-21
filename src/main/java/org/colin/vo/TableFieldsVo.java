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
    
    public String getColumnNm() {
        return columnNm;
    }

    public String getJavaType() {
        return javaType;
    }

    private TableFieldsVo(Builder builder){
        this.columnNm = builder.columnNm;
        this.javaType = builder.javaType;
    }
    
    public static class Builder{
        private final String columnNm;
        private final String javaType;
        public Builder(String columnNm,
                        String javaType){
            this.columnNm = columnNm;
            this.javaType = javaType;
        }
        
        public TableFieldsVo build(){
            return new TableFieldsVo(this);
        }        
    }
}

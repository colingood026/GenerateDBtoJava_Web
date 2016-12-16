package org.colin.vo;
/**
 * 一個普通的JAVA bean裡面個別的field
 * @author Colin
 *
 */
public class CommonClassfield {
    
    /**
     * 型別
     */
    private String type;
    /**
     * 屬性名稱
     */
    private String fieldNm;
    /**
     * 註解
     */
    private String comment;
    
    
    
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getFieldNm() {
        return fieldNm;
    }
    public void setFieldNm(String fieldNm) {
        this.fieldNm = fieldNm;
    }
    public String getComment() {
        return comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    @Override
    public String toString() {
        return "CommonJavaVo [type=" + type + ", fieldNm=" + fieldNm + ", comment=" + comment + "]";
    }
    
    
}

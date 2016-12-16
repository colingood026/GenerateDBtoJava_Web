package org.colin.vo;

import java.util.List;
/**
 * 包含一個普通的class該有的東西
 * @author Colin
 *
 */
public class CommonClassVO {
    /**
     * 類別名
     */
    private String classNm;
    /**
     * 集合 for：型別,屬姓名稱,備註
     */
    private List<CommonClassfield> commonClassfields;
    public String getClassNm() {
        return classNm;
    }
    public void setClassNm(String classNm) {
        this.classNm = classNm;
    }
    public List<CommonClassfield> getCommonClassfields() {
        return commonClassfields;
    }
    public void setCommonClassfields(List<CommonClassfield> commonClassfields) {
        this.commonClassfields = commonClassfields;
    }
    @Override
    public String toString() {
        return "CommonClassVO [classNm=" + classNm + ", commonClassfields=" + commonClassfields + "]";
    }


    
    
    
}

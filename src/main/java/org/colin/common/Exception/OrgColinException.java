package org.colin.common.Exception;
/**
 * @author colin.lee
 * 這個專案我自訂的錯誤父類別，自訂的錯誤都要繼承這個類別
 */
public class OrgColinException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public OrgColinException(String message){
        super(message);
    }
    
    public OrgColinException(String message,Throwable cause){
        super(message,cause);
    }
}

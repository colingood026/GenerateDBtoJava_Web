/**
 * 
 */
package org.colin.common.Exception;

/**
 * @author Colin
 * 從資料庫抓出table內容時發生錯誤
 */
public class DbConnectionException extends OrgColinException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    public DbConnectionException(String message){
        super(message);
    }
    
    public DbConnectionException(String message,Throwable cause){
        super(message,cause);
    }
}

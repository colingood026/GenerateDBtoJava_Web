/**
 * 
 */
package org.colin.common.Exception;

/**
 * @author colin.lee
 * sql型別轉換java型別時發生錯誤
 */
public class JavaTypeNotFoundException extends OrgColinException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public JavaTypeNotFoundException(String message){
		super(message);
	}
	
	public JavaTypeNotFoundException(String message,Throwable cause){
		super(message,cause);
	}

}

package org.colin.common.util;
/**
 * 將exception詳細記錄
 * @author colin.lee
 *
 */
public class ExceptionRecoedUtil {

	private ExceptionRecoedUtil(){
		
	}
	
	public static String recordException(Throwable t){
		StringBuffer bf = new StringBuffer();
		bf.append("\n----------- record Exception start -----------").append("\n");
		bf.append("Exception:" + t.toString()).append("\n");
        StackTraceElement[] messages = t.getStackTrace();
        for (StackTraceElement i : messages) {
            bf.append("類名:" + i.getClassName()).append("\n");
            bf.append("文件名:" + i.getFileName()).append("\n");
            bf.append("行號:" + i.getLineNumber()).append("\n");
            bf.append("方法名:" + i.getMethodName()).append("\n");
            bf.append("信息:" + i.toString()).append("\n");
            bf.append("==========================").append("\n");
        }
        
        bf.append("----------- record Exception end -----------");
        return bf.toString();
	}
}
